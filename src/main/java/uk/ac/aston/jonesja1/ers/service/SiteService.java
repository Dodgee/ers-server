package uk.ac.aston.jonesja1.ers.service;

import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Site;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public interface SiteService {

    Site findByKey(String key);

    List<Site> findAll();

}
