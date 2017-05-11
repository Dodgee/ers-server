package uk.ac.aston.jonesja1.ers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.aston.jonesja1.ers.model.Employee;
import uk.ac.aston.jonesja1.ers.service.employee.EmployeeService;
import uk.ac.aston.jonesja1.ers.service.notification.DeviceNotificationService;

@RestController
@RequestMapping("/notify")
public class NotifyController {

    Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private DeviceNotificationService deviceNotificationService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public void notify(@PathVariable String id) {
        logger.info("Received Notify Request for {}", id);
        Employee employee = employeeService.find(id);
        if (employee != null) {
            logger.info("Sending Notification to {}", employee.getConnectionDetails());
            deviceNotificationService.notifyDevice(employee.getConnectionDetails());
        }
    }
}
