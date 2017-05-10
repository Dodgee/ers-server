package uk.ac.aston.jonesja1.ers.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.ac.aston.jonesja1.ers.model.Employee;
import uk.ac.aston.jonesja1.ers.model.LocationUpdate;
import uk.ac.aston.jonesja1.ers.model.request.LocationUpdateRequest;
import uk.ac.aston.jonesja1.ers.service.EmployeeRiskService;
import uk.ac.aston.jonesja1.ers.service.employee.EmployeeService;


@RestController
@RequestMapping("/update")
public class EmployeeLocationUpdateController {

    Logger logger = LoggerFactory.getLogger(EmployeeLocationUpdateController.class);

    @Autowired
    private EmployeeRiskService employeeRiskService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void update(@RequestBody LocationUpdateRequest locationUpdateRequest) {
        logger.debug("Received Update Request for Employee {}", locationUpdateRequest.getEmployeeId());
        Employee employee = employeeService.find(locationUpdateRequest.getEmployeeId());
        if (employee == null) {
            logger.error("Employee {} is not enrolled to use this system.", locationUpdateRequest.getEmployeeId());
        } else {
            LocationUpdate locationUpdate = new LocationUpdate(employee, locationUpdateRequest.getLocation());
            employeeRiskService.calculateEmployeeRiskLevel(locationUpdate);
            logger.debug("Processed Update Request for Employee {}", locationUpdateRequest.getEmployeeId());
        }
    }
}
