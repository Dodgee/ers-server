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

    /**
     * Enrol an Employee with the system.
     * @param employee the employee details to enrol.
     * @return the unique id assigned to the employee and http response 400 on bad request. 201 on successfully enrolled.
     */
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

    /**
     * Reauthenticate an employee with the system if their Firebase token has been refreshed.
     * See https://firebase.google.com/docs/cloud-messaging/android/client#retrieve-the-current-registration-token
     * for more info on Firebase tokens.
     * @param token the employees firebase token.
     * @param id the unique id given to the client device on enrollment.
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/reauthenticate/{id}")
    public ResponseEntity reauthenticate(
            @Valid @RequestBody final String token,
            @PathVariable String id) {
        logger.info("ReAuth Request Received: {}", id);
        Employee enrolled = employeeService.find(id);
        if (enrolled != null) {
            enrolled.setConnectionDetails(token);
            employeeService.save(enrolled);
            return new ResponseEntity<>("Reauthenticated.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee is not enrolled. Please enrol again.", HttpStatus.NOT_FOUND);
    }

}
