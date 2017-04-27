package cinema.manager;


import javax.inject.Named;
import java.util.ResourceBundle;

/**
 * Created by Tory on 24.04.2017.
 */
@Named
public class MessageManager {

    private ResourceBundle resourceBundle;
    public static final String NONEXISTENT_SEANCE_ID_MSG = "seance.id.error.nonexistent";
    public static final String NONEXISTENT_SEAT_ERR_MSG = "seat.number.error.nonexistent";
    public static final String TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG = "seat.error.alreadyreserved";
    public static final String NONEXISTENT_RESERVATION_CODE_ERR_MSG = "reservation.code.error.nonexistent";
    public static final String RESERVATION_CANCELING_FAILURE_ERR_MSG = "reservation.cancel.error";

    public MessageManager(){
        resourceBundle = ResourceBundle.getBundle("messages");
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }

}
