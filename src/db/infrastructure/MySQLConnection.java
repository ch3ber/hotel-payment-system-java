package db.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
  private String dbUri;
  private String dbUser;
  private String dbPass;
  private Connection connection;

  public MySQLConnection(String dbUri, String dbUser, String dbPass) {
    this.dbUri = dbUri;
    this.dbUser = dbUser;
    this.dbPass = dbPass;
  }

  public void openConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(dbUri, dbUser, dbPass);
      System.out.println("[DEBUG]: Open MySQL connection.");
    } catch (ClassNotFoundException e) {
      System.err.println("[ERROR]: Driver JDBC no encontrado.");
      System.err.println("[POSIBLE SOLUTION]: \n\t1. Agregar el driver JDBC a las dependencias del proyecto.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.err.println("[ERROR]: To connect to MySQL.");
      System.err
          .println("[POSIBLE SOLUTION]: \n\t1. Change .properties file vars.\n\t2. Check MySQL service is running.");
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    return connection;
  }

  public void closeConnection() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        System.out.println("[DEBUG]: Close MySQL connection.");
      }
    } catch (SQLException e) {
      System.err.println("[ERROR]: To close MySQL connection.");
      e.printStackTrace();
    }
  }
}
