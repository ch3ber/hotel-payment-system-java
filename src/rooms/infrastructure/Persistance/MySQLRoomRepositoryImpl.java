package rooms.infrastructure.Persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.infrastructure.MySQLConnection;

import java.sql.Statement;

import rooms.domain.Room;
import rooms.domain.RoomRepository;

public class MySQLRoomRepositoryImpl implements RoomRepository {
    private MySQLConnection mySQLConnectionAccess;

    public MySQLRoomRepositoryImpl(MySQLConnection mySQLConnectionAccess) {
      this.mySQLConnectionAccess = mySQLConnectionAccess;
    }
  
    @Override
    public boolean save(Room room) {
      mySQLConnectionAccess.openConnection();
      Connection connection = mySQLConnectionAccess.getConnection();
      String templateQuery = "";
      boolean isSaved = false;
      try {
        templateQuery = "INSERT INTO habitacion (hotel_id, tipo, costo_por_dia) VALUES (?, ?, ?)";
        PreparedStatement query = connection.prepareStatement(templateQuery);
  
        query.setString(1, Room.hotelId());
        query.setDouble(2, Room.gettype());
        query.setString(3, employee.getCostPerDay());
  
        int rowsAffected = query.executeUpdate();
        isSaved = rowsAffected > 0;
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        mySQLConnectionAccess.closeConnection();
      }
      return isSaved;
    }
}
