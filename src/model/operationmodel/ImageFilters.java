package model.operationmodel;

import core.usecases.GetImageDetails;
import core.usecases.ImageDetails;
import core.usecases.ImagePixel;
import core.usecases.Pixel;
import java.io.IOException;
import java.util.function.BiFunction;

/**
 * The ImageFilters class is an implementation of the ImageFiltersModal interface that provides
 * operations for applying various filters on images. This class extends the ImageOperations class,
 * which provides basic image operations.
 */
public class ImageFilters extends ImageOperations implements ImageFiltersModal {

  /**
   * Constructs a ImageFilters to initiate image data.
   */
  public ImageFilters() {
    super();
  }

  /**
   * Applies the given kernel matrix to the input image and generates a new image with the processed
   * pixels.
   *
   * @param imageToUse   The name of the input image file to be processed.
   * @param newImageName The name of the new image file to be generated after processing.
   * @param kernel       The kernel matrix to be applied to each pixel of the input image.
   * @throws IOException if there is an error while reading or writing the image files.
   */
  private void imageKernelMultiple(String imageToUse, String newImageName, double[][] kernel)
      throws IOException {

    GetImageDetails imageData = checkImageContainOrNot(imageToUse);
    int height = imageData.getHeight();
    int width = imageData.getWidth();

    BiFunction<Integer, Integer, Pixel> pixelFunction = (i, j) -> {
      double redValue = 0;
      double greenValue = 0;
      double blueValue = 0;
      int kernelLength = kernel.length / 2;
      Pixel rgb;

      for (int k = i - kernelLength, x = 0; k <= i + kernelLength; k++, x++) {
        for (int l = j - kernelLength, y = 0; l <= j + kernelLength; l++, y++) {
          try {
            rgb = imageData.getPixelValue(k, l);
          } catch (Exception e) {
            continue;
          }
          if ((k >= 0 && k < height) && (l >= 0 && l < width)) {
            redValue += (rgb.getRed() * kernel[x][y]);
            greenValue += (rgb.getGreen() * kernel[x][y]);
            blueValue += (rgb.getBlue() * kernel[x][y]);
          }
        }
      }
      redValue = numberMinMax(redValue);
      greenValue = numberMinMax(greenValue);
      blueValue = numberMinMax(blueValue);

      return new ImagePixel(convertIntoInt(redValue), convertIntoInt(greenValue),
          convertIntoInt(blueValue));
    };

    imageDatas.put(newImageName, new ImageDetails(width, height, imageData.getMaxValue(),
        forLoopContains(height, width, pixelFunction)));
  }

  @Override
  public void blur(String imageToUse, String newImageName) throws IOException {

    double[][] kernel = new double[][]{
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};

    imageKernelMultiple(imageToUse, newImageName, kernel);
  }

  @Override
  public void sharpening(String imageToUse, String newImageName) throws IOException {

    double[][] kernel = new double[][]{
        {-0.125, -0.125, -0.125, -0.125, -0.125,},
        {-0.125, 0.25, 0.25, 0.25, -0.125,},
        {-0.125, 0.25, 1.00, 0.25, -0.125,},
        {-0.125, 0.25, 0.25, 0.25, -0.125,},
        {-0.125, -0.125, -0.125, -0.125, -0.125,}};

    imageKernelMultiple(imageToUse, newImageName, kernel);
  }

  @Override
  public void sepia(String imageToUse, String newImageName) throws IOException {

    GetImageDetails imageData = checkImageContainOrNot(imageToUse);
    int height = imageData.getHeight();
    int width = imageData.getWidth();
    int maxValue = imageData.getMaxValue();

    BiFunction<Integer, Integer, Pixel> pixelFunction = (i, j) -> imageData.getPixelValue(i, j)
        .greyscalePixelValue("sepia-tone");

    Pixel[][] newPixel = forLoopContains(height, width, pixelFunction);

    imageDatas.put(newImageName, new ImageDetails(width, height, maxValue, newPixel));
  }

  @Override
  public void dithering(String imageToUse, String newImageName) throws IOException {

    super.greyscale("luma-component", imageToUse, newImageName);
    GetImageDetails imageData = checkImageContainOrNot(newImageName);

    int height = imageData.getHeight();
    int width = imageData.getWidth();
    int maxValue = imageData.getMaxValue();
    Pixel[][] oldPixels = imageData.getPixelMatrix();

    BiFunction<Integer, Integer, Pixel> pixelFunction = (i, j) -> {
      int oldValue = oldPixels[i][j].getRed();
      int newValue = closestValue(oldValue, maxValue);
      int error = oldValue - newValue;

      if (j + 1 < width) {
        oldPixels[i][j + 1] = oldPixels[i][j + 1].ditherErrorAdd(0.4375, error);
      }
      if (i + 1 < height && j - 1 >= 0) {
        oldPixels[i + 1][j - 1] = oldPixels[i + 1][j - 1].ditherErrorAdd(0.1875, error);
      }
      if (i + 1 < height) {
        oldPixels[i + 1][j] = oldPixels[i + 1][j].ditherErrorAdd(0.3125, error);
      }
      if (i + 1 < height && j + 1 < width) {
        oldPixels[i + 1][j + 1] = oldPixels[i + 1][j + 1].ditherErrorAdd(0.0625, error);
      }

      return new ImagePixel(newValue, newValue, newValue);
    };

    imageDatas.put(newImageName, new ImageDetails(width, height, maxValue,
        forLoopContains(height, width, pixelFunction)));
  }

}
