package uk.ac.aston.jonesja1.ers.service.employee;

import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Employee;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //TODO replace with repository
    Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public Employee enroll(Employee employee) {
        if (employee != null) {
            employees.put(employee.getId(), employee);
        }
        return employee;
    }

}
