package employees.application.searchAll;

import java.util.ArrayList;

import employees.domain.Employee;
import employees.domain.EmployeesRepository;

public class EmployeesInHotelFinder {
  private EmployeesRepository employeesRepository;

  public EmployeesInHotelFinder(EmployeesRepository employeesRepository) {
    this.employeesRepository = employeesRepository;
  }

  public ArrayList<Employee> run(int hotelId) {
    ArrayList<Employee> employees = new ArrayList<Employee>();
    for (Employee employee : employeesRepository.searchAll()) {
      if (employee.getHotelId() == hotelId) {
        employees.add(employee);
      }
    }
    return employees;
  }
}
