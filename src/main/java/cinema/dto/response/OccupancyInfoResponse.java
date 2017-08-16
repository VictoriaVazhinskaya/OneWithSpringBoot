package cinema.dto.response;

import java.util.List;
import java.util.Set;

/**
 * Created by Tory on 25.04.2017.
 */
public class OccupancyInfoResponse extends Response {

    private int capacity;
    private List<Short> reservedSeats;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Short> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Short> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }
}
