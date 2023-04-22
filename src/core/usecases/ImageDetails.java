package core.usecases;

import java.util.Arrays;
import java.util.Objects;

/**
 * ImageDetails class will store the detail of original image in which image processing will  be
 * performed.
 */
public class ImageDetails implements GetImageDetails {

  private final int width;
  private final int height;
  private final int maxValue;
  private final Pixel[][] pixels;

  /**
   * Constructs a ImageDetails in terms of imageData, which will initialize the original image
   * details.
   *
   * @param width    width of new image.
   * @param height   height of new image.
   * @param maxValue maxvalue of new image.
   * @param pixels   pixels of new image.
   */
  public ImageDetails(int width, int height, int maxValue, Pixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixels = pixels;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public Pixel getPixelValue(int i, int j) {
    return pixels[i][j];
  }

  @Override
  public Pixel[][] getPixelMatrix() {
    return pixels;
  }

  @Override
  public String toString() {
    StringBuilder imageDataInString = new StringBuilder();
    imageDataInString.append(width).append(" ")
        .append(height).append("\n")
        .append(maxValue).append("\n");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imageDataInString.append(pixels[i][j].toString());
      }
    }
    return imageDataInString.toString();
  }

  @Override
  public boolean equals(Object o) {

    if (o == this) {
      return true;
    }

    if (!(o instanceof ImageDetails)) {
      return false;
    }

    ImageDetails c = (ImageDetails) o;

    return this.toString().equals(c.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.width, this.maxValue, Arrays.deepHashCode(this.pixels));
  }
}
