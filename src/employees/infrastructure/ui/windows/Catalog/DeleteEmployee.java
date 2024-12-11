package employees.infrastructure.ui.windows.Catalog;

import javax.swing.JOptionPane;

import db.infrastructure.GetMySQLConnection;

import employees.domain.Employee;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;

public class DeleteEmployee {
  Employee employee;

  private boolean deleteEmployee(String id) {
    MySQLEmployeeRepositoryImpl employeeRepository = new MySQLEmployeeRepositoryImpl(
        GetMySQLConnection.getConnection());
    return employeeRepository.delete(id);
  }

  public void show() {
    // Input employee ID
    String id = JOptionPane.showInputDialog("Enter Employee ID to delete:");

    // Delete employee from the database
    if (!deleteEmployee(id)) {
      JOptionPane.showMessageDialog(null, "Error deleting employee");
      return;
    }

    // Show confirmation message
    String message = "Employee deleted successfully:\n" +
        "Employee ID: " + id;
    JOptionPane.showMessageDialog(null, message);
  }
}
