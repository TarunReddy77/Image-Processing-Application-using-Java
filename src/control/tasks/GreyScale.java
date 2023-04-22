package control.tasks;

import control.PerformingImageTasks;
import java.io.IOException;
import java.util.Arrays;
import model.imagemodel.ImageModal;

/**
 * GreyScale class will control operations related to different kind of grayscale in image.
 */
public class GreyScale implements PerformingImageTasks {

  private final String component;
  private final String imageToUse;
  private final String newImageName;

  /**
   * Constructs a GreyScale in terms of its type, original image name and new image name.
   *
   * @param greScaleType the type of grayScale.
   * @param imageToUse   the name of original image.
   * @param newImageName the name of new edited image.
   */

  public GreyScale(String greScaleType, String imageToUse, String newImageName) {
    this.component = greScaleType;
    this.imageToUse = imageToUse;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    String[] types = {"red-component", "green-component", "blue-component", "value-component",
        "luma-component", "intensity-component"};

    if (Arrays.asList(types).contains(this.component)) {
      modelObj.greyscale(this.component, this.imageToUse, this.newImageName);
    } else {
      throw new IOException("This is invalid Grayscale Type!!");
    }
  }

}
