package rooms.infrastructure.ui.windows.catalog;

import javax.swing.*;

public class DeleteRoom {

  public void show() {
    String roomId = JOptionPane.showInputDialog(null, "Ingrese el ID de la Habitación a eliminar:",
        "Eliminar Habitación", JOptionPane.QUESTION_MESSAGE);
    if (roomId != null && !roomId.trim().isEmpty()) {
      // Lógica para eliminar la habitación de la base de datos
      // ...
      JOptionPane.showMessageDialog(null, "Habitación eliminada con éxito.");
    } else {
      JOptionPane.showMessageDialog(null, "ID de la Habitación no puede estar vacío.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
