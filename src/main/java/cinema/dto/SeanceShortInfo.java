package cinema.dto;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by vazhinskaya on 4/26/17.
 */
public class SeanceShortInfo extends SeanceBaseInfo {
    private int filmId;

    public SeanceShortInfo(int filmId, Date date, Time time) {
        super(date, time);
        this.filmId = filmId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

}
