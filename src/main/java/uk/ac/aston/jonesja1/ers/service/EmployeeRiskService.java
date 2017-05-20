package uk.ac.aston.jonesja1.ers.service;

import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.EmployeeRiskProfiles;
import uk.ac.aston.jonesja1.ers.model.LocationUpdate;

@Service
public interface EmployeeRiskService {

    EmployeeRiskProfiles getAllEmployeeRiskLevels();

    EmployeeRiskProfiles getHighEmployeeRiskLevels();

    EmployeeRiskProfiles getLowEmployeeRiskLevels();

    void updateEmployeeRiskLevel(LocationUpdate locationUpdate);

    void deleteAll();

}
