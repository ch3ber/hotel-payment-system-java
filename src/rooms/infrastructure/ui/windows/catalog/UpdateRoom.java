package rooms.infrastructure.ui.windows.catalog;

import rooms.domain.Room;
import rooms.infrastructure.persistence.RoomRepositoryImpl;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateRoom {
  private RoomRepositoryImpl roomRepository;

  public UpdateRoom(Connection connection) {
    this.roomRepository = new RoomRepositoryImpl(connection);
  }

  public void show() {
    String roomId = JOptionPane.showInputDialog(null, "Ingrese el ID de la Habitación a modificar:",
        "Modificar Habitación", JOptionPane.QUESTION_MESSAGE);
    if (roomId != null && !roomId.trim().isEmpty()) {
      String hotelId = JOptionPane.showInputDialog(null, "Ingrese el ID del Hotel:", "Modificar Habitación",
          JOptionPane.QUESTION_MESSAGE);
      String tipo = JOptionPane.showInputDialog(null, "Ingrese el Tipo de Habitación:", "Modificar Habitación",
          JOptionPane.QUESTION_MESSAGE);
      String costoPorDia = JOptionPane.showInputDialog(null, "Ingrese el Costo por Día:", "Modificar Habitación",
          JOptionPane.QUESTION_MESSAGE);

      if (hotelId != null && tipo != null && costoPorDia != null && !hotelId.trim().isEmpty() && !tipo.trim().isEmpty()
          && !costoPorDia.trim().isEmpty()) {
        try {
          Room room = new Room(Integer.parseInt(roomId), Integer.parseInt(hotelId), tipo,
              Double.parseDouble(costoPorDia));
          roomRepository.updateRoom(room);
          JOptionPane.showMessageDialog(null, "Habitación modificada con éxito.");
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al modificar la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, "ID de la Habitación no puede estar vacío.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}