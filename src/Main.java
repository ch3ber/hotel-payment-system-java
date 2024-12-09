import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.infrastructure.MySQLConnection;

import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;
import employees.infrastructure.ui.components.EmployeeTable;
import employees.application.searchAll.EmployeesInHotelFinder;
import employees.domain.Employee;
import employees.domain.EmployeeRepository;

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
        // 8. Mostrar las comisiones generadas por dia de un hotel en particular.
        // 9. Mostrar un reporte general de todas las comisiones generadas filtrandolas
        // por un rango de fechas.
        // 10. Mostrar un reporte general de todas las ventas hechas filtrandolas por un
        // rango de fechas.
        // 11. Mostrar reporte de los bonos generados mensuales por gerente.

        Menu menu = new Menu("Main Menu", 200, 400);

        menu.addButton("Catalogo de empleados", button -> {
            // 14. El Catalogo consta de las funciones Alta, Baja, Modificacion y Consulta.
            Menu selectedCRUDOption = new Menu("Selecciona una opcion", 200, 400);
            selectedCRUDOption.addButton("Alta", button1 -> {
                return null;
            });

            selectedCRUDOption.addButton("Baja", button1 -> {
                return null;
            });

            selectedCRUDOption.addButton("Modificacion", button1 -> {
                return null;
            });

            selectedCRUDOption.addButton("Consulta", button1 -> {
                Menu selectedHotelOrigin = new Menu("Selecciona el hotel de origen", 200, 400);

                // get hotel id
                String hotelId = JOptionPane.showInputDialog("Ingrese el ID del hotel");

                // validate hotel id
                if (hotelId == null) {
                    // show info message
                    JOptionPane.showMessageDialog(null, "No se ingreso un ID de hotel");
                    return null;
                }

                // search employees from hotel id using finder
                EmployeeRepository employeeRepository = new MySQLEmployeeRepositoryImpl(mySQLConnectionAccess);
                EmployeesInHotelFinder employeesInHotelFinder = new EmployeesInHotelFinder(employeeRepository);
                int hotelIdInt;
                try {
                    hotelIdInt = Integer.parseInt(hotelId);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El ID del hotel debe ser un número");
                    return null;
                }
                ArrayList<Employee> employees = employeesInHotelFinder.run(hotelIdInt);

                // show employees
                if (employees.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No se encontraron empleados en el hotel con ID " + hotelId);
                } else {
                    // show employees in table
                    EmployeeTable employeeTable = new EmployeeTable("Empleados", 400, 400);
                    employeeTable.setEmployees(employees);
                    employeeTable.show();
                }

                selectedHotelOrigin.show();
                return null;
            });

            selectedCRUDOption.show();
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
            // input the employee id
            String id = JOptionPane.showInputDialog("Ingrese el ID del empleado");
            if (id == null) {
                return null;
            }

            // search the employee
            MySQLEmployeeRepositoryImpl employeeRepository = new MySQLEmployeeRepositoryImpl(mySQLConnectionAccess);
            Employee employee = employeeRepository.search(id);

            // show the employee
            if (employee != null) {
                JOptionPane.showMessageDialog(null,
                        "El salario de " + employee.getName() + " es de " + employee.getSalary());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el empleado con el ID " + id);
            }
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
