package employees.infrastructure.ui;

import javax.swing.*;

import employees.infrastructure.persistence.DiskEmployeeRepositoryImpl;

import java.awt.*;

public class EmployeesList {
  // basic list showing dumie data of employees with swing awt components
  public static void show() {
    JFrame frame = new JFrame("Employees List");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

    String[] columnNames = { "ID", "Name", "Position" };
    DiskEmployeeRepositoryImpl employeeRepository = new DiskEmployeeRepositoryImpl();

    Object[][] data = employeeRepository.findAll().stream()
        .map(employee -> new Object[] { employee.getId(), employee.getName(), employee.getClass().getSimpleName() })
        .toArray(Object[][]::new);
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    table.setFillsViewportHeight(true);

    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.setVisible(true);
  }
}
