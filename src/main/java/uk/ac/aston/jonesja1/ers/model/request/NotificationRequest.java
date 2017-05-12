package uk.ac.aston.jonesja1.ers.model.request;

import uk.ac.aston.jonesja1.ers.model.SystemState;

public class NotificationRequest {

    private String site;
    private SystemState state;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public SystemState getState() {
        return state;
    }

    public void setState(SystemState state) {
        this.state = state;
    }

}
