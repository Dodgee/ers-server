package uk.ac.aston.jonesja1.ers.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRiskLevels {

    private List<EmployeeRiskLevel> employeeRiskLevels = new ArrayList<>();

    public List<EmployeeRiskLevel> getEmployeeRiskLevels() {
        return employeeRiskLevels;
    }

    public void setEmployeeRiskLevels(List<EmployeeRiskLevel> employeeRiskLevels) {
        this.employeeRiskLevels = employeeRiskLevels;
    }
}
