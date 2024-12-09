package hotels.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.infrastructure.MySQLConnection;

import java.sql.Statement;

import hotels.domain.Hotel;
import hotels.domain.HotelRepository;

public class MySQLHotelRepositoryImpl implements HotelRepository {
  private MySQLConnection mySQLConnectionAccess;

  public MySQLHotelRepositoryImpl(MySQLConnection mySQLConnectionAccess) {
    this.mySQLConnectionAccess = mySQLConnectionAccess;
  }

  // HOTEL SCHEMA
  // CREATE TABLE Hotel
  // (
  // id INT
  // AUTO_INCREMENT PRIMARY KEY,
  // nombre VARCHAR
  // (100) NOT NULL,
  // nivel_estrellas INT NOT NULL CHECK
  // (nivel_estrellas IN
  // (3, 4, 5)),
  // costo_base_sencilla DECIMAL
  // (10, 2) NOT NULL,
  // costo_base_doble DECIMAL
  // (10, 2) NOT NULL,
  // costo_base_penhouse DECIMAL
  // (10, 2) NOT NULL
  // );
  @Override
  public void save(Hotel hotel) {
    mySQLConnectionAccess.openConnection();
    Connection connection = mySQLConnectionAccess.getConnection();
    String templateQuery = "";
    try {
      templateQuery = "INSERT INTO hotels (nombre, nivel_estrellas, costo_base_sencilla, costo_base_doble, costo_base_penhouse) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement query = connection.prepareStatement(templateQuery);

      query.setString(1, hotel.getName());
      query.setInt(2, hotel.getStars());
      query.setDouble(3, hotel.getCostPerDaySimple());
      query.setDouble(4, hotel.getCostPerDayDouble());
      query.setDouble(5, hotel.getCostPerDayPenhouse());
      query.execute();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mySQLConnectionAccess.closeConnection();
    }
  }

  @Override
  public Hotel search(String id) {
    Hotel hotel = null;

    String query = "SELECT" +
        "  id," +
        "  nombre," +
        "  nivel_estrellas," +
        "  costo_base_sencilla," +
        "  costo_base_doble," +
        "  costo_base_penhouse" +
        " FROM" +
        "    hotels" +
        " WHERE id = ?";

    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          hotel = new Hotel(
              resultSet.getInt("id"),
              resultSet.getString("nombre"),
              resultSet.getInt("nivel_estrellas"),
              resultSet.getDouble("costo_base_sencilla"),
              resultSet.getDouble("costo_base_doble"),
              resultSet.getDouble("costo_base_penhouse"));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mySQLConnectionAccess.closeConnection();
    }

    return hotel;
  }

  @Override
  public ArrayList<Hotel> searchAll() {
    ArrayList<Hotel> hotels = new ArrayList<>();
    String query = "SELECT" +
        "  id," +
        "  nombre," +
        "  nivel_estrellas," +
        "  costo_base_sencilla," +
        "  costo_base_doble," +
        "  costo_base_penhouse" +
        " FROM" +
        "    hotels";

    mySQLConnectionAccess.openConnection();
    try (Connection connection = mySQLConnectionAccess.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
      while (resultSet.next()) {
        Hotel hotel = new Hotel(
            resultSet.getInt("id"),
            resultSet.getString("nombre"),
            resultSet.getInt("nivel_estrellas"),
            resultSet.getDouble("costo_base_sencilla"),
            resultSet.getDouble("costo_base_doble"),
            resultSet.getDouble("costo_base_penhouse"));
        hotels.add(hotel);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      mySQLConnectionAccess.closeConnection();
    }

    return hotels;
  }

  @Override
  public void update(Hotel hotel) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
