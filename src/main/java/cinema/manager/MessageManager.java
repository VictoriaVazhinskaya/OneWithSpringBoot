package cinema.manager;


//import javax.inject.Named;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Created by Tory on 24.04.2017.
 */
@Component
public class MessageManager {

    private ResourceBundle resourceBundle;
    public static final String NONEXISTENT_SEANCE_ID_MSG = "seance.id.error.nonexistent";
    public static final String NONEXISTENT_SEAT_ERR_MSG = "seat.number.error.nonexistent";
    public static final String TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR = "seat.error.alreadyreserved";
    public static final String NONEXISTENT_RESERVATION_CODE_ERR = "reservation.code.error.nonexistent";
    public static final String RESERVATION_CANCELING_FAILURE_ERR_MSG = "reservation.cancel.error";
    public static final String INADMISSIBLE_ACTION_ATTEMPT = "action.attempt.inadmissible";
    public static final String ACTION_FORBIDDEN_CAUSE_MSG="action.forbidden.cause";
    public static final String NONEXISTENT_FILM_ID = "film.id.error.nonexistent";
    public static final String INTERNAL_ERROR = "error.internal";
    public static final String INVALID_DATE_ERR = "date.error.invalid";

    public MessageManager(){
        resourceBundle = ResourceBundle.getBundle("messages");
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }

}
