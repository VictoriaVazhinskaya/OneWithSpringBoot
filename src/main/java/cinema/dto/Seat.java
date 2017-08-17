package cinema.dto;

import javax.persistence.*;

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
    private Long bookingid;

    public Seat(){}

    public Seat(Long bookingid, short number) {
        this.bookingid = bookingid;
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

    public Long getBookingid() {
        return bookingid;
    }

    public void setBookingid(Long bookingid) {
        this.bookingid = bookingid;
    }

}
