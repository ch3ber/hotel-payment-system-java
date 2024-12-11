package rooms.infrastructure.ui.windows.catalog;

import rooms.infrastructure.persistence.RoomRepositoryImpl;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteRoom {
  private RoomRepositoryImpl roomRepository;

  public DeleteRoom(Connection connection) {
    this.roomRepository = new RoomRepositoryImpl(connection);
  }

  public void show() {
    String roomId = JOptionPane.showInputDialog(null, "Ingrese el ID de la Habitación a eliminar:",
        "Eliminar Habitación", JOptionPane.QUESTION_MESSAGE);
    if (roomId != null && !roomId.trim().isEmpty()) {
      try {
        roomRepository.deleteRoom(Integer.parseInt(roomId));
        JOptionPane.showMessageDialog(null, "Habitación eliminada con éxito.");
      } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, "ID de la Habitación no puede estar vacío.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
