package control;

import model.imagemodel.ImageModal;

/**
 * This interface defines the contract for an image controller, which is responsible for performing
 * different tasks related to image processing based on user requirements. Implementations of this
 * interface will provide the logic for the image processing tasks.
 */
public interface ImageController {

  /**
   * Performs the image processing task based on the provided ImageModal object.
   *
   * @param imageProcess The ImageModal object that represents the image to be processed.
   */
  void imageControllerTask(ImageModal imageProcess);
}
