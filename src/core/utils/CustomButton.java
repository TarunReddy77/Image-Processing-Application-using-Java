package core.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * CustomButton is a class that extends JButton and provides custom appearance and behavior to the
 * button. The button has rounded corners, changes appearance when hovered over or clicked, and does
 * not display a border when painted. The size of the button is calculated based on the screen
 * size.
 */
public class CustomButton extends JButton {

  /**
   * Creates a new CustomButton with the specified label. Sets the preferred size of the button
   * based on the screen size, changes the font, foreground color, and border, and adds a mouse
   * listener to handle mouse events.
   *
   * @param label a string containing the text to be displayed on the button
   */
  public CustomButton(String label) {
    super(label);
    setMargin(new Insets(4, 0, 4, 0));
    setFont(new Font("Arial", Font.BOLD, 12));
    setForeground(Color.black);
    setBorderPainted(false);
    setContentAreaFilled(false);
    setFocusPainted(false);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        setBorderPainted(true);
        setContentAreaFilled(true);
      }

      @Override
      public void mouseExited(MouseEvent e) {
        setBorderPainted(false);
        setContentAreaFilled(false);
      }
    });
  }

  @Override
  public void paintComponent(Graphics g) {
    if (getModel().isPressed()) {
      g.setColor(new Color(170, 170, 170));
    } else if (getModel().isRollover()) {
      g.setColor(new Color(220, 220, 220));
    } else {
      g.setColor(getBackground());
    }
    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.black);
    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    g2.setColor(getBackground().brighter());
    g2.drawRoundRect(1, 1, getWidth() - 5, getHeight() - 1, 18, 18);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    super.paintComponent(g);
  }
}
