package model.imageviewmodel;


import core.usecases.GetImageDetails;
import java.util.HashMap;

/**
 * ReadModel is an interface that defines a method to access image data. It requires the
 * implementation of the getImageData method that returns a HashMap of GetImageDetails objects for
 * each image.
 */
public interface ReadModel {

  /**
   * Returns a HashMap of image data maintained by the implementing class.
   *
   * @return a HashMap of image data
   */
  HashMap<String, GetImageDetails> getImageData();

}
