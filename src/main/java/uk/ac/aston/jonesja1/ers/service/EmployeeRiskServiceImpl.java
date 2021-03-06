package uk.ac.aston.jonesja1.ers.service;

import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.*;
import uk.ac.aston.jonesja1.ers.repository.EmployeeRiskLevelRepository;
import uk.ac.aston.jonesja1.ers.service.state.StateService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeRiskServiceImpl implements EmployeeRiskService {

    /**
     * Configurable distance that employee's have to be from an emergency to be deemed HIGH or LOW risk.
     */
    @Value("${ers.server.risk.distance.min}")
    private double minimumDistanceFromRisk;

    @Autowired
    private EmployeeRiskLevelRepository employeeRiskLevelRepository;

    @Autowired
    private StateService stateService;

    @Override
    public EmployeeRiskProfiles getAllEmployeeRiskLevels() {
        List<EmployeeRiskProfile> riskLevels = employeeRiskLevelRepository.findAll();
        EmployeeRiskProfiles allLevels = new EmployeeRiskProfiles();
        allLevels.getEmployeeRiskProfiles().addAll(riskLevels);
        return allLevels;
    }

    @Override
    public EmployeeRiskProfiles getHighEmployeeRiskLevels() {
        List<EmployeeRiskProfile> riskLevels = employeeRiskLevelRepository.findAllByRiskLevelEquals(RiskLevel.HIGH);
        EmployeeRiskProfiles highRiskLevels = new EmployeeRiskProfiles();
        highRiskLevels.getEmployeeRiskProfiles().addAll(riskLevels);
        return highRiskLevels;
    }

    @Override
    public EmployeeRiskProfiles getLowEmployeeRiskLevels() {
        List<EmployeeRiskProfile> riskLevels = employeeRiskLevelRepository.findAllByRiskLevelEquals(RiskLevel.LOW);
        EmployeeRiskProfiles lowRiskLevels = new EmployeeRiskProfiles();
        lowRiskLevels.getEmployeeRiskProfiles().addAll(riskLevels);
        return lowRiskLevels;
    }

    /**
     * Update an Employee's Risk Level based on their current location.
     * Assigns a Risk Level see {@link RiskLevel} by calculating their current distance from the
     * site having an EMERGENCY.
     * @param locationUpdate the location update from the employee.
     */
    @Override
    public void updateEmployeeRiskLevel(LocationUpdate locationUpdate) {
        Site currentSite = stateService.currentSite();
        if (currentSite != null) {
            EmployeeRiskProfile employeeRiskProfile = calculateRiskLevel(currentSite.getSiteLocation(), locationUpdate.getLocation());
            employeeRiskProfile.setEmployee(locationUpdate.getEmployee());
            employeeRiskLevelRepository.save(employeeRiskProfile);
        }
    }

    private EmployeeRiskProfile calculateRiskLevel(Location eventLocation, Location employeeLocation) {
        GeodeticCalculator calculator = new GeodeticCalculator(DefaultGeographicCRS.WGS84);
        calculator.setStartingGeographicPoint(eventLocation.getLongitude().doubleValue(), eventLocation.getLatitude().doubleValue());
        calculator.setDestinationGeographicPoint(employeeLocation.getLongitude().doubleValue(), employeeLocation.getLatitude().doubleValue());
        double displacement = calculator.getOrthodromicDistance();

        EmployeeRiskProfile riskLevel = new EmployeeRiskProfile();
        riskLevel.setDistance(BigDecimal.valueOf(displacement));
        riskLevel.setUpdatedAt(LocalDateTime.now());
        riskLevel.setRiskLevel(generateRiskLevel(displacement));
        riskLevel.setLastKnownLocation(employeeLocation);
        return riskLevel;
    }

    private RiskLevel generateRiskLevel(double distance) {
        if (distance < getMinimumDistanceFromRisk()) {
            return RiskLevel.HIGH;
        }
        return RiskLevel.LOW;
    }

    public double getMinimumDistanceFromRisk() {
        return minimumDistanceFromRisk;
    }

    public void setMinimumDistanceFromRisk(double minimumDistanceFromRisk) {
        this.minimumDistanceFromRisk = minimumDistanceFromRisk;
    }

    /**
     * Delete all EmployeeRiskProfiles
     * Should be called when the service enters CALM mode ready for fresh updates on the next EMERGENCY.
     */
    @Override
    public void deleteAll() {
        employeeRiskLevelRepository.deleteAll();
    }
}
