package uk.ac.aston.jonesja1.ers.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRiskProfiles {

    private List<EmployeeRiskProfile> employeeRiskProfiles = new ArrayList<>();

    public List<EmployeeRiskProfile> getEmployeeRiskProfiles() {
        return employeeRiskProfiles;
    }

    public void setEmployeeRiskProfiles(List<EmployeeRiskProfile> employeeRiskProfiles) {
        this.employeeRiskProfiles = employeeRiskProfiles;
    }
}
