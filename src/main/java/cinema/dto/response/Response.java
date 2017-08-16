package cinema.dto.response;

/**
 * Created by Tory on 25.04.2017.
 */
public abstract class Response {
    private String errorMessage;

    private String infoMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }
}
