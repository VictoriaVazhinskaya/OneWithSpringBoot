package cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Tory on 23.04.2017.
 */
@Entity
@Table(name = "seance")
public class Seance{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private Long id;

    @Temporal(value=TemporalType.DATE)
    private Date date;

    @Temporal(value=TemporalType.TIME)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @OneToMany(mappedBy = "seance")
    private List<Booking> bookings;
/*    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;*/

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(final Date time) {
        this.time = time;
    }

/*    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(final Cinema cinema) {
        this.cinema = cinema;
    }*/

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @JsonIgnore
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    /*    @Override
    public String toString() {
        return new StringBuilder().append("[id=").append(id)
                .append(", date=").append(date)
                .append(", time=").append(time)
                .append(", film_id=").append(film.getId())
                .append(", cinema_id").append(cinema.getId())
                .append("]").toString();
    }*/
}
