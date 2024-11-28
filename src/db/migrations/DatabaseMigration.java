package db.migrations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.infrastructure.MySQLConnection;

public class DatabaseMigration {
  private MySQLConnection mySQLConnectionAccess;

  public DatabaseMigration(MySQLConnection mySQLConnectionAccess) {
    this.mySQLConnectionAccess = mySQLConnectionAccess;
  }

  public void run() {
    List<String> migrations = new ArrayList<>();

    // Definir las migraciones como sentencias SQL
    String dbName = "hotel_payment_system_java";
    String useDb = "USE " + dbName;

    // Crear la base de datos
    migrations.add("CREATE DATABASE IF NOT EXISTS " + dbName);
    migrations.add(useDb);

    // Crear tabla de empleados
    migrations.add("CREATE TABLE IF NOT EXISTS employees (" +
        "id INT PRIMARY KEY AUTO_INCREMENT, " +
        "name VARCHAR(100), " +
        "salary DOUBLE, " +
        "type VARCHAR(50), " +
        "bonus DOUBLE, " +
        "commission DOUBLE, " +
        "totalRooms INT, " +
        "commissionPerRoom DOUBLE)");

    executeMigrations(migrations);
  }

  private void executeMigrations(List<String> migrations) {
    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection();
        Statement statement = connection.createStatement()) {
      for (String migration : migrations) {
        statement.execute(migration);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
