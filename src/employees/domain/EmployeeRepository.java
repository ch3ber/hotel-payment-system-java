package employees.domain;

import java.util.ArrayList;

public interface EmployeeRepository {
  boolean save(Employee employee);

  Employee search(String id);

  // Employee matching(Criteria criteria);

  ArrayList<Employee> searchAll();

  boolean update(Employee employee);

  boolean delete(String id);
}