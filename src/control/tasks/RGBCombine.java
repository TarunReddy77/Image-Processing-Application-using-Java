package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * RGBCombine class will control operations related to combine images in RGB.
 */
public class RGBCombine implements PerformingImageTasks {

  private final String imageToUse;
  private final String redImageName;
  private final String greenImageName;
  private final String blueImageName;

  /**
   * Constructs a RGBCombine in terms of original image name and edited other type of images name.
   *
   * @param imageToUse     the name of image in which different operations will perform.
   * @param redImageName   the name of red combine image.
   * @param greenImageName the name of green combine image.
   * @param blueImageName  the name of blue combine image.
   */
  public RGBCombine(String imageToUse, String redImageName, String greenImageName,
      String blueImageName) {
    this.imageToUse = imageToUse;
    this.redImageName = redImageName;
    this.greenImageName = greenImageName;
    this.blueImageName = blueImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    modelObj.combineRGB(this.imageToUse, this.redImageName, this.greenImageName,
        this.blueImageName);
  }

}
