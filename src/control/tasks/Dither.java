package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * Dither class will control operations related to image dithering.
 */
public class Dither implements PerformingImageTasks {

  private final String imageToUse;
  private final String newImageName;

  /**
   * Constructs a Save in terms of image file and image name.
   *
   * @param imageToUse   the image path in which different operations has been performed.
   * @param newImageName the name of new image.
   */
  public Dither(String imageToUse, String newImageName) {
    this.imageToUse = imageToUse;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.dithering(this.imageToUse, this.newImageName);
  }
}
