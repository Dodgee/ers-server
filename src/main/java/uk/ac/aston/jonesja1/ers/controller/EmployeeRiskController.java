package uk.ac.aston.jonesja1.ers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.aston.jonesja1.ers.model.EmployeeRiskLevels;
import uk.ac.aston.jonesja1.ers.service.EmployeeRiskService;

@RestController
@RequestMapping("/risk")
public class EmployeeRiskController {

    @Autowired
    private EmployeeRiskService employeeRiskService;

    /**
     * Get all the employee risk levels in the system.
     * @return all the employeeRiskLevels
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public EmployeeRiskLevels all() {
        return employeeRiskService.getAllEmployeeRiskLevels();
    }

    /**
     * Get all the employee risk levels in the system which are classed as HIGH risk.
     * @return all the employeeRiskLevels classed as HIGH risk.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/high")
    public EmployeeRiskLevels high() {
        return employeeRiskService.getHighEmployeeRiskLevels();
    }

    /**
     * Get all the employee risk levels in the system which are classed as LOW risk.
     * @return all the employeeRiskLevels classed as LOW risk.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/low")
    public EmployeeRiskLevels low() {
        return employeeRiskService.getLowEmployeeRiskLevels();
    }

}
