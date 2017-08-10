package cinema.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Tory on 23.04.2017.
 */

@Table(name = "seance")
public class Seance{

    @Id
    @GenericGenerator(name="gen", strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(unique = true, nullable = false, precision = 11, scale = 0)
    private long id;
    private Date date;
    private Time time;


    private Film film;
    private long cinemaId;

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

    public long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(final long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
