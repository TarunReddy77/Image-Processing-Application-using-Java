package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * Sharpen class will control operations related to image sharping operation.
 */
public class Sharpen implements PerformingImageTasks {

  private final String imageToUse;
  private final String newImageName;

  /**
   * Constructs a Save in terms of image file and image name.
   *
   * @param imageToUse   the image path in which different operations has been performed.
   * @param newImageName the name of new image.
   */
  public Sharpen(String imageToUse, String newImageName) {
    this.imageToUse = imageToUse;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.sharpening(this.imageToUse, this.newImageName);
  }

}
