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

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public EmployeeRiskLevels all() {
        return employeeRiskService.getAllEmployeeRiskLevels();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/high")
    public EmployeeRiskLevels high() {
        return employeeRiskService.getHighEmployeeRiskLevels();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/low")
    public EmployeeRiskLevels low() {
        return employeeRiskService.getLowEmployeeRiskLevels();
    }

}
