package employees.infrastructure.ui.windows.Catalog;

import javax.swing.JOptionPane;

import db.infrastructure.GetMySQLConnection;

import employees.domain.Employee;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;

public class UpdateEmployee {
  Employee employee;

  private boolean updateEmployee(Employee payloadEmployee) {
    MySQLEmployeeRepositoryImpl employeeRepository = new MySQLEmployeeRepositoryImpl(
        GetMySQLConnection.getConnection());
    return employeeRepository.update(payloadEmployee);
  }

  public void show() {
    // Input employee data
    String idStr = JOptionPane.showInputDialog("Enter Employee ID to update:");
    int id = Integer.parseInt(idStr);

    String name = JOptionPane.showInputDialog("Enter new Employee Name:");

    String type = JOptionPane.showInputDialog("Enter new Employee Type:");

    String salaryStr = JOptionPane.showInputDialog("Enter new Employee Salary:");
    double salary = Double.parseDouble(salaryStr);

    String hotelIdStr = JOptionPane.showInputDialog("Enter new Hotel ID:");
    int hotelId = Integer.parseInt(hotelIdStr);

    // Create updated employee object
    // int id, String name, double salary, String type, int hotelId
    Employee updatedEmployee = new Employee(id, name, salary, type, hotelId);

    // Update employee in the database
    if (!updateEmployee(updatedEmployee)) {
      JOptionPane.showMessageDialog(null, "Error updating employee");
      return;
    }

    // Show new employee data
    String message = "Employee updated successfully:\n" +
        "ID: " + updatedEmployee.getId() + "\n" +
        "Name: " + updatedEmployee.getName() + "\n" +
        "Type: " + updatedEmployee.getType() + "\n" +
        "Salary: " + updatedEmployee.getSalary() + "\n" +
        "Hotel ID: " + updatedEmployee.getHotelId();
    JOptionPane.showMessageDialog(null, message);
  }
}
