package model.operationmodel;

import java.io.IOException;
import java.util.Random;

import core.usecases.GetImageDetails;
import core.usecases.ImageDetails;
import core.usecases.ImagePixel;
import core.usecases.Pixel;

public class ImageMosaicImpl extends ImageFilters implements ImageMosaic {

  @Override
  public void imageMosaic(int numSeeds, String imageToUse, String newImageName) throws IOException {

    GetImageDetails imageData = checkImageContainOrNot(imageToUse);
    int height = imageData.getHeight();
    int width = imageData.getWidth();
    int maxValue = imageData.getMaxValue();

    // Generate random seed locations
    Random random = new Random();
    int[][] seedLocations = new int[numSeeds][2];
    for (int i = 0; i < numSeeds; i++) {
      seedLocations[i][0] = random.nextInt(height);
      seedLocations[i][1] = random.nextInt(width);
    }

    // Assign each pixel to its closest seed
    int[][] clusterLabels = new int[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double minDistance = Double.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < numSeeds; i++) {
          double distance = Math.sqrt(Math.pow(y - seedLocations[i][0], 2) + Math.pow(x - seedLocations[i][1], 2));
          if (distance < minDistance) {
            minDistance = distance;
            minIndex = i;
          }
        }
        clusterLabels[y][x] = minIndex;
      }
    }

    // Calculate the average color for each cluster
    int[][] clusterColors = new int[numSeeds][3];
    for (int i = 0; i < numSeeds; i++) {
      int count = 0;
      int sumR = 0, sumG = 0, sumB = 0;
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          if (clusterLabels[y][x] == i) {
            Pixel pixel = imageData.getPixelValue(y, x);
            sumR += pixel.getRed();
            sumG += pixel.getGreen();
            sumB += pixel.getBlue();
            count++;
          }
        }
      }
      if (count > 0) {
        clusterColors[i][0] = sumR / count;
        clusterColors[i][1] = sumG / count;
        clusterColors[i][2] = sumB / count;
      }
    }

    // Replace each pixel with the average color of its cluster
    Pixel[][] newPixelMatrix = new ImagePixel[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int clusterIndex = clusterLabels[y][x];
        int red = clusterColors[clusterIndex][0];
        int green = clusterColors[clusterIndex][1];
        int blue = clusterColors[clusterIndex][2];
        newPixelMatrix[y][x] = new ImagePixel(red, green, blue);
      }
    }
    imageDatas.put(newImageName, new ImageDetails(width, height, maxValue, newPixelMatrix));
  }
}
