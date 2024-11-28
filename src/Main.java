import java.util.ArrayList;

import db.infrastructure.MySQLConnection;
import employees.domain.Employee;
import employees.domain.Manager;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;
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

        MySQLEmployeeRepositoryImpl employeeRepository = new MySQLEmployeeRepositoryImpl(mySQLConnectionAccess);
        employeeRepository.save(new Manager(1, "Insert from Main", 1000.0, 200.0));

        ArrayList<Employee> employees = employeeRepository.searchAll();
        // print employees
        System.out.println("Employees:" + employees.toString());

        String[] columnNames = { "ID", "Name", "Position" };
        Table<Employee> employeeTable = new Table<>(employees, columnNames, employee -> new Object[] {
                employee.getId(), employee.getName(), "Fixed position from list"
        });
        employeeTable.show();
    }
}
