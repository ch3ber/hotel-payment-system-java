package rooms.infrastructure.ui.windows.catalog;

import java.sql.Connection;

import db.infrastructure.GetMySQLConnection;
import db.infrastructure.MySQLConnection;
import ui.components.Menu;

public class RoomCatalog {
  int APP_WIDTH = 200;
  int APP_HEIGHT = 400;
  String APP_TITLE = "Selecciona una opcion";

  public RoomCatalog(String title, int width, int height) {
    this.APP_TITLE = title;
    this.APP_WIDTH = width;
    this.APP_HEIGHT = height;
  }

  public void show() {
    Menu selectedCatalogOption = new Menu(APP_TITLE, APP_WIDTH, APP_HEIGHT);
    selectedCatalogOption.addButton("Asignar Cuarto", button1 -> {
      MySQLConnection mySQLConnection = GetMySQLConnection.getConnection();
      mySQLConnection.openConnection();
      Connection connection = mySQLConnection.getConnection();

      AssignRoom assignRoom = new AssignRoom(connection);
      assignRoom.show();
      return null;
    });

    selectedCatalogOption.addButton("Eliminar", button1 -> {
      DeleteRoom deleteRoom = new DeleteRoom();
      deleteRoom.show();
      return null;
    });

    selectedCatalogOption.addButton("Modificacion", button1 -> {
      UpdateRoom updateRoom = new UpdateRoom();
      updateRoom.show();
      return null;
    });

    selectedCatalogOption.addButton("Consulta", button1 -> {
      RoomQuery roomQuery = new RoomQuery();
      roomQuery.show();
      return null;
    });

    selectedCatalogOption.show();
  }
}
