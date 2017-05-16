package uk.ac.aston.jonesja1.ers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Location;
import uk.ac.aston.jonesja1.ers.model.Site;
import uk.ac.aston.jonesja1.ers.repository.SiteRepository;

import javax.annotation.PostConstruct;

import java.util.List;

import static uk.ac.aston.jonesja1.ers.constants.Sites.*;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public SiteService() {

    }

    public Site findByKey(String key) {
        return siteRepository.findByKey(key);
    }

    /**
     * Initialise the repository with default sites.
     */
    @PostConstruct
    public void init() {
        createIfNotExists(ASTON_UNIVERSITY_MAIN_BUILDING_KEY, ASTON_UNIVERSITY_MAIN_BUILDING_NAME, ASTON_UNIVERSITY_MAIN_BUILDING_LOCATION);
        createIfNotExists(CAPGMEINI_ASTON_KEY, CAPGMEINI_ASTON_NAME, CAPGMEINI_ASTON_LOCATION);
    }

    private void createIfNotExists(String key, String locationName, Location location) {
        if (siteRepository.findByKey(key) == null) {
            Site site = new Site();
            site.setKey(key);
            site.setSiteName(locationName);
            site.setSiteLocation(location);
            siteRepository.save(site);
        }
    }

    public List<Site> findAll() {
        return siteRepository.findAll();
    }
}
