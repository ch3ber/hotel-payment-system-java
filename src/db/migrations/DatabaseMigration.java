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
    String dbName = "HotelPaymentSystemJava";
    String useDb = "USE " + dbName;

    // Crear la base de datos
    migrations.add("CREATE DATABASE IF NOT EXISTS " + dbName);
    migrations.add(useDb);

    // Crear tabla de Hoteles
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Hotel (" +
            "  id INT AUTO_INCREMENT PRIMARY KEY," +
            "  nombre VARCHAR(100) NOT NULL," +
            "  nivel_estrellas INT NOT NULL CHECK (nivel_estrellas IN (3, 4, 5))," +
            "  costo_base_sencilla DECIMAL(10, 2) NOT NULL," +
            "  costo_base_doble DECIMAL(10, 2) NOT NULL," +
            "  costo_base_penhouse DECIMAL(10, 2) NOT NULL" +
            ")");

    // Crear tabla de Habitaciones
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Habitacion (" +
            "  id INT AUTO_INCREMENT PRIMARY KEY," +
            "  hotel_id INT," +
            "  tipo ENUM('sencilla', 'doble', 'penhouse') NOT NULL," +
            "  costo_por_dia DECIMAL(10, 2) NOT NULL," +
            "  FOREIGN KEY (hotel_id) REFERENCES Hotel(id)" +
            ")");

    // Crear tabla de Empleados
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Empleado (" +
            "  id INT AUTO_INCREMENT PRIMARY KEY," +
            "  nombre VARCHAR(100) NOT NULL," +
            "  tipo ENUM('Gerente', 'Recamarera', 'Vendedor') NOT NULL," +
            "  salario_base DECIMAL(10, 2) NOT NULL," +
            "  hotel_id INT," +
            "  FOREIGN KEY (hotel_id) REFERENCES Hotel(id)" +
            ")");

    // Crear tabla de Gerentes
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Gerente (" +
            "  empleado_id INT PRIMARY KEY," +
            "  bono DECIMAL(10, 2) NOT NULL," +
            "  FOREIGN KEY (empleado_id) REFERENCES Empleado(id)" +
            ")");

    // Crear tabla de Recamareras
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Recamarera (" +
            "  empleado_id INT PRIMARY KEY," +
            "  nivel_experiencia ENUM('auxiliar', 'principiante', 'experimentada', 'ama de llaves') NOT NULL," +
            "  total_habitaciones INT DEFAULT 0," +
            "  FOREIGN KEY (empleado_id) REFERENCES Empleado(id)" +
            ")");

    // Crear tabla de Vendedores
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Vendedor (" +
            "  empleado_id INT PRIMARY KEY," +
            "  comision DECIMAL(10, 2) DEFAULT 0.00," +
            "  FOREIGN KEY (empleado_id) REFERENCES Empleado(id)" +
            ")");

    // Crear tabla de Ventas
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Venta (" +
            "  id INT AUTO_INCREMENT PRIMARY KEY," +
            "  hotel_id INT," +
            "  habitacion_id INT," +
            "  empleado_id INT," +
            "  fecha DATE NOT NULL," +
            "  cantidad_personas INT NOT NULL," +
            "  monto_total DECIMAL(10, 2) NOT NULL," +
            "  FOREIGN KEY (hotel_id) REFERENCES Hotel(id)," +
            "  FOREIGN KEY (habitacion_id) REFERENCES Habitacion(id)," +
            "  FOREIGN KEY (empleado_id) REFERENCES Empleado(id)" +
            ")");

    // Crear tabla de Comisiones
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Comision (" +
            "  id INT AUTO_INCREMENT PRIMARY KEY," +
            "  empleado_id INT," +
            "  fecha DATE NOT NULL," +
            "  monto DECIMAL(10, 2) NOT NULL," +
            "  FOREIGN KEY (empleado_id) REFERENCES Empleado(id)" +
            ")");

    // Crear tabla de Pagos
    migrations.add(
        "CREATE TABLE IF NOT EXISTS Pago (" +
            "  id INT AUTO_INCREMENT PRIMARY KEY," +
            "  empleado_id INT," +
            "  periodo ENUM('semanal', 'quincenal', 'mensual') NOT NULL," +
            "  fecha_pago DATE NOT NULL," +
            "  monto DECIMAL(10, 2) NOT NULL," +
            "  FOREIGN KEY (empleado_id) REFERENCES Empleado(id)" +
            ")");

    // Ejecutar las migraciones
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
