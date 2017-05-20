package uk.ac.aston.jonesja1.ers.service.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Site;
import uk.ac.aston.jonesja1.ers.model.SystemState;
import uk.ac.aston.jonesja1.ers.model.request.NotificationRequest;
import uk.ac.aston.jonesja1.ers.service.EmployeeRiskServiceImpl;
import uk.ac.aston.jonesja1.ers.service.notification.DeviceNotificationService;

@Service
public class StateServiceImpl implements StateService {

    Logger logger = LoggerFactory.getLogger(StateServiceImpl.class);

    @Autowired
    private DeviceNotificationService deviceNotificationService;

    @Autowired
    private EmployeeRiskServiceImpl employeeRiskService;

    private SystemState currentState;

    private Site currentSite;

    public StateServiceImpl() {
        currentState = SystemState.CALM;
        currentSite = null;
    }

    @Override
    public SystemState currentState() {
        return currentState;
    }

    @Override
    public Site currentSite() {
        return currentSite;
    }

    /**
     * Update Observers (clients) that the systemState has changed so they can act accordingly.
     * @param systemState the systemState the application is now in.
     */
    @Override
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

    @Override
    public void updateCurrentSite(Site site) {
        this.currentSite = site;
    }

}
