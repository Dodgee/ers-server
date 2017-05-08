package uk.ac.aston.jonesja1.ers.model;

public class Employee {

    /**
     * ID given to an Employee by to company.
     */
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
