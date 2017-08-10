package cinema.dto;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by vazhinskaya on 4/26/17.
 */
public abstract class SeanceBaseInfo {
    private Date date;
    private Time time;
    private long cinemaId;

    public SeanceBaseInfo() {
        super();
    }

    public SeanceBaseInfo(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(final long cinemaId) {
        this.cinemaId = cinemaId;
    }
}
