package employees.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.infrastructure.MySQLConnection;

import java.sql.Statement;

import employees.domain.Employee;
import employees.domain.EmployeesRepository;
import employees.domain.Manager;
import employees.domain.Salesperson;
import employees.domain.enums.EmployeeTypes;
import employees.domain.enums.HousekeeperLevels;
import employees.domain.housekeepers.Housekeeper;

public class MySQLEmployeeRepositoryImpl implements EmployeesRepository {
  private MySQLConnection mySQLConnectionAccess;

  public MySQLEmployeeRepositoryImpl(MySQLConnection mySQLConnectionAccess) {
    this.mySQLConnectionAccess = mySQLConnectionAccess;
  }

  @Override
  public boolean save(Employee employee) {
    mySQLConnectionAccess.openConnection();
    Connection connection = mySQLConnectionAccess.getConnection();
    String templateQuery = "";
    boolean isSaved = false;
    try {
      templateQuery = "INSERT INTO Empleado (nombre, salario_base, tipo, hotel_id) VALUES (?, ?, ?, ?)";
      PreparedStatement query = connection.prepareStatement(templateQuery);

      query.setString(1, employee.getName());
      query.setDouble(2, employee.getSalary());
      query.setString(3, employee.getType());
      query.setInt(4, employee.getHotelId());

      int rowsAffected = query.executeUpdate();
      isSaved = rowsAffected > 0;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mySQLConnectionAccess.closeConnection();
    }
    return isSaved;
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
  public boolean update(Employee employee) {
    String query = "UPDATE Empleado SET " +
        "nombre = ?, " +
        "tipo = ?, " +
        "salario_base = ?, " +
        "hotel_id = ? " +
        "WHERE id = ?";

    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getType());
      statement.setDouble(3, employee.getSalary());
      statement.setInt(4, employee.getHotelId());
      statement.setInt(5, employee.getId());

      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      mySQLConnectionAccess.closeConnection();
    }

    return true;
  }

  @Override
  public boolean delete(String id) {
    String deletePagosQuery = "DELETE FROM Pago WHERE empleado_id = ?";
    String deleteComisionQuery = "DELETE FROM Comision WHERE empleado_id = ?";
    String deleteGerenteQuery = "DELETE FROM Gerente WHERE empleado_id = ?";
    String deleteRecamareraQuery = "DELETE FROM Recamarera WHERE empleado_id = ?";
    String deleteVendedorQuery = "DELETE FROM Vendedor WHERE empleado_id = ?";
    String deleteEmpleadoQuery = "DELETE FROM Empleado WHERE id = ?";
    boolean isDeleted = false;

    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection()) {
      connection.setAutoCommit(false);

      try (PreparedStatement deletePagosStmt = connection.prepareStatement(deletePagosQuery);
          PreparedStatement deleteComisionStmt = connection.prepareStatement(deleteComisionQuery);
          PreparedStatement deleteGerenteStmt = connection.prepareStatement(deleteGerenteQuery);
          PreparedStatement deleteRecamareraStmt = connection.prepareStatement(deleteRecamareraQuery);
          PreparedStatement deleteVendedorStmt = connection.prepareStatement(deleteVendedorQuery);
          PreparedStatement deleteEmpleadoStmt = connection.prepareStatement(deleteEmpleadoQuery)) {

        // Eliminar registros relacionados en la tabla Pago
        deletePagosStmt.setString(1, id);
        deletePagosStmt.executeUpdate();

        // Eliminar registros relacionados en la tabla Comision
        deleteComisionStmt.setString(1, id);
        deleteComisionStmt.executeUpdate();

        // Eliminar registros relacionados en la tabla Gerente
        deleteGerenteStmt.setString(1, id);
        deleteGerenteStmt.executeUpdate();

        // Eliminar registros relacionados en la tabla Recamarera
        deleteRecamareraStmt.setString(1, id);
        deleteRecamareraStmt.executeUpdate();

        // Eliminar registros relacionados en la tabla Vendedor
        deleteVendedorStmt.setString(1, id);
        deleteVendedorStmt.executeUpdate();

        // Eliminar el empleado
        deleteEmpleadoStmt.setString(1, id);
        int rowsAffected = deleteEmpleadoStmt.executeUpdate();
        isDeleted = rowsAffected > 0;

        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      mySQLConnectionAccess.closeConnection();
    }

    return isDeleted;
  }

}
