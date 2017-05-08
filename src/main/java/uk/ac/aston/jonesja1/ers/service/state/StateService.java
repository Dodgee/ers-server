package uk.ac.aston.jonesja1.ers.service.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.ac.aston.jonesja1.ers.model.SystemState;

@Service
public class StateService {

    Logger logger = LoggerFactory.getLogger(StateService.class);

    private final RestTemplate restTemplate;

    private SystemState currentState;

    public StateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
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
                logger.info("CALM MODE ACTIVATED: NOTIFY DEVICES ENTERED CALM MODE");
                break;
            case EMERGENCY:
                logger.info("EMERGENCY MODE ACTIVATED: NOTIFY DEVICES ENTERED EMERGENCY MODE");
                break;
        }
        currentState = systemState;
    }

}
