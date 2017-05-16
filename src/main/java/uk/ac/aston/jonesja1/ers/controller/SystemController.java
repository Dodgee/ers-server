package uk.ac.aston.jonesja1.ers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.aston.jonesja1.ers.model.Site;
import uk.ac.aston.jonesja1.ers.model.SystemState;
import uk.ac.aston.jonesja1.ers.model.request.NotificationRequest;
import uk.ac.aston.jonesja1.ers.service.SiteService;
import uk.ac.aston.jonesja1.ers.service.notification.DeviceNotificationService;
import uk.ac.aston.jonesja1.ers.service.state.StateService;

import java.util.List;

@RestController
@RequestMapping("/system/")
public class SystemController {

    @Autowired
    private StateService stateService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private DeviceNotificationService deviceNotificationService;

    @RequestMapping(method = RequestMethod.POST, value = "/start/{siteKey}")
    public ResponseEntity<String> start(@PathVariable String siteKey) {
        Site site = siteService.findByKey(siteKey);
        if (site != null) {
            stateService.updateCurrentSite(site);
            stateService.updateSystemState(SystemState.EMERGENCY);
            return new ResponseEntity<String>("Emergency Triggered for Site: " + siteKey, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Site does not exist: " + siteKey, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/stop")
    public ResponseEntity<String> stop() {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setState(SystemState.CALM);
        notificationRequest.setSite(stateService.currentSite().getSiteName());
        stateService.updateSystemState(SystemState.CALM);
        stateService.updateCurrentSite(null);
        deviceNotificationService.notifyAll(notificationRequest);

        return new ResponseEntity<String>("CALM Mode Triggered.", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/state")
    public ResponseEntity<SystemState> currentState() {
        SystemState systemState = stateService.currentState();
        return new ResponseEntity<>(systemState, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/site")
    public ResponseEntity<Site> currentSite() {
        Site site = stateService.currentSite();
        return new ResponseEntity<>(site, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sites")
    public ResponseEntity<List<Site>> sites() {
        List<Site> sites = siteService.findAll();
        return new ResponseEntity<>(sites, HttpStatus.OK);
    }

}
