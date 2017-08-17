package cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tory on 23.04.2017.
 */
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private Long id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "seance_id", nullable = false)
    private Seance seance;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "booking_id")
    private List<Seat> reservedSeats;

    @Column(name = "user_id")
    private Long userid;

    public Booking(){}

    public Booking(final Seance seance, final Long userid){
        this.seance = seance;
        this.userid = userid;
    }

/*    public Booking(Seance seance, short ... seats) {
        this.seance = seance;
        int numberOfSeats = seats.length;
        this.reservedSeats = new ArrayList<Short>(numberOfSeats);
        for(int i=0; i<numberOfSeats; i++){
            reservedSeats.add(seats[i]);
        }
    }*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Seat> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Seat> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Seance getSeance() {
        return seance;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    @JsonIgnore
    public List<Short> getReservedSeatNumbers(){
        return reservedSeats.stream().map(s -> s.getNumber()).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (id != null ? !id.equals(booking.id) : booking.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
