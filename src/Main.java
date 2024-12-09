import employees.infrastructure.ui.windows.Catalog.EmployeeCatalog;
import employees.infrastructure.ui.windows.showSalary.ShowSalary;
import ui.components.*;

public class Main {
    public static void main(String[] args) {
        int APP_WIDTH = 200;
        int APP_HEIGHT = 400;
        String APP_TITLE = "Main Menu";

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
        // 8. Mostrar las comisiones generadas por dia de un hotel en particular.
        // 9. Mostrar un reporte general de todas las comisiones generadas filtrandolas
        // por un rango de fechas.
        // 10. Mostrar un reporte general de todas las ventas hechas filtrandolas por un
        // rango de fechas.
        // 11. Mostrar reporte de los bonos generados mensuales por gerente.

        Menu menu = new Menu(APP_TITLE, APP_WIDTH, APP_HEIGHT);

        menu.addButton("Catalogo de empleados", button -> {
            EmployeeCatalog employeeCatalog = new EmployeeCatalog("Catalogo de empleados", APP_WIDTH, APP_HEIGHT);
            employeeCatalog.show();
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

        menu.addButton("Registrar las ventas diarias", button -> {
            return null;
        });

        menu.addButton("Registrar por dia las comisiones", button -> {
            return null;
        });

        menu.addButton("Mostrar el sueldo", button -> {
            ShowSalary showSalary = new ShowSalary();
            showSalary.show();
            return null;
        });

        menu.addButton("Mostrar las comisiones generadas por dia", button -> {
            return null;
        });

        menu.addButton("Mostrar un reporte general de todas las comisiones", button -> {
            return null;
        });

        menu.addButton("Mostrar un reporte general de todas las ventas", button -> {
            return null;
        });

        menu.addButton("Mostrar reporte de los bonos generados mensuales", button -> {
            return null;
        });

        menu.addButton("Salir", button -> {
            System.exit(0);
            return null;
        });

        menu.show();
    }
}
