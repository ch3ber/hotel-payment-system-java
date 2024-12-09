package employees.infrastructure.ui.components;

import java.util.ArrayList;

import employees.domain.Employee;
import ui.components.Table;

public class EmployeeTable extends Table {
  public EmployeeTable(String title, int width, int height) {
    super(title, width, height);
    addColumn("ID", 50);
    addColumn("Nombre", 100);
    addColumn("Tipo", 100);
    addColumn("Salario", 100);
    addColumn("ID Hotel", 50);
  }

  public void setEmployees(ArrayList<Employee> employees) {
    for (Employee employee : employees) {
      addRow(employee.getId(), employee.getName(), employee.getType(), employee.getSalary(), employee.getHotelId());
    }
  }
}
