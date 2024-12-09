package ui.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Menu {
  private final JFrame frame;
  private final JPanel panel;
  private final ArrayList<JButton> buttons;

  public Menu(String title, int width, int height) {
    this.frame = new JFrame(title);
    this.frame.setSize(width, height);
    this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.frame.setLayout(new BorderLayout());

    this.panel = new JPanel();
    this.panel.setLayout(new GridLayout(0, 1));
    this.frame.add(this.panel, BorderLayout.CENTER);

    this.buttons = new ArrayList<>();
  }

  public void addButton(String text, Function<JButton, Void> onClick) {
    JButton button = new JButton(text);
    button.addActionListener(e -> onClick.apply(button));
    this.buttons.add(button);
    this.panel.add(button);
  }

  public void show() {
    this.frame.setVisible(true);
  }
}
