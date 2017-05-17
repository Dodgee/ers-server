package uk.ac.aston.jonesja1.ers.model.request;

public class SingleNotificationRequest {

    private String connectionToken;

    private String message;

    public String getConnectionToken() {
        return connectionToken;
    }

    public void setConnectionToken(String connectionToken) {
        this.connectionToken = connectionToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
