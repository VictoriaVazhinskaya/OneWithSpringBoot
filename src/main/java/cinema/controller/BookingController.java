package cinema.controller;

import cinema.dao.BookingRepository;
import cinema.dao.SeanceRepository;
import cinema.dao.UserRepository;
import cinema.dto.Booking;
import cinema.dto.Seance;
import cinema.dto.response.*;
import cinema.dto.user.User;
import cinema.message.MessageLabel;
import cinema.security.UserActionManager;
import cinema.service.booking.BookingService;
import cinema.util.BookingCodeValidator;
import cinema.util.NumberRepresentationUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tory on 20.04.2017.
 */

@RestController
public class BookingController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingService bookingService;

    private static final String BOOKING_ACTION_UNAVAILABLE_IF_GUEST_ATTR = "bookingUnavailable";
    private static final String GUEST_ID = "gid";



    @RequestMapping(value = {"/", "/day", "/home"}, method = RequestMethod.GET)
    public SeancesResponse getSpecifiedDaySeances(final HttpServletRequest httpServletRequest,
                                                  @RequestParam(required = false) final String date) {
        final SeancesResponse response = new SeancesResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        List<Seance> seances = new ArrayList<>();
        try {
            final Date entireDate;
            if(date == null){
                final LocalDate localDate = LocalDate.now();
                entireDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant());
            }else {
                entireDate = Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant());
            }
            seances = seanceRepository.findByDateLike(entireDate);
        } catch (Exception e) {
            e.printStackTrace();
            response.setErrorMessage(messageSource.getMessage(MessageLabel.INTERNAL_ERROR_ERR.getLabel(), null, locale));
        }
        if (seances.isEmpty()) {
            response.setErrorMessage(messageSource.getMessage(MessageLabel.SEANCES_NOT_FOUND_FOR_DATE_ERR.getLabel(),
                    new Object[]{date}, locale));
        } else {
            response.setSeances(seances);
        }
        return response;
    }

    @RequestMapping(value = "/film/seances", method = RequestMethod.GET)
    public SeancesResponse getSpecifiedFilmSeances(final HttpServletRequest httpServletRequest,
                                                    @RequestParam final Long id,
                                                   @RequestParam final String date) {
        final SeancesResponse response = new SeancesResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        List<Seance> seances = new ArrayList<>();
        try {
            seances = seanceRepository.findByFilmIdAndDateGreaterThanEqual(id, formatter.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            response.setErrorMessage(messageSource.getMessage(MessageLabel.INTERNAL_ERROR_ERR.getLabel(), null, locale));
        }
        if (seances.isEmpty()) {
            response.setErrorMessage(messageSource.getMessage(MessageLabel.SEANCES_NOT_FOUND_FOR_FILM_ID_ERR.getLabel(),
                    new Object[]{id}, locale));
        } else {
            response.setSeances(seances);
        }
        return response;
    }

    @RequestMapping(value = "/place/seances", method = RequestMethod.GET)
    public SeancesResponse getSpecifiedCinemaSeances(final HttpServletRequest httpServletRequest,
                                                     @RequestParam final Long cinema,
                                                     @RequestParam final String date) {
        final SeancesResponse response = new SeancesResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        List<Seance> seances = new ArrayList<>();
        try {
            seances = seanceRepository.findByHallCinemaIdAndDateGreaterThanEqual(cinema, formatter.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
            response.setErrorMessage(messageSource.getMessage(MessageLabel.INTERNAL_ERROR_ERR.getLabel(), null, locale));
        }
        if (seances.isEmpty()) {
            response.setErrorMessage(messageSource.getMessage(MessageLabel.SEANCES_NOT_FOUND_FOR_DATE_FOR_CINEMA_ID_ERR.getLabel(),
                    new Object[]{date, cinema}, locale));
        } else {
            response.setSeances(seances);
        }
        return response;
    }

    @RequestMapping(value = "/seance/occupancy", method = RequestMethod.GET)
    public OccupancyInfoResponse getFreeSeatsForSeance(final HttpServletRequest httpServletRequest,
                                                       @RequestParam final Long id) {
        final OccupancyInfoResponse response = new OccupancyInfoResponse();
        final Seance seance = seanceRepository.findOne(id);
        if (seance == null) {
            final Locale locale = localeResolver.resolveLocale(httpServletRequest);
            response.setErrorMessage(messageSource.getMessage(MessageLabel.NONEXISTENT_SEANCE_ID_ERR.getLabel(),
                    new Object[]{id}, locale));
        } else {
            final int hallCapacity = seance.getHall().getCapacity();
            response.setCapacity(hallCapacity);
            final List<Booking> bookings = bookingRepository.findBySeanceId(id);
            final List<Short> reservedSeats = new ArrayList<>();
            bookings.stream().forEach(b -> reservedSeats.addAll(b.getReservedSeatNumbers()));
            response.setReservedSeats(reservedSeats);
        }
        return response;
    }

    @RequestMapping(value = "/seance/booking", method = {RequestMethod.GET, RequestMethod.POST})
    public BookingResponse book(final HttpServletRequest httpServletRequest,
                                @RequestParam final Long seanceId,
                                @RequestParam(required = false) final Long userId,
                                @RequestParam final String seats,
                                final HttpServletRequest request) {
        final BookingResponse response = new BookingResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        Booking booking = null;
        User currentUser = null;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final HttpSession session = request.getSession(true);
        final boolean isUserGuest;
        final Boolean isBookingUnavailableForGuest;
        if((isUserGuest = authentication instanceof AnonymousAuthenticationToken)){
            if((isBookingUnavailableForGuest = (Boolean) session.getAttribute(BOOKING_ACTION_UNAVAILABLE_IF_GUEST_ATTR)) == null){
                currentUser = new User();
                userRepository.save(currentUser);
                session.setAttribute(GUEST_ID, currentUser.getId());
            }else  if (!isBookingUnavailableForGuest) {
                currentUser = userRepository.findOne((Long) session.getAttribute(GUEST_ID));
            }
        }else{
            currentUser = (User)authentication.getPrincipal();
        }
        if(currentUser != null) {
            if (UserActionManager.allowsToBook(userId, currentUser.getId(), currentUser.getRole())) {
                final Seance seance = seanceRepository.findOne(seanceId);
                booking = new Booking(seance, currentUser.getId());
                final List<Short> unavailableSeats = bookingService.save(booking,
                        NumberRepresentationUtil.getNumberListFromString(seats));
                if (unavailableSeats != null) {
                    if (unavailableSeats.size() > 0) {
                        response.setErrorMessage(messageSource.getMessage(MessageLabel.TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR.getLabel(),
                                new Object[]{NumberRepresentationUtil.getStringRepresentation(unavailableSeats)},
                                locale));
                        booking = null;
                    } else {
                        response.setIsRebooked(true);
                    }
                }
            } else {
                response.setErrorMessage(messageSource.getMessage(MessageLabel
                                .INADMISSIBLE_ACTION_ATTEMPT_ERR.getLabel(), null, locale));
            }
        }else{
            response.setErrorMessage(messageSource.getMessage(MessageLabel.INADMISSIBLE_ACTION_ATTEMPT_ERR.getLabel(), null, locale));
            response.setInfoMessage(messageSource.getMessage(MessageLabel.ACTION_FORBIDDEN_CAUSE_INFO.getLabel(), null, locale));
        }
        if(isUserGuest) {
            session.setAttribute(BOOKING_ACTION_UNAVAILABLE_IF_GUEST_ATTR, booking != null);
        }
        response.setBooking(booking);
        return response;
    }

    @RequestMapping(value = "/adm/search/bycode", method = RequestMethod.GET)
    public BookingResponse getBookingByCode(final HttpServletRequest httpServletRequest,
                                            @RequestParam final String bookingCode) {
        final BookingResponse response = new BookingResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        if(bookingCode != null && BookingCodeValidator.isValid(bookingCode)){
            final Booking booking = bookingRepository.findByCode(bookingCode);
            if(booking != null) {
                final User authorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (UserActionManager.givesAccessToUserInfo(booking.getUserid(), authorizedUser.getId(), authorizedUser.getRole())){
                    response.setBooking(booking);
            }else{
                    response.setErrorMessage(messageSource.getMessage(MessageLabel.INADMISSIBLE_ACTION_ATTEMPT_ERR.getLabel(), null, locale));
                }
            }
        }else{
            response.setErrorMessage(messageSource.getMessage(MessageLabel.NONEXISTENT_RESERVATION_CODE_ERR.getLabel(),
                    new Object[]{bookingCode}, locale));
        }
        return response;
    }

    @RequestMapping(value = {"/user/story/bylogin", "/adm/user-story/bylogin"}, method = RequestMethod.GET)
    public UserBookingStoryResponse getUserBookingStory(final HttpServletRequest httpServletRequest,
                                                        @RequestParam final String username) {
        final User user = userRepository.findByLogin(username);
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        UserBookingStoryResponse response = new UserBookingStoryResponse();
        if(user != null){
            response = getUserBookingStory(httpServletRequest, user.getId());
        }else{
            response.setErrorMessage(messageSource.getMessage(MessageLabel.NONEXISTENT_USER_LOGIN_ERR.getLabel(),
                    new Object[]{username}, locale));
        }
        return response;
    }

    @RequestMapping(value = {"/user/story/byid", "/adm/user-story/byid"}, method = RequestMethod.GET)
    public UserBookingStoryResponse getUserBookingStory(final HttpServletRequest httpServletRequest,
            @RequestParam final Long userId) {
        final UserBookingStoryResponse response = new UserBookingStoryResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        final User authorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(UserActionManager.givesAccessToUserInfo(userId, authorizedUser.getId(), authorizedUser.getRole())) {
            final List<Booking> bookings = bookingRepository.findByUserid(userId);
            response.setBookings(bookings);
        }else{
            response.setErrorMessage(messageSource.getMessage(MessageLabel.INADMISSIBLE_ACTION_ATTEMPT_ERR.getLabel(),
                    null, locale));
        }
        return response;
    }


    @RequestMapping(value = {"/user/cancel", "adm/user/cancel"}, method = {RequestMethod.GET, RequestMethod.DELETE})
    public CancellationResponse cancelBooking(final HttpServletRequest httpServletRequest,
                                              @RequestParam final Long bookingId) {
        final CancellationResponse response = new CancellationResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        final Booking booking = bookingRepository.findOne(bookingId);
        final User authorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (UserActionManager.allowsToCancelBooking(booking.getUserid(), authorizedUser.getId(), authorizedUser.getRole())) {
            bookingService.cancel(bookingId);
        }else{
            response.setErrorMessage(messageSource.getMessage(MessageLabel.INADMISSIBLE_ACTION_ATTEMPT_ERR.getLabel(), null, locale));
        }
        return response;
    }

    @RequestMapping(value = {"/user/rebooking", "adm/user/rebooking"}, method = RequestMethod.GET)
    public BookingResponse book(final HttpServletRequest httpServletRequest,
                                @RequestParam final Long bookingId,
                                @RequestParam final String newSeats) {
        final BookingResponse response = new BookingResponse();
        final Locale locale = localeResolver.resolveLocale(httpServletRequest);
        final Booking booking = bookingRepository.findOne(bookingId);
        final User authorizedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (UserActionManager.allowsToRebook(booking.getUserid(), authorizedUser.getId(), authorizedUser.getRole())) {
            final List<Short> unavailableSeats = bookingService.update(booking,
                    NumberRepresentationUtil.getNumberListFromString(newSeats));
            if (!unavailableSeats.isEmpty()) {
                response.setErrorMessage(messageSource.getMessage(MessageLabel
                                .TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR.getLabel(),
                        new Object[]{NumberRepresentationUtil.getStringRepresentation(unavailableSeats)}, locale));
            } else {
                response.setBooking(booking);
                response.setIsRebooked(true);
            }
        } else {
            response.setErrorMessage(messageSource.getMessage(MessageLabel.INADMISSIBLE_ACTION_ATTEMPT_ERR.getLabel(), null, locale));
        }
        return response;
    }

}
