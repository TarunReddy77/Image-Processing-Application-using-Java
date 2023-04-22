package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * RGBCombine class will control operations related to split images in RGB.
 */
public class RGBSplit implements PerformingImageTasks {

  private final String imageToUse;
  private final String redImageName;
  private final String greenImageName;
  private final String blueImageName;

  /**
   * Constructs a RGBSplit in terms of original image name and edited other type of images name.
   *
   * @param imageToUse     the name of image in which different operations will perform.
   * @param redImageName   the name of red split image.
   * @param greenImageName the name of green split image.
   * @param blueImageName  the name of blue split image.
   */
  public RGBSplit(String imageToUse, String redImageName, String greenImageName,
      String blueImageName) {
    this.imageToUse = imageToUse;
    this.redImageName = redImageName;
    this.greenImageName = greenImageName;
    this.blueImageName = blueImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.splitRGB(this.imageToUse, this.redImageName, this.greenImageName, this.blueImageName);
  }

}
