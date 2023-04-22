package core.utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicSpinnerUI;

/**
 * CustomSpinnerUI is a subclass of BasicSpinnerUI that provides custom user interface settings for
 * a JSpinner component. It sets the border color to gray, the background color to white, and the
 * font to Arial, plain, size 12. This class overrides the installDefaults() method of
 * BasicSpinnerUI to apply the custom settings. To use this class, set the UI of a JSpinner
 * component to an instance of CustomSpinnerUI.
 */
public class CustomSpinnerUI extends BasicSpinnerUI {

  @Override
  public void installDefaults() {
    super.installDefaults();
    spinner.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // set border color to gray
    spinner.setBackground(Color.WHITE); // set background color to white
    spinner.setFont(new Font("Arial", Font.PLAIN, 12)); // set font to Arial, plain, size 12
  }
}