package uk.ac.aston.jonesja1.ers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.aston.jonesja1.ers.model.Site;

/**
 * MongoDB repository for Site model class.
 */
public interface SiteRepository extends MongoRepository<Site, String> {

    Site findByKey(String key);

}
