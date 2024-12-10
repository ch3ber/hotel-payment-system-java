package employees.infrastructure.ui.windows.Catalog;

import javax.swing.JOptionPane;

import db.infrastructure.GetMySQLConnection;

import employees.domain.Employee;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;

public class RegisterEmployee {
  Employee employee;

  private boolean saveEmployee(Employee payloadEmployee) {
    MySQLEmployeeRepositoryImpl employeeRepository = new MySQLEmployeeRepositoryImpl(
        GetMySQLConnection.getConnection());
    return employeeRepository.save(payloadEmployee);
  }

  public void show() {
    // Input employee data
    String name = JOptionPane.showInputDialog("Enter Employee Name:");

    String type = JOptionPane.showInputDialog("Enter Employee Type:");

    String salaryStr = JOptionPane.showInputDialog("Enter Employee Salary:");
    double salary = Double.parseDouble(salaryStr);

    String hotelIdStr = JOptionPane.showInputDialog("Enter Hotel ID:");
    int hotelId = Integer.parseInt(hotelIdStr);

    // Create new employee object
    Employee newEmployee = new Employee(0, name, salary, type, hotelId);

    // Save employee in the database
    if (!saveEmployee(newEmployee)) {
      JOptionPane.showMessageDialog(null, "Error saving employee");
      return;
    }

    // Show new employee data
    String message = "Employee saved successfully:\n" +
        "Name: " + newEmployee.getName() + "\n" +
        "Type: " + newEmployee.getType() + "\n" +
        "Salary: " + newEmployee.getSalary() + "\n" +
        "Hotel ID: " + newEmployee.getHotelId();
    JOptionPane.showMessageDialog(null, message);
  }
}
