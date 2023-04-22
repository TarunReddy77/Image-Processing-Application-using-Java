package mockcontroller;

import core.usecases.GetImageDetails;
import java.io.IOException;
import model.imagemodel.ImageModal;
import model.operationmodel.ImageFilters;

/**
 * Created ImageOperations class which perform different tasks  related to image processing.
 */
public class MockImageOperations extends ImageFilters implements ImageModal {

  private final StringBuilder log;

  /**
   * Constructs a ImageProcess to initiate image data.
   *
   * @param log the inputs which is passing as arguments.
   */
  public MockImageOperations(StringBuilder log) {
    this.log = log;
  }

  @Override
  public GetImageDetails saveImage(String imageName) throws IOException {
    log.append("Input: " + imageName + "\n");
    GetImageDetails imageData = imageDatas.get(imageName);
    return imageData;
  }

  @Override
  public void loadImage(GetImageDetails imageDetail, String newImageName) {
    imageDatas.put(newImageName, imageDetail);
    this.log.append("Input: " + imageDetail + " " + newImageName);
  }

  @Override
  public void flip(Boolean horizontal, String imageToUse, String newImageName) {
    log.append("Input: " + horizontal + " " + imageToUse + " " + newImageName + "\n");
  }

  @Override
  public void greyscale(String component, String imageToUse, String newImageName) {
    log.append("Input: " + component + " " + imageToUse + " " + newImageName + "\n");
  }

  @Override
  public void combineRGB(String newImageName, String redImageName, String greenImageName,
      String blueImageName) {
    log.append(
        "Input: " + newImageName + " " + redImageName + " " + greenImageName + " " + blueImageName
            + "\n");
  }

  @Override
  public void brightness(int component, String imageToUse, String newImageName) {
    log.append("Input: " + component + " " + imageToUse + " " + newImageName + "\n");
  }

  @Override
  public void blur(String imageToUse, String newImageName) throws IOException {
    log.append("Input: " + imageToUse + " " + newImageName + "\n");
  }

  @Override
  public void sharpening(String imageToUse, String newImageName) throws IOException {
    log.append("Input: " + imageToUse + " " + newImageName + "\n");
  }

  @Override
  public void sepia(String imageToUse, String newImageName) throws IOException {
    log.append("Input: " + imageToUse + " " + newImageName + "\n");
  }

  @Override
  public void dithering(String imageToUse, String newImageName) throws IOException {
    log.append("Input: " + imageToUse + " " + newImageName + "\n");
  }
}