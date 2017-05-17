package uk.ac.aston.jonesja1.ers.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.aston.jonesja1.ers.model.Employee;
import uk.ac.aston.jonesja1.ers.model.Location;
import uk.ac.aston.jonesja1.ers.model.LocationUpdate;
import uk.ac.aston.jonesja1.ers.model.SystemState;
import uk.ac.aston.jonesja1.ers.model.request.LocationUpdateRequest;
import uk.ac.aston.jonesja1.ers.service.EmployeeRiskService;
import uk.ac.aston.jonesja1.ers.service.employee.EmployeeService;
import uk.ac.aston.jonesja1.ers.service.state.StateService;

import javax.validation.Valid;
import java.math.BigDecimal;


@RestController
@RequestMapping("/update")
public class EmployeeLocationUpdateController {

    Logger logger = LoggerFactory.getLogger(EmployeeLocationUpdateController.class);

    @Autowired
    private EmployeeRiskService employeeRiskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StateService stateService;

    /**
     * POST a LocationUpdateRequest from an employee's enrolled device.
     * @param locationUpdateRequest the request object.
     * @return http response. 202 if successful. 405 if system is now accepting requests.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@Valid @RequestBody LocationUpdateRequest locationUpdateRequest) {
        logger.info("Received Update Request for Employee {}", locationUpdateRequest.getId());
        //if the system is not in EMERGENCY state then do not accept the request. response with a 405 response.
        if (stateService.currentState() == SystemState.EMERGENCY) {
            Employee employee = employeeService.find(locationUpdateRequest.getId());
            if (employee == null) {
                logger.error("Employee {} is not enrolled to use this system.", locationUpdateRequest.getId());
            } else {
                LocationUpdate locationUpdate = new LocationUpdate(
                        employee,
                        new Location(
                                BigDecimal.valueOf(locationUpdateRequest.getLongitude()),
                                BigDecimal.valueOf(locationUpdateRequest.getLatitude())
                        )
                );
                employeeRiskService.updateEmployeeRiskLevel(locationUpdate);
                logger.info("Processed Update Request for Employee {}", locationUpdateRequest.getId());
                return new ResponseEntity("Accepted", HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity("System is in CALM state. Will not accept requests.", HttpStatus.METHOD_NOT_ALLOWED);
    }

}
