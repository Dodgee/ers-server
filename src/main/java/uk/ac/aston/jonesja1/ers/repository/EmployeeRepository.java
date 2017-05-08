package uk.ac.aston.jonesja1.ers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.aston.jonesja1.ers.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

}
