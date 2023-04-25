package model.imagemodel;

import core.usecases.GetImageDetails;
import java.io.IOException;

import model.operationmodel.ImageFilters;
import model.operationmodel.ImageMosaicImpl;

/**
 * Created ImageProcess class which perform main tasks related to image processing like loading and
 * saving the images.
 */
public class ImageProcess extends ImageFilters implements ImageModal {

  /**
   * Constructs a ImageProcess to initiate image data.
   */
  public ImageProcess() {
    super();
  }

  @Override
  public GetImageDetails saveImage(String imageName) throws IOException {
    GetImageDetails imageData = imageDatas.getOrDefault(imageName, null);
    if (imageData == null) {
      throw new IOException("File name " + imageName + " not found!!!");
    }
    return imageData;
  }

  @Override
  public void loadImage(GetImageDetails imageDetails, String imageName) {
    imageDatas.put(imageName, imageDetails);
  }
}
