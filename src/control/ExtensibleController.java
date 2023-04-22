package control;

public interface ExtensibleController extends IController{

  /**
   * Adjusts the brightness of the loaded image.
   *
   * @param seeds the value to adjust brightness by
   */
  void mosckImage(int seeds);
}
