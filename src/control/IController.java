package control;

/**
 * The IController interface represents the contract for all controllers in this package. It
 * specifies a set of methods that can be used to manipulate images and interact with the user
 * interface.
 */
public interface IController {

  /**
   * Sets the view for the controller.
   */
  void setView();

  /**
   * Loads an image from the given file path.
   *
   * @param loadPath the file path of the image to load
   */
  void loadImage(String loadPath);

  /**
   * Flips the loaded image horizontally.
   */
  void horizontalImage();

  /**
   * Flips the loaded image vertically.
   */
  void verticalImage();

  /**
   * Converts the loaded image to greyscale.
   */
  void greyscaleImage();

  /**
   * Applies a sepia tone filter to the loaded image.
   */
  void sepiaImage();

  /**
   * Applies a blur filter to the loaded image.
   */
  void blurImage();

  /**
   * Applies a sharped filter to the loaded image.
   */
  void sharpImage();

  /**
   * Applies a dithering effect to the loaded image.
   */
  void dither();

  /**
   * Adjusts the intensity of the loaded image.
   */
  void intensityImage();

  /**
   * Converts the loaded image to luma.
   */
  void lumaImage();

  /**
   * Converts the loaded image to value.
   */
  void valueImage();

  /**
   * Separates the red component of the loaded image.
   */
  void redImage();

  /**
   * Separates the green component of the loaded image.
   */
  void greenImage();

  /**
   * Separates the blue component of the loaded image.
   */
  void blueImage();

  /**
   * Adjusts the brightness of the loaded image.
   *
   * @param value the value to adjust brightness by
   */
  void brightnessImage(int value);

  /**
   * Combines the loaded image with another image from the given file path.
   *
   * @param loadPath the file path of the image to combine with
   * @param uniqueID a unique identifier for the combined image
   */
  void combineImage(String loadPath, int uniqueID);

  /**
   * Separates the red, green, and blue components of the loaded image and displays them
   * separately.
   */
  void rgbSplit();

  /**
   * Displays the RGB split image.
   *
   * @param uniqueID a unique identifier for the combined image
   */
  void rgbSplitImageShow(int uniqueID);

  /**
   * Saves the current state of the loaded image to the given file path.
   *
   * @param savePath the file path to save the image to
   */
  void saveImage(String savePath);
}