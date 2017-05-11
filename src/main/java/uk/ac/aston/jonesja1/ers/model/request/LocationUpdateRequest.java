package uk.ac.aston.jonesja1.ers.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationUpdateRequest {

    /**
     * same as employee.id
     */
    @NotNull
    private String id;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    public LocationUpdateRequest(String id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationUpdateRequest() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
