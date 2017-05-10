package uk.ac.aston.jonesja1.ers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.aston.jonesja1.ers.model.EmployeeLocation;

public interface EmployeeLocationRepository extends MongoRepository<EmployeeLocation, String> {

}
