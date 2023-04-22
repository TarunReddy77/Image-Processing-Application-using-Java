package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * Brighten class will control operations related to brightness increment.
 */
public class Brighten implements PerformingImageTasks {

  private final int brightenValue;
  private final String imageToUse;
  private final String newImageName;

  /**
   * Constructs a Brightened in terms of its value, old image name and new image name.
   *
   * @param brightenValue the value of increment in brightness.
   * @param imageToUse    the name of original image.
   * @param newImageName  the name of new edited image.
   */

  public Brighten(int brightenValue, String imageToUse, String newImageName) {
    this.brightenValue = brightenValue;
    this.imageToUse = imageToUse;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.brightness(this.brightenValue, this.imageToUse, this.newImageName);
  }

}
