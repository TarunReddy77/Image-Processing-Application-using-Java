package control.tasks;

import control.PerformingImageTasks;
import core.utils.ReadImage;
import core.usecases.GetImageDetails;
import java.io.IOException;
import model.imagemodel.ImageModal;


/**
 * Load class will control operations related to loading the image file.
 */
public class Load implements PerformingImageTasks {

  private final String imagePath;
  private final String newImageName;

  /**
   * Constructs a Load in terms of image file and image name.
   *
   * @param imagePath    the image file in which different operations will perform.
   * @param newImageName the name of image.
   */
  public Load(String imagePath, String newImageName) {
    this.imagePath = imagePath;
    this.newImageName = newImageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    GetImageDetails imageDetails = new ReadImage(this.imagePath).getImageDetails();
    modelObj.loadImage(imageDetails, this.newImageName);
  }
}
