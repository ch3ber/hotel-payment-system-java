package employees.infrastructure.ui;

import javax.swing.*;

import employees.domain.Employee;

import java.awt.*;
import java.util.ArrayList;

public class EmployeesList {
  private ArrayList<Employee> employees;

  public EmployeesList(ArrayList<Employee> employees) {
    this.employees = employees;
  }

  public void show() {
    JFrame frame = new JFrame("Employees List");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

    String[] columnNames = { "ID", "Name", "Position" };

    // set employees data
    Object[][] data = new Object[employees.size()][3];
    for (int i = 0; i < employees.size(); i++) {
      data[i][0] = employees.get(i).getId();
      data[i][1] = employees.get(i).getName();
      data[i][2] = "Fixed position from list";
    }
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    table.setFillsViewportHeight(true);

    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.setVisible(true);
  }
}
