package control;

import java.io.IOException;
import model.imagemodel.ImageModal;

/**
 * The controller interface for the image processing program. The functions here have been designed
 * to give control to the controller, and the primary operation for the controller to function
 * (process an image processing tasks).
 */
public interface PerformingImageTasks {

  /**
   * Start the program, i.e. give control to the controller
   *
   * @param modelObj Operations to perform in image
   * @throws IOException exception when invalid type is found in file read.
   */
  void startControl(ImageModal modelObj) throws IOException;

}
