package mockcontroller;

import control.IController;
import javax.swing.JFrame;
import view.ImageView;

/**
 * Created MockViewOperation class which perform different tasks in view related to image
 * processing.
 */
public class MockViewOperation extends JFrame implements ImageView {

  private final StringBuilder log;

  /**
   * Constructs a ReadModel to initiate image data.
   *
   * @param log the inputs which is passing as arguments.
   */
  public MockViewOperation(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addFeatures(IController controller) {
    log.append("addFeatures : ").append(controller.getClass()).append("\n");
  }

  @Override
  public void loadImage(String imageName) {
    log.append("loadImage : ").append(imageName).append("\n");
  }

  @Override
  public void loadCombineImage(String imageName, int uniqueID) {
    log.append("loadCombineImage : ").append(imageName).append(" ").append(uniqueID)
        .append("\n");
  }

  @Override
  public void clearScreen() {
    log.append("Input : clearScreen\n");
  }

  @Override
  public void errorPopUp(String errorMessage) {
    log.append("Input : ").append(errorMessage);
  }
}
