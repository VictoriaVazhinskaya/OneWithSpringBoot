package cinema.controller;

import java.util.*;

import cinema.dao.OnlineBookingDao;
import cinema.manager.MessageManager;
import cinema.model.*;
import cinema.responce.BookingResponse;
import cinema.responce.CancellationResponse;
import cinema.responce.ReservationResponse;
import cinema.responce.ReservedSeatsResponse;
import cinema.util.BookingCodeValidator;
import cinema.util.SeanceCodeValidator;
import cinema.util.SeatNumberValidator;
import cinema.util.StringFromNumbersMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tory on 20.04.2017.
 */

@RestController
@RequestMapping("/by/cinema")
@ComponentScan(basePackages = {"cinema.config"})
public class BookingController {

    @Autowired
    private MessageManager messageManager;

    @Autowired
    private OnlineBookingDao onlineBookingDao;



    @RequestMapping(value="/raspisanie", method = RequestMethod.GET)
    public List<Seance> greeting() {
        List<Seance> raspisanie = onlineBookingDao.getRaspisanie();
        return raspisanie;
    }

    @RequestMapping(value="/seats", method = RequestMethod.POST)
    public ReservedSeatsResponse getReservedSeats(@RequestBody SeanceCodeDump input){
        String inputString = input.getCode();
        ReservedSeatsResponse response = new ReservedSeatsResponse();
        String errorMessage = null;
        boolean isBookingReal = false;
        List<Short> alreadyReserved = null;
        if(SeanceCodeValidator.isValid(inputString)){
            alreadyReserved = onlineBookingDao.getReservedSeats(inputString);
            if(!alreadyReserved.isEmpty()){
                isBookingReal = true;
            }
            else{
                alreadyReserved = null;
            }
        }
        if(!isBookingReal){
            String errorMessagePart1 = messageManager
                    .getProperty(MessageManager.NONEXISTENT_SEANCE_CODE_MSG_P1);
            String errorMessagePart2 = messageManager
                    .getProperty(MessageManager.NONEXISTENT_SEANCE_CODE_MSG_P2);
            errorMessage = errorMessagePart1 + inputString + errorMessagePart2;
        }
        response.setReservedSeats(alreadyReserved);
        response.setErrorMessage(errorMessage);
        return response;
    }

    @RequestMapping(value="/booking", method = RequestMethod.POST)
    public ReservationResponse reserve(@RequestBody BookingInfo input){
        ReservationResponse response = new ReservationResponse();
        String errorMessage = null;
        String inputString = input.getCode();
        List<Short> seats = input.getSeats();
        boolean flag = false;
        String reservationCode = null;
        if(SeanceCodeValidator.isValid(inputString)){
            short seatNumberCheckingResult;
            if((seatNumberCheckingResult = SeatNumberValidator.isValidNumbers(seats))
                    == SeatNumberValidator.SUCCESSFUL_VALIDATION) {
                Set<Short> alreadyReserved = new HashSet<Short>(onlineBookingDao.getReservedSeats(inputString));
                if(!alreadyReserved.isEmpty()) {
                    List<Short> unavailableSeats = new ArrayList<Short>();
                    for(Short seat: seats){
                        if(!alreadyReserved.add(seat)){
                            unavailableSeats.add(seat);
                        }
                    }
                    if (unavailableSeats.isEmpty()) {
                        reservationCode = generateBronikaUID(inputString, seats.get(0));
                        onlineBookingDao.addReservation(reservationCode, seats, inputString);
                    } else {
                        String errorMessagePart1 = messageManager
                                .getProperty(MessageManager.TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG_P1);
                        String errorMessagePart2 = messageManager
                                .getProperty(MessageManager.TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG_P2);
                        errorMessage = errorMessagePart1 + StringFromNumbersMaker.getStringRepresentation(unavailableSeats)
                                + errorMessagePart2;
                    }
                    flag = true;
                }
            }else{
                String errorMessagePart1 = messageManager.getProperty(MessageManager.NONEXISTENT_SEAT_ERR_MSG_P1);
                String errorMessagePart2 = messageManager.getProperty(MessageManager.NONEXISTENT_SEAT_ERR_MSG_P2);
                errorMessage = errorMessagePart1 + seatNumberCheckingResult + errorMessagePart2;
                flag = true;
            }
        }

        if(flag == false){
            String errorMessagePart1 = messageManager
                    .getProperty(MessageManager.NONEXISTENT_SEANCE_CODE_MSG_P1);
            String errorMessagePart2 = messageManager
                    .getProperty(MessageManager.NONEXISTENT_SEANCE_CODE_MSG_P2);
            errorMessage = errorMessagePart1 + inputString + errorMessagePart2;
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
            String errorMessagePart1 = messageManager.getProperty(MessageManager.NONEXISTENT_BRONIKA_UID_ERR_MSG_P1);
            String errorMessagePart2 = messageManager.getProperty(MessageManager.NONEXISTENT_BRONIKA_UID_ERR_MSG_P2);
            String errorMessage = errorMessagePart1 + inputString + errorMessagePart2;
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
            String errorMessagePart1 = messageManager.getProperty(MessageManager.RESERVATION_CANCELING_FAILURE_ERR_MSG);
            String errorMessagePart2 = messageManager.getProperty(MessageManager.NONEXISTENT_BRONIKA_UID_ERR_MSG_P1);
            String errorMessagePart3 = messageManager.getProperty(MessageManager.NONEXISTENT_BRONIKA_UID_ERR_MSG_P2);
            errorMessage = errorMessagePart1 + errorMessagePart2 + inputString + errorMessagePart3;
        }
        response.setErrorMessage(errorMessage);
        return response;
    }

    private String generateBronikaUID(String seanceCode, short seatNumber){
        int n = seanceCode.length();
        Random random = new Random();
        int shift = random.nextInt(n - 1);
        boolean firstOrSecond = random.nextBoolean();
        StringBuilder stringBuilder = new StringBuilder();
        if(firstOrSecond){
            stringBuilder.append(new StringBuilder(seanceCode.substring(shift)).reverse());
        }else{
            stringBuilder.append(seanceCode.substring(shift));
        }
        stringBuilder.append(seatNumber);
        stringBuilder.append(new StringBuilder(seanceCode.substring(0, shift)).reverse());
        return stringBuilder.toString();
    }
}
