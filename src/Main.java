import employees.domain.Manager;
import employees.infrastructure.persistence.DiskEmployeeRepositoryImpl;
import employees.infrastructure.ui.EmployeesList;

public class Main {
    public static void main(String[] args) {
        // show list of employees
        DiskEmployeeRepositoryImpl employeeRepository = new DiskEmployeeRepositoryImpl();

        employeeRepository.add(new Manager(1, "John Doe", 1000.0, 200.0));
        EmployeesList.show();
    }
}
