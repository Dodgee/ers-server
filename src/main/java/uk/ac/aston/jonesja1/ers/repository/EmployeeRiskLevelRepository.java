package uk.ac.aston.jonesja1.ers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.aston.jonesja1.ers.model.EmployeeRiskProfile;
import uk.ac.aston.jonesja1.ers.model.RiskLevel;

import java.util.List;

/**
 * MongoDB repository for EmployeeRiskProfile model class.
 */
public interface EmployeeRiskLevelRepository extends MongoRepository<EmployeeRiskProfile, Integer> {

    List<EmployeeRiskProfile> findAllByRiskLevelEquals(RiskLevel riskLevel);
}
