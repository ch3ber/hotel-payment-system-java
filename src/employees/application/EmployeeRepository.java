package employees.application;

import java.util.List;

import employees.domain.Employee;

public interface EmployeeRepository {
  void add(Employee employee);

  Employee findById(String id);

  List<Employee> findAll();

  void update(Employee employee);

  void delete(String id);
}