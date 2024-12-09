package employees.infrastructure.ui.windows.Catalog;

import ui.components.Menu;

public class EmployeeCatalog {
  int APP_WIDTH = 200;
  int APP_HEIGHT = 400;
  String APP_TITLE = "Selecciona una opcion";

  public EmployeeCatalog(String title, int width, int height) {
    this.APP_TITLE = title;
    this.APP_WIDTH = width;
    this.APP_HEIGHT = height;
  }

  public void show() {
    Menu selectedCatalogOption = new Menu(APP_TITLE, APP_WIDTH, APP_HEIGHT);
    selectedCatalogOption.addButton("Alta", button1 -> {
      return null;
    });

    selectedCatalogOption.addButton("Baja", button1 -> {
      return null;
    });

    selectedCatalogOption.addButton("Modificacion", button1 -> {
      UpdateEmployee updateEmployee = new UpdateEmployee();
      updateEmployee.show();
      return null;
    });

    selectedCatalogOption.addButton("Consulta", button1 -> {
      EmployeeQuery employeeQuery = new EmployeeQuery();
      employeeQuery.show();

      return null;
    });

    selectedCatalogOption.show();
  }
}
