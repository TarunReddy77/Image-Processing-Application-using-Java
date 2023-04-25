package model.operationmodel;

import java.io.IOException;
import java.util.Random;

import core.usecases.GetImageDetails;
import core.usecases.ImageDetails;
import core.usecases.ImagePixel;
import core.usecases.Pixel;

/**
 * This class provides the implementation for the mosaicking operation to be performed on an image.
 */
public class ImageMosaicImpl extends ImageFilters implements ImageMosaic {

  @Override
  public void imageMosaic(int numSeeds, String imageToUse, String newImageName) throws IOException {

    GetImageDetails imageData = checkImageContainOrNot(imageToUse);
    int height = imageData.getHeight();
    int width = imageData.getWidth();
    int maxValue = imageData.getMaxValue();

    Random random = new Random();
    int[][] seedLocations = new int[numSeeds][2];
    for (int i = 0; i < numSeeds; i++) {
      seedLocations[i][0] = random.nextInt(height);
      seedLocations[i][1] = random.nextInt(width);
    }

    int[][] clusterLabels = new int[height][width];
    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        double minDistance = Double.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < numSeeds; i++) {
          double distance = Math.sqrt(Math.pow(h - seedLocations[i][0], 2) + Math.pow(w - seedLocations[i][1], 2));
          if (distance < minDistance) {
            minDistance = distance;
            minIndex = i;
          }
        }
        clusterLabels[h][w] = minIndex;
      }
    }

    int[][] clusterColors = new int[numSeeds][3];
    for (int i = 0; i < numSeeds; i++) {
      int count = 0;
      int redTotal = 0, greenTotal = 0, blueTotal = 0;
      for (int h = 0; h < height; h++) {
        for (int w = 0; w < width; w++) {
          if (clusterLabels[h][w] == i) {
            Pixel pixel = imageData.getPixelValue(h, w);
            redTotal += pixel.getRed();
            greenTotal += pixel.getGreen();
            blueTotal += pixel.getBlue();
            count++;
          }
        }
      }
      if (count > 0) {
        clusterColors[i][0] = redTotal / count;
        clusterColors[i][1] = greenTotal / count;
        clusterColors[i][2] = blueTotal / count;
      }
    }

    Pixel[][] newPixelMatrix = new ImagePixel[height][width];
    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        int clusterIndex = clusterLabels[h][w];
        int red = clusterColors[clusterIndex][0];
        int green = clusterColors[clusterIndex][1];
        int blue = clusterColors[clusterIndex][2];
        newPixelMatrix[h][w] = new ImagePixel(red, green, blue);
      }
    }
    imageDatas.put(newImageName, new ImageDetails(width, height, maxValue, newPixelMatrix));
  }
}
