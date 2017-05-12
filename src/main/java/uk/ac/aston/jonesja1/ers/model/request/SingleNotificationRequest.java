package uk.ac.aston.jonesja1.ers.model.request;

public class SingleNotificationRequest extends NotificationRequest {

    private String connectionToken;

    public String getConnectionToken() {
        return connectionToken;
    }

    public void setConnectionToken(String connectionToken) {
        this.connectionToken = connectionToken;
    }
}
