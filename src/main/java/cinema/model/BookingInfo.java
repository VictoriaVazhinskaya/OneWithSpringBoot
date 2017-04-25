package cinema.model;

import java.util.List;

/**
 * Created by Tory on 24.04.2017.
 */
public class BookingInfo {
    private String code;
    private List<Short> seats;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Short> getSeats() {
        return seats;
    }

    public void setSeats(List<Short> seats) {
        this.seats = seats;
    }
}
