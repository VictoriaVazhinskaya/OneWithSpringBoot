package cinema.dto.response;

import cinema.dto.Booking;

/**
 * Created by Tory on 24.04.2017.
 */
public class BookingResponse extends Response {

    private Booking booking;

    private boolean isRebooked = false;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public boolean getIsRebooked() {
        return isRebooked;
    }

    public void setIsRebooked(boolean isRebooked) {
        this.isRebooked = isRebooked;
    }
}
