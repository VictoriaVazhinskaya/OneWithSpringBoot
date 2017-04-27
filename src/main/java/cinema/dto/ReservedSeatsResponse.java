package cinema.dto;

import java.util.List;

/**
 * Created by Tory on 25.04.2017.
 */
public class ReservedSeatsResponse extends Response {
    static final short MAX_SEAT_NUMBER = 225;
    private List<Short> reservedSeats;


    public List<Short> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Short> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }


}
