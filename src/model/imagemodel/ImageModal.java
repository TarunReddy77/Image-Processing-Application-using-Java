package model.imagemodel;

import core.usecases.GetImageDetails;
import java.io.IOException;

import model.operationmodel.ImageFiltersModal;
import model.operationmodel.ImageMosaic;

/**
 * Get the image from the controller and use it for operation to perform. And after complete store
 * the new image which is created by performing the operation. Image is in ppm format, will save the
 * new file as per the user request to make new file or overwrite it.
 */
public interface ImageModal extends ImageFiltersModal {

  /**
   * Save the image from the operation done on to make a new image.
   *
   * @param imageName the file name we have to use for storing that file to the path.
   * @throws IOException exception when invalid type is found in file save.
   */
  GetImageDetails saveImage(String imageName) throws IOException;

  /**
   * Load the image to further perform operation on it.
   *
   * @param imageDetails intense of the image details.
   * @param newImageName the file name we will use for future operation.
   */
  void loadImage(GetImageDetails imageDetails, String newImageName);
}
