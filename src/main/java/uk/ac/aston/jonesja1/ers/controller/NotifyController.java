package uk.ac.aston.jonesja1.ers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.ac.aston.jonesja1.ers.model.Employee;
import uk.ac.aston.jonesja1.ers.model.Site;
import uk.ac.aston.jonesja1.ers.model.request.NotificationRequest;
import uk.ac.aston.jonesja1.ers.model.request.SingleNotificationRequest;
import uk.ac.aston.jonesja1.ers.service.SiteService;
import uk.ac.aston.jonesja1.ers.service.employee.EmployeeService;
import uk.ac.aston.jonesja1.ers.service.notification.DeviceNotificationService;
import uk.ac.aston.jonesja1.ers.service.state.StateService;

@RestController
@RequestMapping("/notify")
public class NotifyController {

    Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private DeviceNotificationService deviceNotificationService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Send a message to a single employee.
     * @param id the unique id of the employee who is to be notified.
     * @param message the message to send.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/employee/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void sendMessage(@PathVariable String id, @RequestBody String message) {
        logger.info("Received Notify Request for Employee {}", id);
        logger.debug("Message {}", message);
        Employee employee = employeeService.find(id);
        if (employee != null) {
            SingleNotificationRequest request = new SingleNotificationRequest();
            request.setMessage(message);
            request.setConnectionToken(employee.getConnectionDetails());
            deviceNotificationService.notifyDevice(request);
            logger.info("Sent Message to Employee {}", id);
        }
    }

}
