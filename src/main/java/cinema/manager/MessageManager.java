package cinema.manager;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Created by Tory on 24.04.2017.
 */

public class MessageManager {

    private ResourceBundle resourceBundle;
    public static final String NONEXISTENT_SEANCE_CODE_MSG_P1 = "NONEXISTENT_SEANCE_CODE_MSG_P1";
    public static final String NONEXISTENT_SEANCE_CODE_MSG_P2 = "NONEXISTENT_SEANCE_CODE_MSG_P2";
    public static final String NONEXISTENT_SEAT_ERR_MSG_P1 = "NONEXISTENT_SEAT_ERR_MSG_P1";
    public static final String NONEXISTENT_SEAT_ERR_MSG_P2 = "NONEXISTENT_SEAT_ERR_MSG_P2";
    public static final String TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG_P1 = "TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG_P1";
    public static final String TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG_P2 = "TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR_MSG_P2";
    public static final String NONEXISTENT_BRONIKA_UID_ERR_MSG_P1="NONEXISTENT_BRONIKA_UID_ERR_MSG_P1";
    public static final String NONEXISTENT_BRONIKA_UID_ERR_MSG_P2="NONEXISTENT_BRONIKA_UID_ERR_MSG_P2";
    public static final String RESERVATION_CANCELING_FAILURE_ERR_MSG="RESERVATION_CANCELING_FAILURE_ERR_MSG";

    public MessageManager(){
        resourceBundle = ResourceBundle.getBundle("messages");
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }

}
