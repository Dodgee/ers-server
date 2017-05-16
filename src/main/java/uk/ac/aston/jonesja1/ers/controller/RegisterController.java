package uk.ac.aston.jonesja1.ers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.aston.jonesja1.ers.model.Employee;
import uk.ac.aston.jonesja1.ers.service.employee.EmployeeService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/register")
public class RegisterController {

    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.PUT, value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity register(@Valid @RequestBody final Employee employee) {
        logger.info("Register Request Received: {}", employee.getEmployeeId());
        employee.setRegisteredAt(LocalDateTime.now());
        Employee enrolled = employeeService.enroll(employee);
        if (enrolled == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(enrolled.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reauthenticate/{id}")
    public ResponseEntity reauthenticate(
            @Valid @RequestBody final String token,
            @PathVariable String id) {
        logger.info("ReAuth Request Received: {}", id);
        Employee enrolled = employeeService.find(id);
        if (enrolled != null) {
            enrolled.setConnectionDetails(token);
            employeeService.save(enrolled);
            return new ResponseEntity<>("Failed to Register User.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee is not enrolled. Please enrol again.", HttpStatus.NOT_FOUND);
    }

}
