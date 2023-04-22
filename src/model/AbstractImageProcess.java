package model;

import core.usecases.GetImageDetails;
import core.usecases.ImagePixel;
import core.usecases.Pixel;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Abstract class help to store common code among the class to make application more re-usable.
 */
public abstract class AbstractImageProcess {

  protected HashMap<String, GetImageDetails> imageDatas;

  /**
   * Constructs a AbstractImageProcess to initiate image data as a HashMap.
   */
  public AbstractImageProcess() {
    imageDatas = new HashMap<>();
  }

  /**
   * Create a checkImageContainOrNot to check the correct path or does that path contain image.
   *
   * @param imageToUse image to perform this operation.
   * @return give the details of given image
   * @throws IOException exception when file or file path does not found.
   */
  protected GetImageDetails checkImageContainOrNot(String imageToUse) throws IOException {
    if (imageDatas.containsKey(imageToUse)) {
      return imageDatas.get(imageToUse);
    } else {
      throw new IOException("File name " + imageToUse + " not found!!!");
    }
  }


  /**
   * Returns the number, channel between 0 and 255.
   *
   * @param channel The number to channel.
   * @return The channel number.
   */
  protected double numberMinMax(double channel) {
    double number = Math.min(255, channel);
    number = Math.max(number, 0);
    return number;
  }

  /**
   * Convert a double value into an integer by rounding to the nearest integer.
   *
   * @param operation The double value to convert.
   * @return The converted integer value.
   */
  protected int convertIntoInt(double operation) {
    return (int) Math.round(operation);
  }


  /**
   * Creates a two-dimensional array of pixels using a for loop to iterate over each row and column
   * of the image.
   *
   * @param height        the height of the image in pixels
   * @param width         the width of the image in pixels
   * @param pixelFunction a function that maps pixel coordinates to pixel values
   * @return a two-dimensional array of pixels
   */
  protected Pixel[][] forLoopContains(int height, int width,
      BiFunction<Integer, Integer, Pixel> pixelFunction) {
    Pixel[][] pixels = new ImagePixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixels[i][j] = pixelFunction.apply(i, j);
      }
    }
    return pixels;
  }

  /**
   * Checks whether the given list of GetImageDetails objects have the same width, height, and max
   * value, which are required to combine the images into a single image. If any of these properties
   * are different, an IOException is thrown with a corresponding error message.
   *
   * @param imageData a list of GetImageDetails objects representing the images to be combined
   * @throws IOException if the width, height, or max value of the images are not the same
   */
  protected void checkHaveSameDetailsToCombine(List<GetImageDetails> imageData) throws IOException {
    if (imageData.get(0).getWidth() != imageData.get(1).getWidth()
        || imageData.get(1).getWidth() != imageData.get(2).getWidth()) {
      throw new IOException("Width of all combine image is not same!!");
    }
    if (imageData.get(0).getHeight() != imageData.get(1).getHeight()
        || imageData.get(1).getHeight() != imageData.get(2).getHeight()) {
      throw new IOException("Height of all combine image is not same!!");
    }
    if (imageData.get(0).getMaxValue() != imageData.get(1).getMaxValue()
        || imageData.get(1).getMaxValue() != imageData.get(2).getMaxValue()) {
      throw new IOException("MaxValue of all combine image is not same!!");
    }
  }

  /**
   * Calculates the closest value to maxValue based on the given channel value.
   *
   * @param channel  the channel value to be used in the calculation
   * @param maxValue the maximum value to be used in the calculation
   * @return the closest value to maxValue based on the given channel value
   */
  protected int closestValue(int channel, int maxValue) {
    if (channel > maxValue / 2) {
      return maxValue;
    } else {
      return 0;
    }
  }
}
