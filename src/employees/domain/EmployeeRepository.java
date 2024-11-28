package employees.domain;

import java.util.ArrayList;

public interface EmployeeRepository {
  void save(Employee employee);

  Employee search(String id);

  ArrayList<Employee> searchAll();

  void update(Employee employee);

  void delete(String id);
}