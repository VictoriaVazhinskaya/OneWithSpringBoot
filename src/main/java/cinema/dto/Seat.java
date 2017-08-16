package cinema.dto;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;

/**
 * Created by Victoria on 8/11/17.
 */
@Entity
@Table(name="reserved_seat")
public class Seat {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private Long id;

    @Column(nullable = false)
    private short number;

    @Column(name = "booking_id", nullable = false)
    private Long bookingId;

    public Seat(){}

    public Seat(Long bookingId, short number) {
        this.bookingId = bookingId;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public short getNumber() {
        return number;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

}
