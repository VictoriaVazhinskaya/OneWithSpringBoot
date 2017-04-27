package cinema.dto;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Tory on 23.04.2017.
 */
public class Seance extends SeanceBaseInfo {
    private Film film;

    public Seance() {
        super();
    }

    public Seance(Date date, Time time, Film film) {
        super(date, time);
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
