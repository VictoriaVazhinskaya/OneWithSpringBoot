package cinema.dto.request;

import java.util.List;

/**
 * Created by Tory on 13.08.2017.
 */
public class BookingRequest {

    private Long seanceId;
    private List<Short> seats;
    private Long userId;

    public Long getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(Long seanceId) {
        this.seanceId = seanceId;
    }

    public List<Short> getSeats() {
        return seats;
    }

    public void setSeats(List<Short> seats) {
        this.seats = seats;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
