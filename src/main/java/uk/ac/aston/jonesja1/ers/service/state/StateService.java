package uk.ac.aston.jonesja1.ers.service.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.SystemState;
import uk.ac.aston.jonesja1.ers.service.EmployeeRiskService;

@Service
public class StateService {

    Logger logger = LoggerFactory.getLogger(StateService.class);

    private SystemState currentState;

    public StateService() {
        currentState = SystemState.CALM;
    }

    public SystemState currentState() {
        return currentState;
    }

    /**
     * Update Observers that the systemState has changed so they can act accordingly.
     * @param systemState the systemState the application is now in.
     */
    public void update(SystemState systemState) {
        //TODO implement actually updating devices
        switch (systemState) {
            case CALM:
                logger.info("--- CALM MODE ACTIVATED ---");
                logger.info("NOTIFYING DEVICES ENTERED CALM MODE");
                //TODO notify devices
                break;
            case EMERGENCY:
                logger.info("--- EMERGENCY MODE ACTIVATED ---");
                logger.info("NOTIFYING DEVICES ENTERED EMERGENCY MODE");
                //TODO notify devices
                break;
        }
        currentState = systemState;
    }

}
