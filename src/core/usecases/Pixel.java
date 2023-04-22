package core.usecases;

import java.awt.Color;

/**
 * An interface for representing a pixel in an image. A pixel is defined by its red, green, blue,
 * and alpha color channels, and can be converted to various types of grayscale or brightness values
 * using the provided methods.
 */
public interface Pixel {

  /**
   * Returns the red color channel of the pixel.
   *
   * @return the red color channel of the pixel.
   */
  int getRed();

  /**
   * Returns the green color channel of the pixel.
   *
   * @return the green color channel of the pixel.
   */
  int getGreen();

  /**
   * Returns the blue color channel of the pixel.
   *
   * @return the blue color channel of the pixel.
   */
  int getBlue();


  /**
   * Get an instance of this pixel as a Pixel object.
   *
   * @return An instance of this pixel as a Pixel object.
   */
  Pixel imagePixelInstense();


  /**
   * Get an instance of this pixel as a Color object.
   *
   * @return An instance of this pixel as a Color object.
   */
  Color colorInstance();

  /**
   * Converts the pixel to a grayscale value of the specified type, such as luminance or average.
   *
   * @param component the type of grayscale required.
   * @return the pixel value of the specified grayscale type.
   */
  Pixel greyscalePixelValue(String component);

  /**
   * Adjusts the brightness of the pixel by the specified amount.
   *
   * @param component the amount by which to adjust the brightness.
   * @return the new pixel with the adjusted brightness value.
   */
  Pixel brightnessPixelValue(int component);

  /**
   * Create a new ImagePixel object with the specified error value added to each color component.
   *
   * @param value The value to multiply the error by before adding to the color component.
   * @param error The error value to add to the color component.
   * @return A new ImagePixel object with the modified color components.
   */
  Pixel ditherErrorAdd(double value, int error);
}
