package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * HorizontalFlip class will control operations related to image flip Horizontally.
 */
public class HorizontalFlip implements PerformingImageTasks {

  private final String imageToUse;
  private final String newImageName;

  /**
   * Constructs a HorizontalFlip in terms of original image name and new image name.
   *
   * @param imageToUse   the name of original image.
   * @param newImageName the name of new edited horizontal image.
   */
  public HorizontalFlip(String imageToUse, String newImageName) {
    this.imageToUse = imageToUse;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.flip(true, this.imageToUse, this.newImageName);
  }

}
