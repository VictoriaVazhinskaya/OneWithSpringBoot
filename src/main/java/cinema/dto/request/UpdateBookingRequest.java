package cinema.dto.request;

import java.util.List;
import java.util.Set;

/**
 * Created by Tory on 14.08.2017.
 */
public class UpdateBookingRequest {

    private Long bookingId;

    private String code;

    private List<Short> newSeatSet;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public List<Short> getNewSeatSet() {
        return newSeatSet;
    }

    public void setNewSeatSet(List<Short> newSeatSet) {
        this.newSeatSet = newSeatSet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
