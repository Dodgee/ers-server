package uk.ac.aston.jonesja1.ers.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * A Longitude/Latitude representation of a location.
 */
public class Location {

    @NotNull
    private BigDecimal longitude;

    @NotNull
    private BigDecimal latitude;

    public Location(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location() {

    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
