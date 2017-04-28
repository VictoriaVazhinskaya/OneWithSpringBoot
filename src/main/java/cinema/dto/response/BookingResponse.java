package cinema.dto.response;

import cinema.dto.Booking;

/**
 * Created by Tory on 24.04.2017.
 */
public class BookingResponse extends Response {

    private Booking booking;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}
