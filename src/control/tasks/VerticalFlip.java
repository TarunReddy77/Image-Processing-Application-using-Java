package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * VerticalFlip class will control operations related to image flip Vertically.
 */
public class VerticalFlip implements PerformingImageTasks {

  private final String imageToUse;
  private final String newImageName;

  /**
   * Constructs a VerticalFlip in terms of original image name and new image name.
   *
   * @param imageToUse   the name of original image.
   * @param newImageName the name of new edited Vertical image.
   */
  public VerticalFlip(String imageToUse, String newImageName) {
    this.imageToUse = imageToUse;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.flip(false, this.imageToUse, this.newImageName);
  }

}
