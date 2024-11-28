package ui.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Table<T> {
  private ArrayList<T> items;
  private String[] columnNames;
  private Function<T, Object[]> rowMapper;

  public Table(ArrayList<T> items, String[] columnNames, Function<T, Object[]> rowMapper) {
    this.items = items;
    this.columnNames = columnNames;
    this.rowMapper = rowMapper;
  }

  public void show() {
    JFrame frame = new JFrame("List");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

    // set items data
    Object[][] data = new Object[items.size()][columnNames.length];
    for (int i = 0; i < items.size(); i++) {
      data[i] = rowMapper.apply(items.get(i));
    }
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    table.setFillsViewportHeight(true);

    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.setVisible(true);
  }
}
