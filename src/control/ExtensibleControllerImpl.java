package control;

import model.imagemodel.ImageModal;
import view.ImageView;

public class ExtensibleControllerImpl extends Controller implements ExtensibleController{

  /**
   * Creates a new Controller instance with the given ImageModal and ImageView.
   *
   * @param imageModal the ImageModal instance to use
   * @param imageView  the ImageView instance to use
   */
  public ExtensibleControllerImpl(ImageModal imageModal, ImageView imageView) {
    super(imageModal, imageView);
  }

  @Override
  public void mosckImage(int seeds) {
    performAction("mosaic " + seeds);
  }
}
