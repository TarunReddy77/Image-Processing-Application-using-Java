package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

public class Mosaic implements PerformingImageTasks {

  private final int numSeed;
  private final String imageToUse;
  private final String newImageName;

  /**
   * Constructs a Brightened in terms of its value, old image name and new image name.
   *
   * @param numSeed the value of increment in brightness.
   * @param imageToUse    the name of original image.
   * @param newImageName  the name of new edited image.
   */

  public Mosaic(int numSeed, String imageToUse, String newImageName) {
    this.numSeed = numSeed;
    this.imageToUse = imageToUse;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.imageMosck(this.numSeed, this.imageToUse, this.newImageName);
  }


}
