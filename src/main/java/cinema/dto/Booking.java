package cinema.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tory on 23.04.2017.
 */

@Table(name = "reservation")
public class Booking {

    @Id
    @GenericGenerator(name="gen", strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(unique = true, nullable = false, precision = 11, scale = 0)
    private int id;


    private String code;

    @OneToOne(targetEntity = Seance.class)
    @JoinColumn(name = "seance_id", referencedColumnName = "id", table = "seance")
    private Seance seance;

    @ElementCollection
    @CollectionTable(name="seat", joinColumns=@JoinColumn(name="reservation_id"))
    private List<Short> reservedSeats;

    public Booking(){
        super();
    }

    public Booking(Seance seance, short ... seats) {
        this.seance = seance;
        int numberOfSeats = seats.length;
        this.reservedSeats = new ArrayList<Short>(numberOfSeats);
        for(int i=0; i<numberOfSeats; i++){
            reservedSeats.add(seats[i]);
        }
    }

    public List<Short> getReservedSeats() {
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

    public void setReservedSeats(List<Short> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void addSeat(short seat){
        reservedSeats.add(seat);
    }
}
