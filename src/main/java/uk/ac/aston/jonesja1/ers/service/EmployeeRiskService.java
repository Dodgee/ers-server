package uk.ac.aston.jonesja1.ers.service;

import org.geotools.referencing.GeodeticCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.constants.Coordinates;
import uk.ac.aston.jonesja1.ers.model.*;
import uk.ac.aston.jonesja1.ers.repository.EmployeeRiskLevelRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeRiskService {

    @Autowired
    private EmployeeRiskLevelRepository employeeRiskLevelRepository;

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

    public void calculateEmployeeRiskLevel(EmployeeLocation updatedEmployeeLocation) {
        EmployeeRiskLevel employeeRiskLevel = calculateRiskLevel(Coordinates.ASTON_UNIVERSITY_MAIN_BUILDING, updatedEmployeeLocation.getLocation());
        employeeRiskLevel.setId(updatedEmployeeLocation.getEmployeeId());
        //TODO get current site instead of defaulting to Aston Main Building
        employeeRiskLevelRepository.save(employeeRiskLevel);
    }

    private EmployeeRiskLevel calculateRiskLevel(Location event, Location point) {
        GeodeticCalculator calculator = new GeodeticCalculator();
        calculator.setStartingGeographicPoint(event.getLongitude().doubleValue(), event.getLatitude().doubleValue());
        calculator.setDestinationGeographicPoint(point.getLongitude().doubleValue(), point.getLatitude().doubleValue());
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
