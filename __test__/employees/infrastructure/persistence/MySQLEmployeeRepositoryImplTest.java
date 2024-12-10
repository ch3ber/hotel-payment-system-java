package employees.infrastructure.persistence;

import java.util.ArrayList;

import employees.domain.Employee;
import employees.domain.Manager;
import utils.LoadENV;
import db.infrastructure.MySQLConnection;

public class MySQLEmployeeRepositoryImplTest {
  public static void main(String[] args) {
    LoadENV env = new LoadENV();
    env.load(".properties");

    // Configuración de la conexión
    MySQLConnection connection = new MySQLConnection(env.getProperty("TEST_DB_URI"), env.getProperty("TEST_DB_USER"),
        env.getProperty("TEST_DB_PASS"));
    MySQLEmployeeRepositoryImpl repository = new MySQLEmployeeRepositoryImpl(connection);

    // print test suite info
    System.out.println("Running Test Suite: MySQLEmployeeRepositoryImplTest");

    // Test: Crear un empleado
    System.out.println("Running: Test Save Employee");
    testSave(repository);

    // Test: Buscar todos los empleados
    System.out.println("Running: Test Search All Employees");
    testSearchAll(repository);

    // Test: Buscar empleado por ID
    System.out.println("Running: Test Search Employee by ID");
    testSearchById(repository);

    // Test: Actualizar un empleado
    System.out.println("Running: Test Update Employee");
    testUpdate(repository);

    // Test: Eliminar un empleado
    System.out.println("Running: Test Delete Employee");
    testDelete(repository);
  }

  private static void testSave(MySQLEmployeeRepositoryImpl repository) {
    Manager manager = new Manager(0, "Test Manager", 18000, 5000, 1); // El ID será generado automáticamente
    repository.save(manager);
    System.out.println("\tTest Save Employee: Success");
  }

  private static void testSearchAll(MySQLEmployeeRepositoryImpl repository) {
    ArrayList<Employee> employees = repository.searchAll();
    System.out.println("\tFound " + employees.size() + " employees:");
    for (Employee employee : employees) {
      System.out.println(employee.toString());
    }
  }

  private static void testSearchById(MySQLEmployeeRepositoryImpl repository) {
    Employee employee = repository.search("1"); // Asume que existe un empleado con ID 1
    if (employee != null) {
      System.out.println("\tEmployee Found: " + employee.toString());
    } else {
      System.out.println("\tNo employee found with ID 1");
    }
  }

  private static void testUpdate(MySQLEmployeeRepositoryImpl repository) {
    Manager manager = new Manager(1, "Updated Manager", 20000, 7000, 1); // Actualiza un empleado con ID 1
    boolean isUpdated = repository.update(manager);
    if (isUpdated) {
      System.out.println("\tTest Update Employee: Success");
    } else {
      System.out.println("\tTest Update Employee: Failed");
    }
  }

  private static void testDelete(MySQLEmployeeRepositoryImpl repository) {
    boolean isDeleted = repository.delete("1"); // Elimina un empleado con ID 1
    if (isDeleted) {
      System.out.println("\tTest Delete Employee: Success");
    } else {
      System.out.println("\tTest Delete Employee: Failed");
    }
  }
}
