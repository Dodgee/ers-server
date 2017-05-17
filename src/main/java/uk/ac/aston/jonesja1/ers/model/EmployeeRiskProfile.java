package uk.ac.aston.jonesja1.ers.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class EmployeeRiskProfile {

    @Id
    private String id;

    private Employee employee;

    private RiskLevel riskLevel;

    private BigDecimal distance;

    private LocalDateTime updatedAt;

    private Location lastKnownLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.id = employee.getId();
        this.employee = employee;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public long getLastUpdatedAt() {
        return getUpdatedAt().toEpochSecond(ZoneOffset.UTC);
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }
}
