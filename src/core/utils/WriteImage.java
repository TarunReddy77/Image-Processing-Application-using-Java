package core.utils;

import core.usecases.GetImageDetails;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;


/**
 * This class implements the ImageReadWrite interface and provides methods for writing image data to
 * a file. The supported image formats include ppm, png, jpg, jpeg, and bmp.
 */
public class WriteImage {

  private final GetImageDetails imageData;

  /**
   * Constructs a new WriteImage object with the specified GetImageDetails object and image file
   * path.
   *
   * @param imageData the GetImageDetails object containing the image data to be written
   * @param imagePath the file path of the image to be written
   * @throws IOException if an I/O error occurs while writing the image file
   */
  public WriteImage(GetImageDetails imageData, String imagePath) throws IOException {
    this.imageData = imageData;
    String imageExtension = getFileExtension(imagePath);

    switch (imageExtension.toLowerCase()) {
      case "ppm":
        writePPM(imagePath);
        break;
      case "png":
      case "jpg":
      case "jpeg":
      case "bmp":
        writeImageIO(imageExtension, imagePath);
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
   * Writes the image data to a ppm file at the given file path using a PrintWriter.
   *
   * @param imagePath the file path of the ppm file to be written
   * @throws IOException if an I/O error occurs while writing the ppm file
   */
  private void writePPM(String imagePath) throws IOException {

    PrintWriter saveWrite;
    try {
      saveWrite = new PrintWriter(new FileWriter(imagePath));
    } catch (IOException e) {
      throw new IOException("Filepath not found !!");
    }
    saveWrite.write("P3" + "\n" + this.imageData.toString());
    saveWrite.close();
  }

  /**
   * Writes the image data to a file at the given file path using ImageIO library. The file format
   * is determined by the imageExtension parameter.
   *
   * @param imageExtension the format of the file to be written (png, jpg, jpeg, or bmp)
   * @param imagePath      the file path of the image file to be written
   * @throws IOException if an I/O error occurs while writing the image file
   */
  private void writeImageIO(String imageExtension, String imagePath) throws IOException {

    BufferedImage saveImage = new BufferedImage(this.imageData.getWidth(),
        this.imageData.getHeight(), BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < this.imageData.getHeight(); i++) {
      for (int j = 0; j < this.imageData.getWidth(); j++) {
        Color rgba = this.imageData.getPixelValue(i, j).colorInstance();
        saveImage.setRGB(j, i, rgba.getRGB());
      }
    }
    try {
      ImageIO.write(saveImage, imageExtension, new File(imagePath));
    } catch (IOException e) {
      throw new IOException("Filepath not found !!");
    }
  }

}
