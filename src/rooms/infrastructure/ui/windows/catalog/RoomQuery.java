package rooms.infrastructure.ui.windows.catalog;

import javax.swing.*;

public class RoomQuery {

  public void show() {
    String roomId = JOptionPane.showInputDialog(null, "Ingrese el ID de la Habitación a consultar:",
        "Consultar Habitación", JOptionPane.QUESTION_MESSAGE);
    if (roomId != null && !roomId.trim().isEmpty()) {
      // Lógica para consultar la habitación en la base de datos
      // ...
      JOptionPane.showMessageDialog(null, "Información de la habitación:\nID: " + roomId + "\n...otros detalles...");
    } else {
      JOptionPane.showMessageDialog(null, "ID de la Habitación no puede estar vacío.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
