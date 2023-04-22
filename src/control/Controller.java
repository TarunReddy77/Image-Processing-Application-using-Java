package control;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import model.imagemodel.ImageModal;
import view.ImageView;

/**
 * The Controller class is responsible for controlling the image processing functionality and
 * interacting with the user interface. It implements the IController interface and extends the
 * ImageProcessingController class.
 */
public class Controller extends ImageProcessingController implements IController {

  private final ImageModal imageModal;
  private final ImageView imageView;
  private String imageName;
  private final HashMap<Integer, String> totalCombineImage;
  private final HashMap<Integer, String> totalSplitImage;

  /**
   * Creates a new Controller instance with the given ImageModal and ImageView.
   *
   * @param imageModal the ImageModal instance to use
   * @param imageView  the ImageView instance to use
   */
  public Controller(ImageModal imageModal, ImageView imageView) {
    super(new InputStreamReader(System.in), new StringBuffer());

    this.imageModal = imageModal;
    this.imageView = imageView;
    this.imageName = String.valueOf(imageModal.hashCode());
    totalCombineImage = new HashMap<>();
    totalSplitImage = new HashMap<>();
  }

  /**
   * Executes a series of actions on the image based on the command passed as a parameter. If an
   * error is found during the action execution, displays a pop-up message with the error message
   * and clears the screen and the list of split images.
   *
   * @param command a string containing the command to be executed on the image
   */
  void performAction(String command) {
    this.imageName = String.valueOf(imageModal.hashCode());
    super.in = new StringReader(command + " " + this.imageName + " " + this.imageName);
    actionPerform();
  }

  /**
   * Combines two images and displays the result on the image view.
   *
   * @param loadPath  the file path of the image to be loaded and combined with another image
   * @param uniqueID  the unique identifier of the image to be combined with
   * @param imageName the name of the image to be combined with
   */
  private void combineActionPerform(String loadPath, int uniqueID, String imageName) {
    super.in = new StringReader("load " + loadPath + " " + imageName);
    super.imageControllerTask(this.imageModal);
    String errorFound = super.out.toString();
    if (errorFound.equals("")) {
      this.imageView.loadCombineImage(imageName, uniqueID);
    } else {
      this.imageView.errorPopUp(errorFound);
      super.out = new StringBuilder();
    }
  }

  /**
   * Processes the action on the image. Then checks if any error message was generated during the
   * action execution. If no error is found, clears the screen and loads the image. If an error is
   * found, displays a pop-up message with the error message, clears the StringBuilder output and
   * the list of split images.
   */
  private void actionPerform() {
    super.imageControllerTask(this.imageModal);
    String errorFound = super.out.toString();
    if (errorFound.equals("")) {
      this.imageView.clearScreen();
      this.imageView.loadImage(this.imageName);
    } else {
      this.imageView.errorPopUp(errorFound);
      super.out = new StringBuilder();
      totalSplitImage.clear();
    }
  }

  @Override
  public void setView() {
    this.imageView.addFeatures(this);
  }

  @Override
  public void loadImage(String loadPath) {
    this.imageName = String.valueOf(imageModal.hashCode());
    super.in = new StringReader("load " + loadPath + " " + this.imageName);
    actionPerform();
  }

  @Override
  public void horizontalImage() {
    performAction("horizontal-flip");
  }

  @Override
  public void verticalImage() {
    performAction("vertical-flip");
  }

  @Override
  public void greyscaleImage() {
    performAction("greyscale luma-component");
  }

  @Override
  public void sepiaImage() {
    performAction("sepia");
  }

  @Override
  public void blurImage() {
    performAction("blur");
  }

  @Override
  public void sharpImage() {
    performAction("sharpen");
  }

  @Override
  public void dither() {
    performAction("dither");
  }

  @Override
  public void intensityImage() {
    performAction("greyscale intensity-component");
  }

  @Override
  public void lumaImage() {
    performAction("greyscale luma-component");
  }

  @Override
  public void valueImage() {
    performAction("greyscale value-component");
  }

  @Override
  public void redImage() {
    performAction("greyscale red-component");
  }

  @Override
  public void greenImage() {
    performAction("greyscale green-component");
  }

  @Override
  public void blueImage() {
    performAction("greyscale blue-component");
  }

  @Override
  public void brightnessImage(int value) {
    performAction("brighten " + value);
  }

  @Override
  public void combineImage(String loadPath, int uniqueID) {
    this.imageName = String.valueOf(imageModal.hashCode());
    String imageName = this.imageName + uniqueID;
    totalCombineImage.put(uniqueID, imageName);

    if (uniqueID == 0) {
      combineActionPerform(loadPath, uniqueID, imageName);
    }
    if (uniqueID == 1) {
      combineActionPerform(loadPath, uniqueID, imageName);
    }
    if (uniqueID == 2) {
      combineActionPerform(loadPath, uniqueID, imageName);
    }
    if (totalCombineImage.size() == 3) {
      super.in = new StringReader(
          "rgb-combine " + this.imageName + " " + totalCombineImage.get(0) + " "
              + totalCombineImage.get(1) + " " + totalCombineImage.get(2));
      actionPerform();
      totalCombineImage.clear();
    }
  }

  @Override
  public void rgbSplit() {
    this.imageName = String.valueOf(imageModal.hashCode());
    totalSplitImage.clear();
    totalSplitImage.put(0, this.imageName + 0);
    totalSplitImage.put(1, this.imageName + 1);
    totalSplitImage.put(2, this.imageName + 2);
    super.in = new StringReader("rgb-split " + this.imageName + " " + totalSplitImage.get(0)
        + " " + totalSplitImage.get(1) + " " + totalSplitImage.get(2));
    actionPerform();
  }

  @Override
  public void rgbSplitImageShow(int uniqueID) {
    if (totalSplitImage.size() == 3) {
      this.imageView.clearScreen();
      this.imageName = totalSplitImage.get(uniqueID);
      this.imageView.loadImage(this.imageName);
    } else {
      this.imageView.errorPopUp("Image Not Found!!!");
    }
  }

  @Override
  public void saveImage(String savePath) {
    super.in = new StringReader("save " + savePath + " " + this.imageName);
    super.imageControllerTask(imageModal);
    String errorFound = super.out.toString();
    if (!errorFound.equals("")) {
      imageView.errorPopUp(errorFound);
      super.out = new StringBuilder();
    }
  }
}
