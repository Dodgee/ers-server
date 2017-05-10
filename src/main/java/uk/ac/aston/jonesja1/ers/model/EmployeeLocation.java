package uk.ac.aston.jonesja1.ers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * An update from an employees device.
 * Contains information on who the update is for and the
 * location details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeLocation {

    /**
     * Same as Employee.id
     */
    private String employeeId;

    private Location location;

    public EmployeeLocation(String employeeID, Location location) {
        this.employeeId = employeeID;
        this.location = location;
    }

    public EmployeeLocation() {

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
