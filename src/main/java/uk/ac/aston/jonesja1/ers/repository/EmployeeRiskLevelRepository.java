package uk.ac.aston.jonesja1.ers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.aston.jonesja1.ers.model.EmployeeRiskLevel;
import uk.ac.aston.jonesja1.ers.model.RiskLevel;

import java.util.List;

public interface EmployeeRiskLevelRepository extends MongoRepository<EmployeeRiskLevel, Integer> {

    List<EmployeeRiskLevel> findAllByRiskLevelEquals(RiskLevel riskLevel);
}
