package employees.infrastructure.ui;

import javax.swing.*;

import employees.infrastructure.persistence.MySQLEmployeeRepositoryImpl;

import java.awt.*;

public class EmployeesList {
  private MySQLEmployeeRepositoryImpl repository;

  public EmployeesList(MySQLEmployeeRepositoryImpl repository) {
    this.repository = repository;
  }

  public void show() {
    JFrame frame = new JFrame("Employees List");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

    String[] columnNames = { "ID", "Name", "Position" };

    Object[][] data = repository.findAll().stream()
        .map(employee -> new Object[] { employee.getId(), employee.getName(), employee.getClass().getSimpleName() })
        .toArray(Object[][]::new);
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    table.setFillsViewportHeight(true);

    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.setVisible(true);
  }
}
