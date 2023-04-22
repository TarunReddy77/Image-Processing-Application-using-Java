package control.tasks;

import control.PerformingImageTasks;
import core.utils.WriteImage;
import core.usecases.GetImageDetails;
import model.imagemodel.ImageModal;
import java.io.IOException;

/**
 * Save class will control operations related to Saving edited images.
 */
public class Save implements PerformingImageTasks {

  private final String imagePath;
  private final String imageName;

  /**
   * Constructs a Save in terms of image file and image name.
   *
   * @param imagePath the image path in which different operations has been performed.
   * @param imageName the name of new image.
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    GetImageDetails imageData = modelObj.saveImage(this.imageName);
    new WriteImage(imageData, this.imagePath);
  }

}
