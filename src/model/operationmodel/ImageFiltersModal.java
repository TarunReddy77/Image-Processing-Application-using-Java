package model.operationmodel;

import java.io.IOException;

/**
 * The ImageFiltersModal interface defines the operations for applying filters on images. This
 * interface extends the ImageOperationsModel interface, which provides basic image operations.
 */
public interface ImageFiltersModal extends ImageOperationsModel {

  /**
   * Applies a blur filter on the specified image and stores the result in a new image file with the
   * specified name.
   *
   * @param imageToUse   image to perform this operation.
   * @param newImageName store the new image from by the operation.
   * @throws IOException exception when invalid file path or image name in file read.
   */
  void blur(String imageToUse, String newImageName) throws IOException;

  /**
   * Applies a sharpening filter on the specified image and stores the result in a new image file
   * with the specified name.
   *
   * @param imageToUse   image to perform this operation.
   * @param newImageName store the new image from by the operation.
   * @throws IOException exception when invalid file path or image name in file read.
   */
  void sharpening(String imageToUse, String newImageName) throws IOException;

  /**
   * Applies a sepia filter on the specified image and stores the result in a new image file with
   * the specified name.
   *
   * @param imageToUse   image to perform this operation.
   * @param newImageName store the new image from by the operation.
   * @throws IOException exception when invalid file path or image name in file read.
   */
  void sepia(String imageToUse, String newImageName) throws IOException;

  /**
   * Applies a dithering filter on the specified image and stores the result in a new image file *
   * with the specified name.
   *
   * @param imageToUse   image to perform this operation.
   * @param newImageName store the new image from by the operation.
   * @throws IOException exception when invalid file path or image name in file read.
   */
  void dithering(String imageToUse, String newImageName) throws IOException;

}
