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

@RestController
@RequestMapping("/register")
public class RegisterController {

    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.PUT, value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity register(@Valid @RequestBody final Employee employee) {
        logger.info("Register Request Received: {}", employee.getEmployeeId());
        Employee enrolled = employeeService.enroll(employee);
        if (enrolled == null) {
            return new ResponseEntity<>("Failed to Register User.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully Enrolled.", HttpStatus.CREATED);
    }

}
