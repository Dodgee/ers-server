package uk.ac.aston.jonesja1.ers.service.employee;

import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Employee;

import java.util.List;

@Service
public interface EmployeeService {

    /**
     * Enroll an Employee to use the service.
     * @param employee the employee who is enrolling.
     */
    Employee enroll(Employee employee);

    Employee find(String id);

    List<Employee> all();

    Employee save(Employee employee);
}
