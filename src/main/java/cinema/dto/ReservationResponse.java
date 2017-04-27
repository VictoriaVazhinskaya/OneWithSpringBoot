package cinema.dto;

/**
 * Created by Tory on 24.04.2017.
 */
public class ReservationResponse extends  Response {
    private String reservationCode;

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getReservationCode() {
        return reservationCode;
    }
}
