package employees.infrastructure.persistence;

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
    } catch (ClassNotFoundException e) {
      System.err.println("Driver JDBC no encontrado");
      e.printStackTrace();
    } catch (SQLException e) {
      System.err.println("Error al conectar con la base de datos");
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
      }
    } catch (SQLException e) {
      System.err.println("Error al cerrar la conexión");
      e.printStackTrace();
    }
  }
}
