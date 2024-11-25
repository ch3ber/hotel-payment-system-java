package employees.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Statement;

import employees.domain.Employee;
import employees.domain.EmployeeRepository;
import employees.domain.Manager;
import employees.domain.Salesperson;

public class MySQLEmployeeRepositoryImpl implements EmployeeRepository {
  private MySQLConnection mySQLConnectionAccess;

  public MySQLEmployeeRepositoryImpl(MySQLConnection mySQLConnectionAccess) {
    this.mySQLConnectionAccess = mySQLConnectionAccess;
  }

  public void DEV_insert() {
    mySQLConnectionAccess.openConnection();
    Connection connection = mySQLConnectionAccess.getConnection();
    String templateQuery = "";
    try {
      templateQuery = "INSERT INTO employees (name, salary, type, bonus, commission, totalRooms, commissionPerRoom) VALUES (?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement query = connection.prepareStatement(templateQuery);

      query.setString(1, "Tester");
      query.setString(2, "123.123");
      query.setString(3, "Manager");
      query.setString(4, "111");
      query.setString(5, "222");
      query.setString(6, "9");
      query.setString(7, "9.9");
      query.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mySQLConnectionAccess.closeConnection();
    }
  }

  @Override
  public void save(Employee employee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public Employee findById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public ArrayList<Employee> findAll() {
    ArrayList<Employee> employees = new ArrayList<>();
    String query = "SELECT * FROM employees";

    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double salary = resultSet.getDouble("salary");
        String type = resultSet.getString("type");

        Employee employee = null;
        switch (type) {
          case "Manager":
            double bonus = resultSet.getDouble("bonus");
            employee = new Manager(id, name, salary, bonus);
            break;
          case "Salesperson":
            double commission = resultSet.getDouble("commission");
            employee = new Salesperson(id, name, salary, commission);
            break;
          // case "Housekeeper":
          // int totalRooms = resultSet.getInt("totalRooms");
          // double commissionPerRoom = resultSet.getDouble("commissionPerRoom");
          // employee = new Housekeeper(id, name, salary, totalRooms, commissionPerRoom);
          // break;
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
