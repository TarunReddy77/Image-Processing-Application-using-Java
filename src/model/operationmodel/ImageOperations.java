package model.operationmodel;

import core.usecases.GetImageDetails;
import core.usecases.ImageDetails;
import core.usecases.ImagePixel;
import core.usecases.Pixel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import model.AbstractImageProcess;

/**
 * Created ImageOperations class which perform different tasks  related to image processing.
 */
public class ImageOperations extends AbstractImageProcess implements ImageOperationsModel {

  /**
   * Constructs a ImageOperations to initiate image data as a HashMap.
   */
  public ImageOperations() {
    super();
  }

  @Override
  public HashMap<String, GetImageDetails> getImageData() {
    return imageDatas;
  }

  @Override
  public void flip(Boolean horizontal, String imageToUse, String newImageName) throws IOException {

    GetImageDetails imageData = checkImageContainOrNot(imageToUse);

    BiFunction<Integer, Integer, Pixel> pixelFunction = (i, j) -> {
      if (horizontal) {
        int y = imageData.getWidth() - j - 1;
        return imageData.getPixelValue(i, y).imagePixelInstense();
      } else {
        int x = imageData.getHeight() - i - 1;
        return imageData.getPixelValue(x, j).imagePixelInstense();
      }
    };

    imageDatas.put(newImageName,
        new ImageDetails(imageData.getWidth(), imageData.getHeight(), imageData.getMaxValue(),
            forLoopContains(imageData.getHeight(), imageData.getWidth(), pixelFunction)));
  }


  @Override
  public void greyscale(String component, String imageToUse, String newImageName)
      throws IOException {

    GetImageDetails imageData = checkImageContainOrNot(imageToUse);

    BiFunction<Integer, Integer, Pixel> pixelFunction = (i, j) -> imageData.getPixelValue(i, j)
        .greyscalePixelValue(component);

    imageDatas.put(newImageName,
        new ImageDetails(imageData.getWidth(), imageData.getHeight(), imageData.getMaxValue(),
            forLoopContains(imageData.getHeight(), imageData.getWidth(), pixelFunction)));
  }

  @Override
  public void splitRGB(String imageToUse, String redImageName, String greenImageName,
      String blueImageName) throws IOException {
    greyscale("red-component", imageToUse, redImageName);
    greyscale("green-component", imageToUse, greenImageName);
    greyscale("blue-component", imageToUse, blueImageName);
  }

  @Override
  public void combineRGB(String newImageName, String redImageName, String greenImageName,
      String blueImageName) throws IOException {

    List<GetImageDetails> imageData = new ArrayList<>(Arrays.asList(
        checkImageContainOrNot(redImageName),
        checkImageContainOrNot(greenImageName),
        checkImageContainOrNot(blueImageName)
    ));

    checkHaveSameDetailsToCombine(imageData);

    BiFunction<Integer, Integer, Pixel> pixelFunction = (i, j) -> {
      int r = imageData.get(0).getPixelValue(i, j).getRed();
      int g = imageData.get(1).getPixelValue(i, j).getGreen();
      int b = imageData.get(2).getPixelValue(i, j).getBlue();
      return new ImagePixel(r, g, b);
    };

    imageDatas.put(newImageName,
        new ImageDetails(imageData.get(0).getWidth(), imageData.get(0).getHeight(),
            imageData.get(0).getMaxValue(),
            forLoopContains(imageData.get(0).getHeight(), imageData.get(0).getWidth(),
                pixelFunction)));
  }

  @Override
  public void brightness(int component, String imageToUse, String newImageName) throws IOException {

    GetImageDetails imageData = checkImageContainOrNot(imageToUse);

    BiFunction<Integer, Integer, Pixel> pixelFunction = (i, j) -> imageData.getPixelValue(i, j)
        .brightnessPixelValue(component);

    imageDatas.put(newImageName,
        new ImageDetails(imageData.getWidth(), imageData.getHeight(), imageData.getMaxValue(),
            forLoopContains(imageData.getHeight(), imageData.getWidth(), pixelFunction)));
  }

}
