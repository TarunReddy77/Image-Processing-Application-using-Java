package core.utils;

import core.usecases.GetImageDetails;
import core.usecases.ImageDetails;
import core.usecases.ImagePixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * The ReadImage class implements the ImageReadWrite interface, and provides methods for reading
 * image details from different file formats (currently supports PPM, PNG, JPG, and BMP formats).
 */
public class ReadImage {

  private final GetImageDetails imageDetails;

  /**
   * Constructs an ImageReader object and reads image details from the specified file path.
   *
   * @param imagePath the path to the image file to be read.
   * @throws IOException if an I/O error occurs while reading the image file and the image format is
   *                     not supported.
   */
  public ReadImage(String imagePath) throws IOException {

    String imageExtension = getFileExtension(imagePath);

    switch (imageExtension.toLowerCase()) {
      case "ppm":
        this.imageDetails = readPPM(imagePath);
        break;
      case "png":
      case "jpg":
      case "jpeg":
      case "bmp":
        this.imageDetails = readImageIO(imagePath);
        break;
      default:
        throw new IOException("Unsupported image format: " + imageExtension);
    }
  }

  /**
   * Returns the extension of the specified file.
   *
   * @param imagePath the file whose extension is to be returned.
   * @return the extension of the specified file.
   */
  private static String getFileExtension(String imagePath) {
    int lastDotIndex = imagePath.lastIndexOf(".");
    if (lastDotIndex > 0 && lastDotIndex < imagePath.length() - 1) {
      return imagePath.substring(lastDotIndex + 1).toLowerCase();
    }
    return "";
  }

  /**
   * Returns an InputStream object for the specified image file path.
   *
   * @param imagePath the path to the image file
   * @return an InputStream object for reading the image file
   * @throws IOException if the image file cannot be opened or read
   */
  private InputStream getInputStream(String imagePath) throws IOException {
    try {
      File imageFile = new File(imagePath);
      return new FileInputStream(imageFile);
    } catch (Exception e) {
      throw new IOException("File \"" + imagePath + "\" not found !!");
    }
  }

  /**
   * Throws an IOException with a message if the specified condition is true.
   *
   * @param errorName the name of the error or value that caused the error
   * @param condition the condition to check for an error
   * @throws IOException if the condition is true
   */
  private void imageReadException(String errorName, boolean condition) throws IOException {
    if (condition) {
      throw new IOException("Image " + errorName + " value are inappropriate!!");
    }
  }

  /**
   * Reads image details from a PPM file.
   *
   * @param imagePath the PPM image path to be read.
   * @return an ImageDetails object containing the image details.
   * @throws IOException if an I/O error occurs while reading the image file.
   */
  private ImageDetails readPPM(String imagePath) throws IOException {
    Scanner readPPM = new Scanner(getInputStream(imagePath));

    Appendable builder = new StringBuilder();

    while (readPPM.hasNextLine()) {
      String s = readPPM.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    readPPM = new Scanner(builder.toString());
    try {
      String token = readPPM.next();
      imageReadException("token", !token.equals("P3"));
      int width = readPPM.nextInt();
      imageReadException("width", width < 0);
      int height = readPPM.nextInt();
      imageReadException("height", height < 0);
      int maxValue = readPPM.nextInt();
      imageReadException("maxValue", maxValue < 0);

      ImagePixel[][] pixels = new ImagePixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = readPPM.nextInt();
          int g = readPPM.nextInt();
          int b = readPPM.nextInt();
          imageReadException("pixel",
              ((r > maxValue || r < 0) || (g > maxValue || g < 0) || (b > maxValue || b < 0)));
          pixels[i][j] = new ImagePixel(r, g, b);
        }
      }
      imageReadException("pixel", readPPM.hasNext());
      return new ImageDetails(width, height, maxValue, pixels);
    } catch (NoSuchElementException e) {
      throw new IOException("Pixel not found!!!");
    }

  }

  /**
   * Reads image details from a PNG, JPG, BMP file.
   *
   * @param imagePath the PPM image path to be read.
   * @return an ImageDetails object containing the image details.
   * @throws IOException if an I/O error occurs while reading the image file.
   */
  private ImageDetails readImageIO(String imagePath) throws IOException {

    BufferedImage readImage = ImageIO.read(getInputStream(imagePath));

    try {
      int width = readImage.getWidth();
      imageReadException("width", width < 0);
      int height = readImage.getHeight();
      imageReadException("height", height < 0);
      int maxValue = 255;

      ImagePixel[][] pixels = new ImagePixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color rgba = new Color(readImage.getRGB(j, i));
          int r = rgba.getRed();
          int g = rgba.getGreen();
          int b = rgba.getBlue();
          imageReadException("pixel",
              ((r > maxValue || r < 0) || (g > maxValue || g < 0) || (b > maxValue || b < 0)));
          pixels[i][j] = new ImagePixel(r, g, b);
        }
      }
      return new ImageDetails(width, height, maxValue, pixels);
    } catch (NoSuchElementException e) {
      throw new IOException("Pixel not found!!!");
    }
  }

  /**
   * Returns the GetImageDetails object containing the image data associated with this
   * ImageReadWrite instance.
   *
   * @return the GetImageDetails object containing the image data
   */
  public GetImageDetails getImageDetails() {
    return imageDetails;
  }
}
