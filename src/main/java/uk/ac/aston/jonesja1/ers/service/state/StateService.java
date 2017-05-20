package uk.ac.aston.jonesja1.ers.service.state;

import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Site;
import uk.ac.aston.jonesja1.ers.model.SystemState;

@Service
public interface StateService {

    SystemState currentState();

    Site currentSite();

    void updateSystemState(SystemState systemState);

    void updateCurrentSite(Site site);

}
