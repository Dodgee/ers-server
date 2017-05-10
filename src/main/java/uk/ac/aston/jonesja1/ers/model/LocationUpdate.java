package uk.ac.aston.jonesja1.ers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * An update from an employees device.
 * Contains information on who the update is for and the
 * location details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationUpdate {

    private Employee employee;

    private Location location;

    public LocationUpdate(Employee employee, Location location) {
        this.employee = employee;
        this.location = location;
    }

    public LocationUpdate() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
