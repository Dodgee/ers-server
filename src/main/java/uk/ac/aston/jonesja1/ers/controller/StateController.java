package uk.ac.aston.jonesja1.ers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.aston.jonesja1.ers.model.SystemState;
import uk.ac.aston.jonesja1.ers.service.state.StateService;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @RequestMapping(method = RequestMethod.POST, value = "/{systemState}")
    public void update(@PathVariable SystemState systemState) {
        stateService.update(systemState);
    }

    @RequestMapping(method = RequestMethod.GET)
    public SystemState current() {
        return stateService.currentState();
    }

}
