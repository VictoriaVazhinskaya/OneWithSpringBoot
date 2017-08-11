package cinema.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tory on 23.04.2017.
 */
@Entity
@Table(name = "reservation")
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private int id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "seance_id", referencedColumnName = "id", table = "seance")
    private Seance seance;


    @OneToMany
    @JoinColumn(name="reservation_id", referencedColumnName="id")
    private List<Seat> reservedSeats;

    public Booking(){
        super();
    }

/*    public Booking(Seance seance, short ... seats) {
        this.seance = seance;
        int numberOfSeats = seats.length;
        this.reservedSeats = new ArrayList<Short>(numberOfSeats);
        for(int i=0; i<numberOfSeats; i++){
            reservedSeats.add(seats[i]);
        }
    }*/

    public List<Seat> getReservedSeats() {
        return reservedSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Seance getSeance() {
        return seance;
    }

    public Seance getSeanceID() {
        return seance;
    }

    public void setReservedSeats(List<Seat> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

/*    public void addSeat(short seat){
        reservedSeats.add(seat);
    }*/
}
