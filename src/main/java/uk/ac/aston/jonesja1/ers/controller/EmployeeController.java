package uk.ac.aston.jonesja1.ers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.aston.jonesja1.ers.model.Employee;
import uk.ac.aston.jonesja1.ers.service.employee.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Employee employee(@PathVariable String id) {
        return employeeService.find(id);
    }

}
