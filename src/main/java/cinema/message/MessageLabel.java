package cinema.message;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Created by Tory on 24.04.2017.
 */

public enum MessageLabel {

    SEANCES_NOT_FOUND_FOR_FILM_ID_ERR("seance.error.notfound.byfilm"),
    SEANCES_NOT_FOUND_FOR_DATE_ERR("seance.error.notfound.bydate"),
    SEANCES_NOT_FOUND_FOR_DATE_FOR_CINEMA_ID_ERR("seance.error.notfound.bydate.bycinema"),
    TRYING_RESERVE_ALREADY_RESERVED_SEATS_ERR("seat.error.alreadyreserved"),
    NONEXISTENT_RESERVATION_CODE_ERR("reservation.code.error.nonexistent"),
    INADMISSIBLE_ACTION_ATTEMPT_ERR("action.attempt.inadmissible"),
    ACTION_FORBIDDEN_CAUSE_INFO("action.forbidden.cause"),
    NONEXISTENT_SEANCE_ID_ERR("seance.id.error.nonexistent"),
    INTERNAL_ERROR_ERR("error.internal"),
    NONEXISTENT_USER_LOGIN_ERR("user.error.notfound.bylogin"),
    INVALID_DATE_ERR("date.error.invalid");

    private String label;

    MessageLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
