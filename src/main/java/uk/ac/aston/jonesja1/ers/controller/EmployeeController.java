package uk.ac.aston.jonesja1.ers.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.aston.jonesja1.ers.model.Employee;

@RestController
public class EmployeeController {

    @RequestMapping("/{id}")
    public Employee employee() {
        return new Employee();
    }

}
