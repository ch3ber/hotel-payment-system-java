package employees.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.infrastructure.MySQLConnection;

import java.sql.Statement;

import employees.domain.Employee;
import employees.domain.EmployeeRepository;
import employees.domain.Manager;
import employees.domain.Salesperson;
import employees.domain.enums.EmployeeTypes;
import employees.domain.enums.HousekeeperLevels;
import employees.domain.housekeepers.Housekeeper;

public class MySQLEmployeeRepositoryImpl implements EmployeeRepository {
  private MySQLConnection mySQLConnectionAccess;

  public MySQLEmployeeRepositoryImpl(MySQLConnection mySQLConnectionAccess) {
    this.mySQLConnectionAccess = mySQLConnectionAccess;
  }

  @Override
  public void save(Employee employee) {
    mySQLConnectionAccess.openConnection();
    Connection connection = mySQLConnectionAccess.getConnection();
    String templateQuery = "";
    try {
      templateQuery = "INSERT INTO Empleado (nombre, salario_base, tipo, hotel_id) VALUES (?, ?, ?, ?)";
      PreparedStatement query = connection.prepareStatement(templateQuery);

      query.setString(1, employee.getName());
      query.setDouble(2, employee.getSalary());
      query.setString(3, employee.getType());
      query.setInt(4, employee.getHotelId());

      query.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mySQLConnectionAccess.closeConnection();
    }
  }

  @Override
  public Employee search(String id) {
    Employee employee = null;

    String query = "SELECT" +
        "  e.id AS empleado_id," +
        "  e.nombre AS empleado_nombre," +
        "  e.tipo AS tipo_empleado," +
        "  e.salario_base," +
        "  h.nombre AS hotel_nombre," +
        "  h.nivel_estrellas," +
        "  COALESCE(g.bono, 0) AS bono," +
        "  COALESCE(r.nivel_experiencia, '') AS nivel_experiencia," +
        "  COALESCE(r.total_habitaciones, 0) AS total_habitaciones," +
        "  COALESCE(v.comision, 0) AS comision" +
        " FROM" +
        "    Empleado e" +
        " LEFT JOIN" +
        "    Hotel h ON e.hotel_id = h.id" +
        " LEFT JOIN" +
        "    Gerente g ON e.id = g.empleado_id" +
        " LEFT JOIN" +
        "    Recamarera r ON e.id = r.empleado_id" +
        " LEFT JOIN" +
        "    Vendedor v ON e.id = v.empleado_id" +
        " WHERE e.id = " + id;

    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        String name = resultSet.getString("empleado_nombre");
        String type = resultSet.getString("tipo_empleado");
        double salary = resultSet.getDouble("salario_base");
        String hotelName = resultSet.getString("hotel_nombre");
        int stars = resultSet.getInt("nivel_estrellas");
        double bonus = resultSet.getDouble("bono");
        String experienceLevel = resultSet.getString("nivel_experiencia");
        int totalRooms = resultSet.getInt("total_habitaciones");
        double commission = resultSet.getDouble("comision");

        if (type.equals(EmployeeTypes.MANAGER.getType())) {
          employee = new Manager(Integer.valueOf(id), name, salary, bonus, 0);
        }

        if (type.equals(EmployeeTypes.SELLER.getType())) {
          employee = new Salesperson(Integer.valueOf(id), name, salary, commission, 0);
        }

        if (type.equals(EmployeeTypes.MAID.getType())) {
          if (experienceLevel.equals(HousekeeperLevels.AMA_DE_LLAVES.getLevel())) {
            employee = new Housekeeper(Integer.valueOf(id), name, salary, totalRooms, commission, 0,
                HousekeeperLevels.AMA_DE_LLAVES);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return employee;
  }

  @Override
  public ArrayList<Employee> searchAll() {
    ArrayList<Employee> employees = new ArrayList<>();
    String query = "SELECT" +
        "  e.id AS empleado_id," +
        "  e.nombre AS empleado_nombre," +
        "  e.tipo AS tipo_empleado," +
        "  e.salario_base," +
        "  COALESCE(h.id, NULL) AS hotel_id," + // Maneja hotel_id como NULL si no aplica
        "  COALESCE(h.nombre, 'Sin asignar') AS hotel_nombre," +
        "  COALESCE(h.nivel_estrellas, 0) AS nivel_estrellas," +
        "  COALESCE(g.bono, 0) AS bono," +
        "  COALESCE(r.nivel_experiencia, '') AS nivel_experiencia," +
        "  COALESCE(r.total_habitaciones, 0) AS total_habitaciones," +
        "  COALESCE(v.comision, 0) AS comision" +
        " FROM" +
        "    Empleado e" +
        " LEFT JOIN" +
        "    Hotel h ON e.hotel_id = h.id" +
        " LEFT JOIN" +
        "    Gerente g ON e.id = g.empleado_id" +
        " LEFT JOIN" +
        "    Recamarera r ON e.id = r.empleado_id" +
        " LEFT JOIN" +
        "    Vendedor v ON e.id = v.empleado_id";

    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        int id = resultSet.getInt("empleado_id");
        String name = resultSet.getString("empleado_nombre");
        String type = resultSet.getString("tipo_empleado");
        double salary = resultSet.getDouble("salario_base");
        Integer hotelId = resultSet.getInt("hotel_id") != 0 ? resultSet.getInt("hotel_id") : null; // Maneja 0 como NULL
        String hotelName = resultSet.getString("hotel_nombre");
        int stars = resultSet.getInt("nivel_estrellas");
        double bonus = resultSet.getDouble("bono");
        String experienceLevel = resultSet.getString("nivel_experiencia");
        int totalRooms = resultSet.getInt("total_habitaciones");
        double commission = resultSet.getDouble("comision");

        Employee employee = null;

        if (type.equals(EmployeeTypes.MANAGER.getType())) {
          employee = new Manager(id, name, salary, bonus, hotelId);
        }

        if (type.equals(EmployeeTypes.SELLER.getType())) {
          employee = new Salesperson(id, name, salary, commission, hotelId);
        }

        if (type.equals(EmployeeTypes.MAID.getType())) {
          employee = new Housekeeper(id, name, salary, totalRooms, commission, hotelId, HousekeeperLevels.PRINCIPIANTE);
        }

        if (employee != null) {
          employees.add(employee);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return employees;
  }

  @Override
  public void update(Employee employee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
