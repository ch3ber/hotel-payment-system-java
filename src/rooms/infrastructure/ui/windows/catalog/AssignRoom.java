package rooms.infrastructure.ui.windows.catalog;

import rooms.infrastructure.persistence.RoomRepositoryImpl;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AssignRoom {
  private RoomRepositoryImpl roomRepository;

  public AssignRoom(Connection connection) {
    this.roomRepository = new RoomRepositoryImpl(connection);
  }

  public void show() {
    String roomId = JOptionPane.showInputDialog(null, "Ingrese el ID de la Habitación a asignar:", "Asignar Habitación",
        JOptionPane.QUESTION_MESSAGE);
    if (roomId != null && !roomId.trim().isEmpty()) {
      String[] options = { "Ocupada", "Vacía" };
      int status = JOptionPane.showOptionDialog(null, "Seleccione el estado de la Habitación:", "Asignar Habitación",
          JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

      if (status != -1) {
        boolean isOccupied = status == 0;
        try {
          changeState(Integer.parseInt(roomId), isOccupied);
          JOptionPane.showMessageDialog(null,
              "Habitación asignada como " + (isOccupied ? "Ocupada" : "Vacía") + " con éxito.");
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la habitación.", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un estado.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, "ID de la Habitación no puede estar vacío.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void changeState(int roomId, boolean isOccupied) throws SQLException {
    roomRepository.assignRoomStatus(roomId, isOccupied);
  }
}
