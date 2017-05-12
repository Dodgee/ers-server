package uk.ac.aston.jonesja1.ers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StateService stateService;

    @Autowired
    private SiteService siteService;

    @RequestMapping(method = RequestMethod.GET, value = "/{siteKey}/{id}")
    public void notify(@PathVariable String siteKey, @PathVariable String id) {
        logger.info("Received Notify Request for {}", id);
        Site site = siteService.findByKey(siteKey);
        if (site != null) {
            Employee employee = employeeService.find(id);
            if (employee != null) {
                logger.info("Sending Notification to {}", employee.getConnectionDetails());
                SingleNotificationRequest notificationRequest = new SingleNotificationRequest();
                notificationRequest.setConnectionToken(employee.getConnectionDetails());
                notificationRequest.setSite(site.getSiteName());
                notificationRequest.setState(stateService.currentState());
                deviceNotificationService.notifyDevice(notificationRequest);
            }
        } else {
            logger.warn("Site {} does not exist.", siteKey);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{siteKey}/")
    public void notifyAll(@PathVariable String siteKey) {
        logger.info("Received Notify Request to all devices.");
        Site site = siteService.findByKey(siteKey);
        if (site != null) {
            logger.info("Sending Notification to all");
            NotificationRequest notificationRequest = new NotificationRequest();
            notificationRequest.setSite(site.getSiteName());
            notificationRequest.setState(stateService.currentState());
            deviceNotificationService.notifyAll(notificationRequest);
        } else {
            logger.warn("Site {} does not exist.", siteKey);
        }
    }
}
