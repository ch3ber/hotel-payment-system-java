package employees.infrastructure.ui.windows.Catalog;

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
    selectedCatalogOption.addButton("Registar Cuarto", button1 -> {
      RegisterRoom registerRoom = new RegisterRoom();
      registerRoom.show();
      return null;
    });

    selectedCatalogOption.addButton("Listar Habitaciones", button1 -> {
      ListRoom ListRoom = new ListRoom();
      ListRoom.show();
      return null;
    });

    selectedCatalogOption.show();
  }
}