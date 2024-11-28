package employees.infrastructure.persistence;

import java.io.*;
import java.util.ArrayList;

import employees.domain.Employee;
import employees.domain.EmployeeRepository;
import employees.domain.Manager;
import employees.domain.Salesperson;
import employees.domain.housekeepers.Housekeeper;

public class DiskEmployeeRepositoryImpl implements EmployeeRepository {
    private static final String FILE_PATH = "src/db/employees.csv";

    @Override
    public void save(Employee employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(employeeToCSV(employee));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee search(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = csvToEmployee(line);
                if (employee != null && String.valueOf(employee.getId()).equals(id)) {
                    return employee;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Employee employee) {
        ArrayList<Employee> employees = searchAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee emp : employees) {
                if (emp.getId() == employee.getId()) {
                    writer.write(employeeToCSV(employee));
                } else {
                    writer.write(employeeToCSV(emp));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        ArrayList<Employee> employees = searchAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee emp : employees) {
                if (!String.valueOf(emp.getId()).equals(id)) {
                    writer.write(employeeToCSV(emp));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Employee> searchAll() {
        ArrayList<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = csvToEmployee(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private String employeeToCSV(Employee employee) {
        // Convert employee to CSV format
        // Example: id,name,salary,bonus (for Manager)
        StringBuilder sb = new StringBuilder();
        sb.append(employee.getId()).append(",");
        sb.append(employee.getName()).append(",");
        sb.append(employee.getSalary());

        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            sb.append(",").append(manager.getBonus());
        } else if (employee instanceof Salesperson) {
            Salesperson salesperson = (Salesperson) employee;
            sb.append(",").append(salesperson.getCommission());
        } else if (employee instanceof Housekeeper) {
            Housekeeper housekeeper = (Housekeeper) employee;
            sb.append(",").append(housekeeper.getTotalRooms()).append(",");
            sb.append(housekeeper.getCommissionPerRoom());
        }

        return sb.toString();
    }

    private Employee csvToEmployee(String csv) {
        // Convert CSV format to employee
        // Example: id,name,salary,bonus (for Manager)
        String[] fields = csv.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        double salary = Double.parseDouble(fields[2]);

        if (fields.length == 4) {
            double bonus = Double.parseDouble(fields[3]);
            return new Manager(id, name, salary, bonus);
        } else if (fields.length == 4) {
            double commission = Double.parseDouble(fields[3]);
            return new Salesperson(id, name, salary, commission);
        } else if (fields.length == 5) {
            // int totalRooms = Integer.parseInt(fields[3]);
            // double commissionPerRoom = Double.parseDouble(fields[4]);
            // return new Housekeeper(id, name, salary, totalRooms, commissionPerRoom);
        }

        return null;
    }
}