package uk.ac.aston.jonesja1.ers.service.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Site;
import uk.ac.aston.jonesja1.ers.model.SystemState;
import uk.ac.aston.jonesja1.ers.model.request.NotificationRequest;
import uk.ac.aston.jonesja1.ers.service.EmployeeRiskService;
import uk.ac.aston.jonesja1.ers.service.notification.DeviceNotificationService;

@Service
public class StateService {

    Logger logger = LoggerFactory.getLogger(StateService.class);

    @Autowired
    private DeviceNotificationService deviceNotificationService;

    @Autowired
    private EmployeeRiskService employeeRiskService;

    private SystemState currentState;

    private Site currentSite;

    public StateService() {
        currentState = SystemState.CALM;
        currentSite = null;
    }

    public SystemState currentState() {
        return currentState;
    }

    public Site currentSite() {
        return currentSite;
    }

    /**
     * Update Observers (clients) that the systemState has changed so they can act accordingly.
     * @param systemState the systemState the application is now in.
     */
    public void updateSystemState(SystemState systemState) {
        logger.info("--- {} MODE ACTIVATED ---", systemState.toString());
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setState(systemState);
        notificationRequest.setSite(currentSite().getSiteName());

        currentState = systemState;
        if(systemState == SystemState.CALM) {
            employeeRiskService.deleteAll();
            currentSite = null;
        }

        logger.info("NOTIFYING DEVICES ENTERED {} MODE", systemState.toString());
        deviceNotificationService.notifyAll(notificationRequest);
    }

    public void updateCurrentSite(Site site) {
        this.currentSite = site;
    }

}
