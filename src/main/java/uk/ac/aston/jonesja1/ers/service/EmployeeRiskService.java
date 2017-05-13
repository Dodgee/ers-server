package uk.ac.aston.jonesja1.ers.service;

import org.geotools.referencing.GeodeticCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.*;
import uk.ac.aston.jonesja1.ers.repository.EmployeeRiskLevelRepository;
import uk.ac.aston.jonesja1.ers.service.state.StateService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeRiskService {

    @Autowired
    private EmployeeRiskLevelRepository employeeRiskLevelRepository;

    @Autowired
    private StateService stateService;

    public EmployeeRiskLevels getAllEmployeeRiskLevels() {
        List<EmployeeRiskLevel> riskLevels = employeeRiskLevelRepository.findAll();
        EmployeeRiskLevels allLevels = new EmployeeRiskLevels();
        allLevels.getEmployeeRiskLevels().addAll(riskLevels);
        return allLevels;
    }

    public EmployeeRiskLevels getHighEmployeeRiskLevels() {
        List<EmployeeRiskLevel> riskLevels = employeeRiskLevelRepository.findAllByRiskLevelEquals(RiskLevel.HIGH);
        EmployeeRiskLevels highRiskLevels = new EmployeeRiskLevels();
        highRiskLevels.getEmployeeRiskLevels().addAll(riskLevels);
        return highRiskLevels;
    }

    public EmployeeRiskLevels getLowEmployeeRiskLevels() {
        List<EmployeeRiskLevel> riskLevels = employeeRiskLevelRepository.findAllByRiskLevelEquals(RiskLevel.LOW);
        EmployeeRiskLevels lowRiskLevels = new EmployeeRiskLevels();
        lowRiskLevels.getEmployeeRiskLevels().addAll(riskLevels);
        return lowRiskLevels;
    }

    public void calculateEmployeeRiskLevel(LocationUpdate locationUpdate) {
        Site currentSite = stateService.currentSite();
        if (currentSite != null) {
            EmployeeRiskLevel employeeRiskLevel = calculateRiskLevel(currentSite.getSiteLocation(), locationUpdate.getLocation());
            employeeRiskLevel.setEmployee(locationUpdate.getEmployee());
            employeeRiskLevelRepository.save(employeeRiskLevel);
        }
    }

    private EmployeeRiskLevel calculateRiskLevel(Location eventLocation, Location employeeLocation) {
        GeodeticCalculator calculator = new GeodeticCalculator();
        calculator.setStartingGeographicPoint(eventLocation.getLongitude().doubleValue(), eventLocation.getLatitude().doubleValue());
        calculator.setDestinationGeographicPoint(employeeLocation.getLongitude().doubleValue(), employeeLocation.getLatitude().doubleValue());
        double displacement = calculator.getOrthodromicDistance();

        EmployeeRiskLevel riskLevel = new EmployeeRiskLevel();
        riskLevel.setDistance(BigDecimal.valueOf(displacement));
        riskLevel.setDateCreated(LocalDateTime.now());
        riskLevel.setRiskLevel(generateRiskLevel(displacement));
        return riskLevel;
    }

    private RiskLevel generateRiskLevel(double distance) {
        //TODO extract 250 into config or store locations in the database?
        if (distance < 250) {
            return RiskLevel.HIGH;
        }
        return RiskLevel.LOW;
    }
}
