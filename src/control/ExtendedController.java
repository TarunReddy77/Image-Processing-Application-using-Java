package control;

public interface ExtendedController extends IController{

  /**
   * Adjusts the brightness of the loaded image.
   *
   * @param seeds the value to adjust brightness by
   */
  void mosaicImage(int seeds);
}
