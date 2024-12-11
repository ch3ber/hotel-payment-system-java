package rooms.infrastructure.ui.windows.catalog;

import javax.swing.*;

public class UpdateRoom {

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
        // Lógica para actualizar la habitación en la base de datos
        // ...
        JOptionPane.showMessageDialog(null, "Habitación modificada con éxito.");
      } else {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(null, "ID de la Habitación no puede estar vacío.", "Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}