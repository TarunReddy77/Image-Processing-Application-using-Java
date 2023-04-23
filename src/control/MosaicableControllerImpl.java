package control;

public class MosaicableControllerImpl implements MosaicableController {
  private final IController controller;

  public MosaicableControllerImpl(IController controller) {
    this.controller = controller;
  }

  @Override
  public void mosaicImage(int numOfSeeds) {
    controller.performAction("mosaic " + numOfSeeds);
  }

  @Override
  public void setView() {
    controller.setView();
  }

  @Override
  public void loadImage(String loadPath) {
    controller.loadImage(loadPath);
  }

  @Override
  public void horizontalImage() {
    controller.horizontalImage();
  }

  @Override
  public void verticalImage() {
    controller.verticalImage();
  }

  @Override
  public void greyscaleImage() {
    controller.verticalImage();
  }

  @Override
  public void sepiaImage() {
    controller.sepiaImage();
  }

  @Override
  public void blurImage() {
    controller.blurImage();
  }

  @Override
  public void sharpImage() {
    controller.sharpImage();
  }

  @Override
  public void dither() {
    controller.dither();
  }

  @Override
  public void intensityImage() {
    controller.intensityImage();
  }

  @Override
  public void lumaImage() {
    controller.lumaImage();
  }

  @Override
  public void valueImage() {
    controller.valueImage();
  }

  @Override
  public void redImage() {
    controller.redImage();
  }

  @Override
  public void greenImage() {
    controller.greenImage();
  }

  @Override
  public void blueImage() {
    controller.blueImage();
  }

  @Override
  public void brightnessImage(int value) {
    controller.brightnessImage(value);
  }

  @Override
  public void combineImage(String loadPath, int uniqueID) {
    controller.combineImage(loadPath, uniqueID);
  }

  @Override
  public void rgbSplit() {
    controller.rgbSplit();
  }

  @Override
  public void rgbSplitImageShow(int uniqueID) {
    controller.rgbSplitImageShow(uniqueID);
  }

  @Override
  public void saveImage(String savePath) {
    controller.saveImage(savePath);
  }

  @Override
  public void performAction(String command) {
    controller.performAction(command);
  }
}
