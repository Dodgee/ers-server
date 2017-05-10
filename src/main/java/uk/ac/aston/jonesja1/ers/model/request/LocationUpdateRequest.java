package uk.ac.aston.jonesja1.ers.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import uk.ac.aston.jonesja1.ers.model.Location;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationUpdateRequest {

    /**
     * same as employee.id
     */
    @NotNull
    private String employeeId;

    @NotNull
    @Valid
    private Location location;

    public LocationUpdateRequest(String employeeID, Location location) {
        this.employeeId = employeeID;
        this.location = location;
    }

    public LocationUpdateRequest() {

    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeID) {
        this.employeeId = employeeID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
