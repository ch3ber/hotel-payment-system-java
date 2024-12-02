package employees.infrastructure.ui.components;

import java.util.ArrayList;

import employees.domain.Employee;
import employees.domain.EmployeeRepository;
import ui.components.Table;

public class EmployeeTable {
  private static Table<Employee> employeeTable;
  private static String[] columnNames;

  public static void setColumnNames(String[] columnNames) {
    EmployeeTable.columnNames = columnNames;
  }

  public static void loadEmployeesFrom(EmployeeRepository employeeRepository) {
    ArrayList<Employee> employees = employeeRepository.searchAll();

    employeeTable = new Table<>(employees, columnNames, employee -> new Object[] {
        employee.getId(), employee.getName(), "Fixed position from EmployeeTable"
    });
  }

  public static void show() {
    employeeTable.show();
  }
}
