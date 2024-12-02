import db.infrastructure.MySQLConnection;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;
import employees.infrastructure.ui.components.EmployeeTable;
import ui.components.*;
import utils.LoadENV;

public class Main {
    public static void main(String[] args) {
        // load env vars
        LoadENV env = new LoadENV();
        env.load(".properties");

        MySQLConnection mySQLConnectionAccess = new MySQLConnection(env.getProperty("DB_URI"),
                env.getProperty("DB_USER"),
                env.getProperty("DB_PASS"));

        // menu options
        // 1. Catalogo de empleados: en esta ocación son Gerentes, Recamarera y
        // Vendedores.
        // 2. Catalogo de habitaciones segun el hotel y el tipo de recamara. considerar
        // el atributo costo por dia parte del registro de la habitacion
        // 3. Catalogo de hoteles segun su nivel de estrellas. Considerar el atributo
        // del costo por dia segun el nivel del hotel.
        // 4. Catalogo de comisiones: en esta ocacion son para la Recamarera y Vendedor,
        // considerar el nivel del hotel al pertenece el emeplado
        // 5. Registrar las ventas diarias segun las rentas de las habitaciones por
        // hotel, habitacion y cantidad de personas.
        // 6. Registrar por dia las comisiones de cada empleado segun la venta
        // registrada.
        // 7. Mostrar el sueldo mensual, quincenal o semanal de un empleado en
        // particular.
        // 8. Mostrar las comisiones generadas por dia de un hotel en particular.
        // 9. Mostrar un reporte general de todas las comisiones generadas filtrandolas
        // por un rango de fechas.
        // 10. Mostrar un reporte general de todas las ventas hechas filtrandolas por un
        // rango de fechas.
        // 11. Mostrar reporte de los bonos generados mensuales por gerente.

        Menu menu = new Menu("Main Menu", 200, 400);

        menu.addButton("Catalogo de empleados", button -> {
            Menu selectHotelMenu = new Menu("Selecciona el hotel de origen", 200, 400);
            selectHotelMenu.addButton("Hotel 1", button1 -> {
                EmployeeTable.setColumnNames(new String[] { "ID", "Name", "Position" });
                EmployeeTable.loadEmployeesFrom(new MySQLEmployeeRepositoryImpl(mySQLConnectionAccess));
                EmployeeTable.show();
                return null;
            });

            selectHotelMenu.addButton("Hotel 2", button1 -> {
                EmployeeTable.setColumnNames(new String[] { "ID", "Name", "Position" });
                EmployeeTable.loadEmployeesFrom(new MySQLEmployeeRepositoryImpl(mySQLConnectionAccess));
                EmployeeTable.show();
                return null;
            });

            selectHotelMenu.show();
            return null;
        });

        menu.addButton("Catalogo de habitaciones", button -> {
            return null;
        });

        menu.addButton("Catalogo de hoteles", button -> {
            return null;
        });

        menu.addButton("Catalogo de comisiones", button -> {
            return null;
        });

        menu.addButton("Salir", button -> {
            System.exit(0);
            return null;
        });

        menu.show();
    }
}
