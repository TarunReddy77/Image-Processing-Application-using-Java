package model.operationmodel;

import java.io.IOException;

/**
 * This interface declares the functionality to perform mosaic operation on an image.
 */
public interface ImageMosaic extends ImageFiltersModal {

  /**
   * Method to perform mosaic operation on an image. An image can be "broken down" to look like a
   * collection of stained-glass pieces, by choosing a set of points in the image (called seeds).
   * Each pixel in the image is then paired to the seed that is closest to it (by distance). This
   * creates a cluster of pixels for each seed. Then the color of each pixel in the image is
   * replaced with the average color of its cluster.
   *
   * @param numSeeds the number of seeds
   * @param imageToUse the image on which the operation is to be performed
   * @param newImageName the name of the mosaicked image
   * @throws IOException when invalid file path or image name in file read
   */
  void imageMosaic(int numSeeds, String imageToUse, String newImageName) throws IOException;
}
