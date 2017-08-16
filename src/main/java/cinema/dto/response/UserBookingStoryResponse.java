package cinema.dto.response;

import cinema.dto.Booking;

import java.util.List;

/**
 * Created by Tory on 16.08.2017.
 */
public class UserBookingStoryResponse extends Response {

    private List<Booking> bookings;

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
