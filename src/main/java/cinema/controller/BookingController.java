package cinema.controller;

import cinema.dao.OnlineBookingDao;
import cinema.dto.*;
import cinema.manager.MessageManager;
import cinema.util.BookingCodeValidator;
import cinema.util.SeatNumberValidator;
import cinema.util.StringFromNumbersMaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Tory on 20.04.2017.
 */

@RestController
@RequestMapping("/by/cinema")
@ComponentScan(basePackages = {"cinema.dao", "cinema.manager"})
public class BookingController {

    @Inject
    private MessageManager messageManager;

    @Inject
    private OnlineBookingDao onlineBookingDao;


    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public List<Seance> greeting() {
        List<Seance> raspisanie = onlineBookingDao.getSchedule();
        return raspisanie;
    }

    @RequestMapping(value="/seats", method = RequestMethod.POST)
    public ReservedSeatsResponse getReservedSeats(@RequestBody SeanceIdDump input) {
        int id = input.getId();
        ReservedSeatsResponse response = new ReservedSeatsResponse();
        String errorMessage = null;
        boolean isBookingReal = false;
        List<Short> alreadyReserved = null;
        if (id > 0) {
            alreadyReserved = onlineBookingDao.getReservedSeats(id);
            if(!alreadyReserved.isEmpty()){
                if (alreadyReserved.get(0) == null) {
                    alreadyReserved.clear();
                }
                isBookingReal = true;
            }
            else{
                alreadyReserved = null;
            }
        }
        if(!isBookingReal){
            errorMessage = String.format(messageManager.getProperty(MessageManager.NONEXISTENT_SEANCE_ID_MSG), id);
        }
        response.setReservedSeats(alreadyReserved);
        response.setErrorMessage(errorMessage);
        return response;
    }

    @RequestMapping(value="/booking", method = RequestMethod.POST)
    public ReservationResponse reserve(@RequestBody BookingInfo input){
        ReservationResponse response = new ReservationResponse();
        String errorMessage = null;
        int seanceId = input.getSeanceId();
        List<Short> seats = input.getSeats();
        boolean flag = false;
        String reservationCode = null;
        if (seanceId > 0) {
            short seatNumberCheckingResult;
            if((seatNumberCheckingResult = SeatNumberValidator.isValidNumbers(seats))
                    == SeatNumberValidator.SUCCESSFUL_VALIDATION) {
                Set<Short> alreadyReserved = new HashSet<Short>(onlineBookingDao.getReservedSeats(seanceId));
                if(!alreadyReserved.isEmpty()) {
                    List<Short> unavailableSeats = seats.stream().filter(seat -> alreadyReserved.contains(seat))
                            .collect(Collectors.toList());
                    if (unavailableSeats.isEmpty()) {
                        SeanceShortInfo info = onlineBookingDao.getSeanceInfo(seanceId);
                        reservationCode = generateBookingCode(info, seats.get(0));
                        onlineBookingDao.addReservation(reservationCode, seats, seanceId);
                    } else {
                        errorMessage = String.format(messageManager.getProperty(MessageManager
                                        .TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG),
                                StringFromNumbersMaker.getStringRepresentation(unavailableSeats));
                    }
                    flag = true;
                }
            }else{
                errorMessage = String.format(messageManager.getProperty(MessageManager.NONEXISTENT_SEAT_ERR_MSG),
                        seatNumberCheckingResult);
                flag = true;
            }
        }

        if(flag == false){
            errorMessage = String.format(messageManager.getProperty(MessageManager.NONEXISTENT_SEANCE_ID_MSG), seanceId);
        }
        response.setReservationCode(reservationCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

    @RequestMapping(value="/mybooking", method = RequestMethod.POST)
    public BookingResponse getBookingInfo(@RequestBody BookingCodeDump input){
        String inputString = input.getCode();
        BookingResponse response = new BookingResponse();
        boolean isBookingReal = false;
        if(BookingCodeValidator.isValid(inputString)) {
            Booking booking = onlineBookingDao.getBookingInfo(inputString);
            if(booking != null) {
                response.setBooking(booking);
                response.setErrorMessage(null);
                isBookingReal = true;
            }
        }
        if(!isBookingReal){
            response.setBooking(null);
            String errorMessage = String.format(messageManager.getProperty(MessageManager
                    .NONEXISTENT_RESERVATION_CODE_ERR_MSG), inputString);
            response.setErrorMessage(errorMessage);
        }
        return response;
    }

    @RequestMapping(value = "/mybooking/cancel", method = RequestMethod.DELETE)
    public CancellationResponse cancelReservation(@RequestBody BookingCodeDump input){
        String inputString = input.getCode();
        CancellationResponse response = new CancellationResponse();
        String errorMessage = null;
        boolean isReservationCanceled = false;
        if(BookingCodeValidator.isValid(inputString)) {
            isReservationCanceled = onlineBookingDao.deleteBooking(inputString);
        }
        if(!isReservationCanceled) {
            errorMessage = String.format(messageManager.getProperty(MessageManager
                    .RESERVATION_CANCELING_FAILURE_ERR_MSG), String.format(messageManager.getProperty(MessageManager
                    .NONEXISTENT_RESERVATION_CODE_ERR_MSG), inputString));
        }
        response.setErrorMessage(errorMessage);
        return response;
    }

    private String generateBookingCode(SeanceShortInfo info, short seatNumber) {
        int filmId = info.getFilm_id();
        String date = info.getDate().toString().replaceAll("-", "");
        String time = info.getTime().toString().substring(0, 6).replaceAll(":", "");
        return String.format("%1$d%2$s%3$s%4$d", filmId, date, time, seatNumber);
    }
}
