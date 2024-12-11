package employees.infrastructure.ui.windows.Catalog;

import db.infrastructure.GetMySQLConnection;
import employees.application.searchAll.EmployeesInHotelFinder;
import employees.domain.Employee;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;
import employees.infrastructure.ui.components.EmployeeTable;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ui.components.Menu;

public class RoomQuery {
    // Clase interna para gestionar el catálogo de habitaciones
    public static class RoomCatalog {
        private String APP_TITLE = "Catálogo de Habitaciones";
        private int APP_WIDTH = 300;
        private int APP_HEIGHT = 500;

        public void show() {
            Menu roomMenu = new Menu(APP_TITLE, APP_WIDTH, APP_HEIGHT);

            // Botón para registrar una nueva habitación
            roomMenu.addButton("Registrar Habitación", button -> {
                RegisterRoom registerRoom = new RegisterRoom();
                registerRoom.show();
                return null;
            });

            // Botón para listar las habitaciones
            roomMenu.addButton("Listar Habitaciones", button -> {
                RoomList roomList = new RoomList();
                roomList.show();
                return null;
            });

            // Botón para volver al menú principal
            roomMenu.addButton("Volver al menú principal", button -> {
                EmployeeCatalog mainCatalog = new EmployeeCatalog(APP_TITLE, APP_WIDTH, APP_HEIGHT);
                mainCatalog.show();
                return null;
            });

            // Mostrar el menú
            roomMenu.display();
        }
    }

    // Clase interna para registrar una nueva habitación
    public static class RegisterRoom {
        private String APP_TITLE = "Registrar Habitación";
        private int APP_WIDTH = 400;
        private int APP_HEIGHT = 300;

        public void show() {
            Form roomForm = new Form(APP_TITLE, APP_WIDTH, APP_HEIGHT);

            // Campos para registrar la habitación
            roomForm.addTextField("Tipo de Habitación (Individual, Doble, Suite)");
            roomForm.addTextField("Precio por Noche");
            roomForm.addTextField("Capacidad");
            roomForm.addTextField("Vista (Mar, Jardín, Ciudad)");
            roomForm.addTextField("Estado (Disponible, Ocupada)");

            // Botón para guardar la información
            roomForm.addSubmitButton("Registrar", form -> {
                // Procesa los datos del formulario
                System.out.println("Habitación registrada con éxito.");
                return null;
            });

            roomForm.display();
        }
    }

    // Clase interna para listar habitaciones
    public static class RoomList {
        private String APP_TITLE = "Lista de Habitaciones";
        private int APP_WIDTH = 400;
        private int APP_HEIGHT = 500;

        public void show() {
            ListView roomListView = new ListView(APP_TITLE, APP_WIDTH, APP_HEIGHT);

            // Ejemplo de habitaciones registradas
            List<String> rooms = Arrays.asList(
                "Habitación 101: Individual, $50/noche, Vista al Jardín, Disponible",
                "Habitación 102: Suite Doble, $120/noche, Vista al Mar, Ocupada"
            );

            // Mostrar las habitaciones en la lista
            roomListView.addItems(rooms);

            // Botón para volver al catálogo
            roomListView.addBackButton("Volver al Catálogo", button -> {
                RoomCatalog roomCatalog = new RoomCatalog();
                roomCatalog.show();
                return null;
            });

            roomListView.display();
        }
    }
}
