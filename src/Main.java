import employees.infrastructure.persistence.MySQLConnection;
import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;
import employees.infrastructure.ui.EmployeesList;
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
        employeeRepository.DEV_insert();

        // employeeRepository.save(new Manager(1, "John Doe", 1000.0, 200.0));
        EmployeesList employeesList = new EmployeesList(employeeRepository);
        employeesList.show();
    }
}
