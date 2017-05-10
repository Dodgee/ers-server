package uk.ac.aston.jonesja1.ers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.aston.jonesja1.ers.model.EmployeeLocation;
import uk.ac.aston.jonesja1.ers.service.update.EmployeeLocationUpdateService;

@RestController
@RequestMapping("/update")
public class EmployeeLocationUpdateController {

    @Autowired
    private EmployeeLocationUpdateService employeeLocationUpdateService;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void update(@RequestBody EmployeeLocation employeeLocation) {
        employeeLocationUpdateService.update(employeeLocation);
    }
}
