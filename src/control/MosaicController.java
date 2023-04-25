package control;

/**
 * This interface represents a controller that has the functionality to perform mosaicking on an
 * image.
 */
public interface MosaicController extends IController{

  /**
   * Perform mosaicking on an image.
   *
   * @param numOfSeeds the number of seeds to use for mosaicking
   */
  void mosaicImage(int numOfSeeds);
}
