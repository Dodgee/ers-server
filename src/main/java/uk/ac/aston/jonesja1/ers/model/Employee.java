package uk.ac.aston.jonesja1.ers.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Employee {


    @Id
    private String id;

    /**
     * ID given to an Employee by the company.
     */
    private Integer employeeId;

    /**
     * Employee's name.
     */
    private String name;

    /**
     * Company email address of the employee.
     */
    private String emailAddress;

    private DeviceType deviceType;

    private String connectionDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getConnectionDetails() {
        return connectionDetails;
    }

    public void setConnectionDetails(String connectionDetails) {
        this.connectionDetails = connectionDetails;
    }
}
