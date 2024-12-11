package rooms.infrastructure.ui.windows.catalog;

import rooms.domain.Room;
import rooms.infrastructure.persistence.RoomRepositoryImpl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class RoomQuery {
  private RoomRepositoryImpl roomRepository;

  public RoomQuery(Connection connection) {
    this.roomRepository = new RoomRepositoryImpl(connection);
  }

  public void show() {
    String roomId = JOptionPane.showInputDialog(null, "Ingrese el ID de la Habitación a consultar:",
        "Consultar Habitación", JOptionPane.QUESTION_MESSAGE);
    if (roomId != null && !roomId.trim().isEmpty()) {
      try {
        Room room = roomRepository.getRoomById(Integer.parseInt(roomId));
        if (room != null) {
          JOptionPane.showMessageDialog(null, "Información de la habitación:\n" + room.toString());
        } else {
          JOptionPane.showMessageDialog(null, "Habitación no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, "ID de la Habitación no puede estar vacío.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
