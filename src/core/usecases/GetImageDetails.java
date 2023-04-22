package core.usecases;

/**
 * The GetImageDetails interface for the image processing program. The functions here have been
 * designed to get/store detail of original image in which all image processing operation will be
 * performed.
 */
public interface GetImageDetails {

  /**
   * Get the width from the image file.
   *
   * @return width of image.
   */
  int getWidth();

  /**
   * Get the height from the image file.
   *
   * @return height of image.
   */
  int getHeight();

  /**
   * Get the max pixel value from the image file.
   *
   * @return max pixel value of image.
   */
  int getMaxValue();

  /**
   * Get the pixel value of the image file.
   *
   * @param i x index of 2d array.
   * @param j y index of 2d array.
   * @return pixel value of image at that index.
   */
  Pixel getPixelValue(int i, int j);

  /**
   * Get the all pixel value of the image file.
   *
   * @return all pixel value of image at that index.
   */
  Pixel[][] getPixelMatrix();
}
