
package rooms.infrastructure.persistence;

import rooms.domain.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl {
  private Connection connection;

  public RoomRepositoryImpl(Connection connection) {
    this.connection = connection;
  }

  public void addRoom(Room room) throws SQLException {
    String query = "INSERT INTO Habitacion (hotel_id, tipo, costo_por_dia, ocupada) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, room.getHotelId());
      stmt.setString(2, room.getTipo());
      stmt.setDouble(3, room.getCostoPorDia());
      stmt.setBoolean(4, false); // Default to not occupied
      stmt.executeUpdate();
    }
  }

  public void deleteRoom(int roomId) throws SQLException {
    String query = "DELETE FROM Habitacion WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, roomId);
      stmt.executeUpdate();
    }
  }

  public void updateRoom(Room room) throws SQLException {
    String query = "UPDATE Habitacion SET hotel_id = ?, tipo = ?, costo_por_dia = ? WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, room.getHotelId());
      stmt.setString(2, room.getTipo());
      stmt.setDouble(3, room.getCostoPorDia());
      stmt.setInt(4, room.getId());
      stmt.executeUpdate();
    }
  }

  public Room getRoomById(int roomId) throws SQLException {
    String query = "SELECT * FROM Habitacion WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, roomId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Room(
            rs.getInt("id"),
            rs.getInt("hotel_id"),
            rs.getString("tipo"),
            rs.getDouble("costo_por_dia"));
      }
    }
    return null;
  }

  public List<Room> getAllRooms() throws SQLException {
    List<Room> rooms = new ArrayList<>();
    String query = "SELECT * FROM Habitacion";
    try (Statement stmt = connection.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        rooms.add(new Room(
            rs.getInt("id"),
            rs.getInt("hotel_id"),
            rs.getString("tipo"),
            rs.getDouble("costo_por_dia")));
      }
    }
    return rooms;
  }

  public void assignRoomStatus(int roomId, boolean isOccupied) throws SQLException {
    String query = "UPDATE Habitacion SET ocupada = ? WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      stmt.setBoolean(1, isOccupied);
      stmt.setInt(2, roomId);
      stmt.executeUpdate();
    }
  }
}