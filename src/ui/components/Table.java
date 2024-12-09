package ui.components;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Table {

  private Frame frame;
  private Panel panel;
  private List<String> columnNames;
  private List<Integer> columnWidths;
  private List<Object[]> rows;

  public Table(String title, int width, int height) {
    frame = new Frame(title);
    frame.setSize(width, height);
    frame.setLayout(new BorderLayout());

    panel = new Panel();
    panel.setLayout(new GridLayout(0, 1)); // Dynamic row creation

    columnNames = new ArrayList<>();
    columnWidths = new ArrayList<>();
    rows = new ArrayList<>();

    frame.add(panel, BorderLayout.CENTER);

    // Handle window closing
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        frame.dispose();
      }
    });
  }

  public void addColumn(String name, int width) {
    columnNames.add(name);
    columnWidths.add(width);
  }

  public void addRow(Object... values) {
    if (values.length != columnNames.size()) {
      throw new IllegalArgumentException("Row data must match the number of columns.");
    }
    rows.add(values);
  }

  public void show() {
    // Add column headers
    Panel header = new Panel(new GridLayout(1, columnNames.size()));
    for (int i = 0; i < columnNames.size(); i++) {
      Label label = new Label(columnNames.get(i), Label.CENTER);
      label.setPreferredSize(new Dimension(columnWidths.get(i), 30));
      header.add(label);
    }
    panel.add(header);

    // Add rows
    for (Object[] row : rows) {
      Panel rowPanel = new Panel(new GridLayout(1, columnNames.size()));
      for (int i = 0; i < row.length; i++) {
        Label label = new Label(String.valueOf(row[i]), Label.CENTER);
        label.setPreferredSize(new Dimension(columnWidths.get(i), 30));
        rowPanel.add(label);
      }
      panel.add(rowPanel);
    }

    frame.setVisible(true);
  }
}
