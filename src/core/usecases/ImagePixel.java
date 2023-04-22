package core.usecases;

import java.awt.Color;
import java.util.Objects;

/**
 * The ImagePixel class represents a pixel in an image. The class also provides methods to convert
 * the pixel to grayscale, adjust its brightness and dither.
 */
public class ImagePixel implements Pixel {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructs an ImagePixel object with the specified red, green, and blue color channels and a
   * default alpha value of 255.
   *
   * @param red   the red color channel value for the pixel, ranging from 0 to 255.
   * @param green the green color channel value for the pixel, ranging from 0 to 255.
   * @param blue  the blue color channel value for the pixel, ranging from 0 to 255.
   */
  public ImagePixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Convert a double value into an integer by rounding to the nearest integer.
   *
   * @param operation The double value to convert.
   * @return The converted integer value.
   */
  private int convertIntoInt(double operation) {
    return (int) Math.round(operation);
  }

  /**
   * Returns the number, channel between 0 and 255.
   *
   * @param channel The number to channel.
   * @return The channel number.
   */
  private int numberMinMax(int channel) {
    int number = Math.min(255, channel);
    number = Math.max(number, 0);
    return number;
  }

  @Override
  public int getRed() {
    int red = this.red;
    return red;
  }

  @Override
  public int getGreen() {
    int green = this.green;
    return green;
  }

  @Override
  public int getBlue() {
    int blue = this.blue;
    return blue;
  }


  @Override
  public Pixel imagePixelInstense() {
    int r = this.red;
    int g = this.green;
    int b = this.blue;

    return new ImagePixel(r, g, b);
  }

  @Override
  public Color colorInstance() {
    int r = this.red;
    int g = this.green;
    int b = this.blue;

    return new Color(r, g, b);
  }

  @Override
  public Pixel greyscalePixelValue(String component) {
    int pixels = 0;

    switch (component) {
      case "sepia-tone":
        int red = numberMinMax(convertIntoInt(
            (0.393 * this.red) + (0.769 * this.green) + (0.189 * this.blue)));
        int green = numberMinMax(convertIntoInt(
            (0.349 * this.red) + (0.686 * this.green) + (0.168 * this.blue)));
        int blue = numberMinMax(convertIntoInt(
            (0.272 * this.red) + (0.534 * this.green) + (0.131 * this.blue)));

        return new ImagePixel(red, green, blue);

      case "value-component":
        pixels = Math.max(this.red, Math.max(this.green, this.blue));
        break;

      case "intensity-component":
        pixels = (this.red + this.green + this.blue) / 3;
        break;

      case "luma-component":
        pixels = convertIntoInt(
            (0.2126 * this.red) + (0.7152 * this.green) + (0.0722 * this.blue));
        break;

      case "red-component":
        pixels = this.red;
        break;

      case "green-component":
        pixels = this.green;
        break;

      case "blue-component":
        pixels = this.blue;
        break;

      default:
        break;
    }
    return new ImagePixel(pixels, pixels, pixels);
  }

  @Override
  public Pixel brightnessPixelValue(int component) {
    int r = numberMinMax(this.red + component);
    int g = numberMinMax(this.green + component);
    int b = numberMinMax(this.blue + component);

    return new ImagePixel(r, g, b);
  }

  @Override
  public Pixel ditherErrorAdd(double value, int error) {
    int red = convertIntoInt(this.red + (value * error));
    int green = convertIntoInt(this.green + (value * error));
    int blue = convertIntoInt(this.blue + (value * error));

    return new ImagePixel(red, green, blue);
  }

  @Override
  public String toString() {
    String result = this.red + " "
        + this.green + " "
        + this.blue + "\n";

    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ImagePixel)) {
      return false;
    }

    ImagePixel other = (ImagePixel) o;

    return Objects.equals(this.toString(), other.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }
}
