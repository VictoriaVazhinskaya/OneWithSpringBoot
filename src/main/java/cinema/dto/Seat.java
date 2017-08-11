package cinema.dto;

import javax.persistence.*;

/**
 * Created by Victoria on 8/11/17.
 */

@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private long id;

    private int number;

    @Column(name = "reservation_id")
    private long reservationId;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(final long reservationId) {
        this.reservationId = reservationId;
    }
}
