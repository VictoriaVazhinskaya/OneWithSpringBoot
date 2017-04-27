package cinema.dto;

import java.util.List;

/**
 * Created by Tory on 24.04.2017.
 */
public class BookingInfo {
    private int seanceId;
    private List<Short> seats;

    public int getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(int seanceId) {
        this.seanceId = seanceId;
    }

    public List<Short> getSeats() {
        return seats;
    }

    public void setSeats(List<Short> seats) {
        this.seats = seats;
    }
}
