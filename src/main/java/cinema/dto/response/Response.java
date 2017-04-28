package cinema.dto.response;

/**
 * Created by Tory on 25.04.2017.
 */
public abstract class Response {
    String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
