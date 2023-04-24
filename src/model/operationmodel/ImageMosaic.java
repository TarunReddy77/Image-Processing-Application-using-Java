package model.operationmodel;

import java.io.IOException;

public interface ImageMosaic extends ImageFiltersModal {

  void imageMosaic(int numSeeds, String imageToUse, String newImageName) throws IOException;
}
