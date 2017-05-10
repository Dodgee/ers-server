package uk.ac.aston.jonesja1.ers.service.update;

import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.EmployeeLocation;

@Service
public interface EmployeeLocationUpdateService {

    void update(EmployeeLocation employeeLocation);

}
