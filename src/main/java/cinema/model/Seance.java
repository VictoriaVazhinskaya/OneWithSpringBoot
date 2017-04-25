package cinema.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Created by Tory on 23.04.2017.
 */
public class Seance {
    private String code;
    private Film film;
    private Date date;
    private Time time;

    public Seance(){
        super();
    }

    public Seance(String code, Date date, Time time, Film film) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.film = film;
    }

    public Date getDate(){
        return date;
    }

    public Film getFilm() {
        return film;
    }

    public Time getTime() {
        return time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String seance_code) {
        this.code = seance_code;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        return  Objects.hash(film, date, time);
    }
}
