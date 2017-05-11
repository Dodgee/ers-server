package uk.ac.aston.jonesja1.ers.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.Employee;
import uk.ac.aston.jonesja1.ers.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee enroll(Employee employee) {
        if (employee != null) {
            return employeeRepository.save(employee);
        }
        return employee;
    }

    public Employee find(String id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> all() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

}
