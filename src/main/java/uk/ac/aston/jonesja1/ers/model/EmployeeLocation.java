package uk.ac.aston.jonesja1.ers.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * An update from an employees device.
 * Contains information on who the update is for and the
 * location details.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeLocation {

    @Id
    private String id;

    private Integer employeeID;

    private Location location;

    private LocalDate dateCreated;

    @JsonCreator
    public EmployeeLocation(Integer employeeID, Location location) {
        this.employeeID = employeeID;
        this.location = location;
        dateCreated = LocalDate.now();
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
