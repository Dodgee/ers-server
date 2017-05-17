package uk.ac.aston.jonesja1.ers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.aston.jonesja1.ers.model.Employee;

/**
 * MongoDB repository for Employee model class.
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
