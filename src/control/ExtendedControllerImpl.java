package control;

import model.imagemodel.ImageModal;
import view.ImageView;

public class ExtendedControllerImpl extends Controller implements ExtendedController {

  /**
   * Creates a new Controller instance with the given ImageModal and ImageView.
   *
   * @param imageModal the ImageModal instance to use
   * @param imageView  the ImageView instance to use
   */
  public ExtendedControllerImpl(ImageModal imageModal, ImageView imageView) {
    super(imageModal, imageView);
  }

  @Override
  public void mosaicImage(int seeds) {
    performAction("mosaic " + seeds);
  }
}
