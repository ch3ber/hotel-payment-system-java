package employees.domain;

import java.util.ArrayList;

public interface EmployeeRepository {
  void save(Employee employee);

  Employee findById(String id);

  ArrayList<Employee> findAll();

  void update(Employee employee);

  void delete(String id);
}