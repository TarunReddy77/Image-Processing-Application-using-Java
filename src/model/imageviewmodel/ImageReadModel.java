package model.imageviewmodel;

import core.usecases.GetImageDetails;
import java.util.HashMap;
import model.imagemodel.ImageModal;

/**
 * ImageReadModel is a class that implements the ReadModel interface and provides methods to access
 * image data. It maintains a HashMap of GetImageDetails objects for each image.
 */
public class ImageReadModel implements ReadModel {

  private final HashMap<String, GetImageDetails> imageDetail;

  /**
   * Creates a new ImageReadModel object with the specified ImageModal object.
   *
   * @param imageProcess an ImageModal object used to retrieve image data
   */
  public ImageReadModel(ImageModal imageProcess) {
    this.imageDetail = imageProcess.getImageData();
  }

  @Override
  public HashMap<String, GetImageDetails> getImageData() {
    HashMap<String, GetImageDetails> imageDetail = this.imageDetail;
    return imageDetail;
  }

}
