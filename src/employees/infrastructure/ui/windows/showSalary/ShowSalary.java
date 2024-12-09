package employees.infrastructure.ui.windows.showSalary;

import javax.swing.JOptionPane;

import db.infrastructure.GetMySQLConnection;
import employees.domain.Employee;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;

public class ShowSalary {
  public void show() {
    // input the employee id
    String id = JOptionPane.showInputDialog("Ingrese el ID del empleado");
    if (id == null) {
      return;
    }

    // search the employee
    MySQLEmployeeRepositoryImpl employeeRepository = new MySQLEmployeeRepositoryImpl(
        GetMySQLConnection.getConnection());
    Employee employee = employeeRepository.search(id);

    // show the employee
    if (employee != null) {
      JOptionPane.showMessageDialog(null,
          "El salario de " + employee.getName() + " es de " + employee.getSalary());
    } else {
      JOptionPane.showMessageDialog(null, "No se encontro el empleado con el ID " + id);
    }
  }
}
