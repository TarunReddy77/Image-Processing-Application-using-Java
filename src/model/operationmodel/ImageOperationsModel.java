package model.operationmodel;

import java.io.IOException;
import model.imageviewmodel.ReadModel;

/**
 * This interface specifies the different operations to perform on image.
 */
public interface ImageOperationsModel extends ReadModel {

  /**
   * Flip an image vertically or horizontally to create a new image, referred to henceforth by the
   * given destination name.
   *
   * @param horizontal   type of flip vertically or horizontally.
   * @param imageToUse   image to perform this operation.
   * @param newImageName store the new image from by the operation.
   * @throws IOException exception when invalid file path or image name in file read.
   */
  void flip(Boolean horizontal, String imageToUse, String newImageName) throws IOException;

  /**
   * Convert the image into greyscale with the given name. And refer to it henceforth in the program
   * by the given destination name. Similar commands for red, green, blue, value, luma, intensity
   * components should be supported.
   *
   * @param component    type of greyscale to perform.
   * @param imageToUse   image to perform this operation.
   * @param newImageName store the new image from by the operation.
   * @throws IOException exception when invalid gray scale type in file read.
   */
  void greyscale(String component, String imageToUse, String newImageName) throws IOException;

  /**
   * Split the given image into three greyscale images containing its red, green and blue components
   * respectively.
   *
   * @param imageToUse     image to perform this operation.
   * @param redImageName   store the new red image from by the operation.
   * @param greenImageName store the new green image from by the operation.
   * @param blueImageName  store the new blue image from by the operation.
   * @throws IOException exception when invalid file path or image name in file read.
   */
  void splitRGB(String imageToUse, String redImageName, String greenImageName,
      String blueImageName) throws IOException;

  /**
   * Combine the three greyscale images into a single image that gets its red, green and blue
   * components from the three images respectively.
   *
   * @param newImageName   store the new image from by the operation.
   * @param redImageName   red image to perform this operation.
   * @param greenImageName green image to perform this operation.
   * @param blueImageName  blue image to perform this operation.
   * @throws IOException exception when invalid type is found in file read.
   */
  void combineRGB(String newImageName, String redImageName, String greenImageName,
      String blueImageName) throws IOException;

  /**
   * Brightness the image by the given increment to create a new image, referred to henceforth by
   * the given destination name. The increment may be positive (brightening) or negative
   * (darkening).
   *
   * @param component    increment to do in image.
   * @param imageToUse   image to perform this operation.
   * @param newImageName store the new image from by the operation.
   * @throws IOException exception when invalid file path or image name in file read.
   */
  void brightness(int component, String imageToUse, String newImageName) throws IOException;

}
