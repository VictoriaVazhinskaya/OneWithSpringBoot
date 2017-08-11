package cinema.dto;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Tory on 23.04.2017.
 */
@Entity
@Table(name = "seance")
public class Seance{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private long id;

    @Temporal(value=TemporalType.DATE)
    private Date date;

    @Temporal(value=TemporalType.TIME)
    private Time time;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @ManyToOne(targetEntity = Cinema.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    private Cinema cinema;

    public Seance() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(final Time time) {
        this.time = time;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(final Cinema cinema) {
        this.cinema = cinema;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
