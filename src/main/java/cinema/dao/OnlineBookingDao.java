package cinema.dao;

import cinema.model.Booking;
import cinema.model.Seance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by Tory on 23.04.2017.
 */

public interface OnlineBookingDao {

    List<Seance> getRaspisanie();

    List<Short> getReservedSeats(String seanceCode);

    //List<Short> checkSeats(String seanceCode, List<Short> seats);

    void addReservation(String reservation_code, List<Short> seats, String seance_code);

    Booking getBookingInfo(String bookingCode);

    boolean deleteBooking(String bookingCode);
}
