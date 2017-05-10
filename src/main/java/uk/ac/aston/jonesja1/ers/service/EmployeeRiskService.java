package uk.ac.aston.jonesja1.ers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.EmployeeLocation;
import uk.ac.aston.jonesja1.ers.model.EmployeeRiskLevel;
import uk.ac.aston.jonesja1.ers.model.EmployeeRiskLevels;
import uk.ac.aston.jonesja1.ers.model.RiskLevel;
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
        EmployeeRiskLevel employeeRiskLevel = new EmployeeRiskLevel();
        employeeRiskLevel.setId(updatedEmployeeLocation.getEmployeeId());
        employeeRiskLevel.setDateCreated(LocalDateTime.now());
        employeeRiskLevel.setDistance(BigDecimal.TEN);
        employeeRiskLevel.setRiskLevel(RiskLevel.LOW);
        //TODO do...
        //get current site
        //work out distance from employee
        //set appropriate risk
        employeeRiskLevelRepository.save(employeeRiskLevel);
    }
}
