package uk.ac.aston.jonesja1.ers.service.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.EmployeeLocation;
import uk.ac.aston.jonesja1.ers.repository.EmployeeLocationRepository;

@Service
public class EmployeeLocationEmployeeLocationUpdateServiceImpl implements EmployeeLocationUpdateService {

    @Autowired
    private EmployeeLocationRepository employeeLocationRepository;

    @Override
    public void update(EmployeeLocation employeeLocation) {
        employeeLocationRepository.save(employeeLocation);
    }
}
