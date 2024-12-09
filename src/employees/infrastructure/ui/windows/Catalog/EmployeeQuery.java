package employees.infrastructure.ui.windows.Catalog;

import java.util.ArrayList;

import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;
import employees.infrastructure.ui.components.EmployeeTable;
import ui.components.Menu;
import employees.application.searchAll.EmployeesInHotelFinder;
import employees.domain.Employee;
import employees.domain.EmployeeRepository;

import javax.swing.JOptionPane;

import db.infrastructure.GetMySQLConnection;

public class EmployeeQuery {
  ArrayList<Employee> employees = new ArrayList<Employee>();

  private void fetchEmployeesFromHotel(String hotelId) {
    EmployeeRepository employeeRepository = new MySQLEmployeeRepositoryImpl(GetMySQLConnection.getConnection());
    EmployeesInHotelFinder employeesInHotelFinder = new EmployeesInHotelFinder(employeeRepository);

    int hotelIdInt;
    try {
      hotelIdInt = Integer.parseInt(hotelId);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "El ID del hotel debe ser un número");
      return;
    }

    employees = employeesInHotelFinder.run(hotelIdInt);
  }

  public void show() {
    Menu selectedHotelOrigin = new Menu("Selecciona el hotel de origen", 200, 400);

    // get hotel id
    String hotelId = JOptionPane.showInputDialog("Ingrese el ID del hotel");

    // validate hotel id
    if (hotelId == null) {
      // show info message
      JOptionPane.showMessageDialog(null, "No se ingreso un ID de hotel");
      return;
    }

    // search employees from hotel id using finder
    fetchEmployeesFromHotel(hotelId);

    // show employees
    if (employees.size() == 0) {
      JOptionPane.showMessageDialog(null, "No se encontraron empleados en el hotel con ID " + hotelId);
    } else {
      // show employees in table
      EmployeeTable employeeTable = new EmployeeTable("Empleados", 400, 400);
      employeeTable.setEmployees(employees);
      employeeTable.show();
    }

    selectedHotelOrigin.show();
  }
}
