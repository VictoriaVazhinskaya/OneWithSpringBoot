package cinema.dto;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by vazhinskaya on 4/26/17.
 */
public class SeanceShortInfo extends SeanceBaseInfo {
    private int film_id;

    public SeanceShortInfo(int film_id, Date date, Time time) {
        super(date, time);
        this.film_id = film_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

}
