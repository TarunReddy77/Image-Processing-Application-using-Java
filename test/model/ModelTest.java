package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import core.usecases.GetImageDetails;
import core.utils.ReadImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.imagemodel.ImageModal;
import model.imagemodel.ImageProcess;
import org.junit.Before;
import org.junit.Test;

/**
 * Created ModelTest class to test the Model before merge with the controller like a Mock Model
 * testing.
 */
public class ModelTest {


  List<GetImageDetails> actualFiles = new ArrayList<>();

  // image file to be use for compare the result file with the below true image.
  List<String> testCorrectFileName = new ArrayList<>(Arrays.asList(
      "snakes.ppm", //0
      "snakes-blue-greyscale.ppm", //1
      "snakes-brighter.ppm", //2
      "snakes-darken.ppm", //3
      "snakes-green-greyscale.ppm", //4
      "snakes-horizontal.ppm", //5
      "snakes-horizontal-vertical.ppm", //6
      "snakes-intensity-greyscale.ppm", //7
      "snakes-luma-greyscale.ppm", //8
      "snakes-red-greyscale.ppm", //9
      "snakes-value-greyscale.ppm", //10
      "snakes-vertical.ppm", //11
      "snakes-red-brighter-50.ppm", //12
      "snakes-change-snakes-red-50-combine.ppm", //13
      "snakes-sepia.ppm", //14
      "snakes-dither.ppm", //15
      "snakes-sharpen.ppm", //16
      "snakes-blur.ppm" //17
  ));

  @Before
  public void setUp() {
    for (String s : testCorrectFileName) {
      String imagePath = "test/assets/" + s;
      try {
        GetImageDetails imageData = new ReadImage(imagePath).getImageDetails();
        actualFiles.add(imageData);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Test
  public void testModelDataStore() {
    ImageModal imageProcess = new ImageProcess();

    try {

      // Checking image detail.
      imageProcess.getImageData().getOrDefault("snakes-briger", null);

      //load a new image.
      imageProcess.loadImage(new ReadImage("res/assets/snakes.ppm").getImageDetails(),
          "snakes");
      assertEquals(imageProcess.getImageData().get("snakes"), actualFiles.get(0));
      assertEquals(imageProcess.getImageData().size(), 1);

      try {
        imageProcess.loadImage(new ReadImage("res/assetsd/snakes.ppm").getImageDetails(),
            "snakes");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File \"res/assetsd/snakes.ppm\" not found !!", e.getMessage());
      }
      try {
        imageProcess.loadImage(new ReadImage("res/assetsd/snakes.png").getImageDetails(),
            "snakes");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File \"res/assetsd/snakes.png\" not found !!", e.getMessage());
      }
      try {
        imageProcess.loadImage(new ReadImage("res/assetsd/snakes.jpg").getImageDetails(),
            "snakes");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File \"res/assetsd/snakes.jpg\" not found !!", e.getMessage());
      }
      try {
        imageProcess.loadImage(new ReadImage("res/assetsd/snakes.bmp").getImageDetails(),
            "snakes");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File \"res/assetsd/snakes.bmp\" not found !!", e.getMessage());
      }
      try {
        imageProcess.loadImage(new ReadImage("res/assets/snakes.wrong").getImageDetails(),
            "snakes");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("Unsupported image format: wrong", e.getMessage());
      }

      //add new image by performing brightness.
      imageProcess.brightness(50, "snakes", "snakes-brighter");
      assertEquals(imageProcess.getImageData().get("snakes-brighter"), actualFiles.get(2));
      assertEquals(imageProcess.getImageData().size(), 2);

      try {
        imageProcess.brightness(50, "snakeses", "snakes-brighter");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name snakeses not found!!!", e.getMessage());
      }

      //Performing flip operation in image and add in hashmap.

      imageProcess.flip(false, "snakes", "snakes-vertical");
      assertEquals(imageProcess.getImageData().get("snakes-vertical"), actualFiles.get(11));
      assertEquals(imageProcess.getImageData().size(), 3);

      imageProcess.flip(true, "snakes-vertical",
          "snakes-vertical-horizontal");
      assertEquals(imageProcess.getImageData().get("snakes-vertical-horizontal"),
          actualFiles.get(6));
      assertEquals(imageProcess.getImageData().size(), 4);

      try {
        imageProcess.flip(false, "wrongSnakes", "snakes-vertical");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      //Performing greyscale operation in image and add in hashmap.
      imageProcess.greyscale("value-component", "snakes",
          "snakes-greyscale");
      assertEquals(imageProcess.getImageData().get("snakes-greyscale"), actualFiles.get(10));
      assertEquals(imageProcess.getImageData().size(), 5);

      try {
        imageProcess.greyscale("value-component", "wrongSnakes",
            "snakes-greyscale");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      // Save images.
      imageProcess.saveImage("snakes-brighter");
      assertEquals(imageProcess.getImageData().get("snakes-brighter"), actualFiles.get(2));
      assertEquals(imageProcess.getImageData().size(), 5);

      imageProcess.saveImage("snakes-greyscale");
      assertEquals(imageProcess.getImageData().get("snakes-greyscale"), actualFiles.get(10));
      assertEquals(imageProcess.getImageData().size(), 5);

      try {
        imageProcess.saveImage("wrongSnakes");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      //Load a new image.
      imageProcess.loadImage(new ReadImage("res/assets/snakes.ppm").getImageDetails(),
          "snakes");
      assertEquals(imageProcess.getImageData().get("snakes"), actualFiles.get(0));
      assertEquals(imageProcess.getImageData().size(), 5);

      //Performing split operation and added new images in hashmap.
      imageProcess.splitRGB("snakes", "snakes-red", "snakes-green",
          "snakes-blue");
      assertEquals(imageProcess.getImageData().get("snakes-red"), actualFiles.get(9));
      assertEquals(imageProcess.getImageData().get("snakes-green"), actualFiles.get(4));
      assertEquals(imageProcess.getImageData().get("snakes-blue"), actualFiles.get(1));
      assertEquals(imageProcess.getImageData().size(), 8);

      try {
        imageProcess.splitRGB("wrongSnakes", "snakes-red", "snakes-green",
            "snakes-blue");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      //Performing brightness.
      imageProcess.brightness(100, "snakes-red", "snakes-red-brighten");
      assertEquals(imageProcess.getImageData().get("snakes-red-brighten"), actualFiles.get(12));
      assertEquals(imageProcess.getImageData().size(), 9);

      //performing combine.
      imageProcess.combineRGB("snakes-red-tint", "snakes-red",
          "snakes-green", "snakes-blue");
      assertEquals(imageProcess.getImageData().get("snakes-red-tint"), actualFiles.get(0));
      assertEquals(imageProcess.getImageData().size(), 10);

      imageProcess.saveImage("snakes-red-tint");

      try {
        imageProcess.combineRGB("snakes-red-tint", "wrongSnakes-red",
            "snakes-green", "snakes-blue");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes-red not found!!!", e.getMessage());
      }

      imageProcess.loadImage(new ReadImage("test/assets/fox-1x2.ppm").getImageDetails(), "fox-1x2");
      imageProcess.loadImage(new ReadImage("test/assets/fox-2x1.ppm").getImageDetails(), "fox-2x1");
      imageProcess.loadImage(new ReadImage("test/assets/fox-2x2.ppm").getImageDetails(), "fox-2x2");
      imageProcess.loadImage(new ReadImage("test/assets/fox-maxvalue.ppm").getImageDetails(),
          "fox-maxvalue");
      try {
        imageProcess.combineRGB("snakes-red-tint", "fox-2x2",
            "fox-2x2", "fox-1x2");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("Width of all combine image is not same!!", e.getMessage());
      }
      try {
        imageProcess.combineRGB("snakes-red-tint", "fox-2x2",
            "fox-2x2", "fox-2x1");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("Height of all combine image is not same!!", e.getMessage());
      }
      try {
        imageProcess.combineRGB("snakes-red-tint", "fox-2x2",
            "fox-2x2", "fox-maxvalue");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("MaxValue of all combine image is not same!!", e.getMessage());
      }

      assertEquals(14, imageProcess.getImageData().size());
    } catch (IOException e) {
      fail("All file is not save in model");
    }
  }

  @Test
  public void testFilterModelDataStore() {
    ImageModal imageProcess = new ImageProcess();
    try {
      imageProcess.loadImage(new ReadImage("res/assets/snakes.ppm").getImageDetails(),
          "snakes");

      //Performing Sepia operation.
      imageProcess.sepia("snakes", "snakes-sepia");
      assertEquals(imageProcess.getImageData().get("snakes-sepia"), actualFiles.get(14));
      assertEquals(imageProcess.getImageData().size(), 2);
      try {
        imageProcess.sepia("wrongSnakes", "snakes-sepia");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      //Performing Dithering operation.
      imageProcess.dithering("snakes", "snakes-dithering");
      assertEquals(imageProcess.getImageData().get("snakes-dithering"), actualFiles.get(15));
      assertEquals(imageProcess.getImageData().size(), 3);
      try {
        imageProcess.sepia("wrongSnakes", "snakes-dithering");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      //Performing Dithering operation.
      imageProcess.sharpening("snakes", "snakes-sharpening");
      assertEquals(imageProcess.getImageData().get("snakes-sharpening"), actualFiles.get(16));
      assertEquals(imageProcess.getImageData().size(), 4);
      try {
        imageProcess.sepia("wrongSnakes", "snakes-sharpening");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      //Performing Blur operation.
      imageProcess.blur("snakes", "snakes-blur");
      assertEquals(imageProcess.getImageData().get("snakes-blur"), actualFiles.get(17));
      assertEquals(imageProcess.getImageData().size(), 5);
      try {
        imageProcess.sepia("wrongSnakes", "snakes-blur");
        fail("File was not present but still shown it's present!!");
      } catch (IOException e) {
        assertEquals("File name wrongSnakes not found!!!", e.getMessage());
      }

      assertEquals(5, imageProcess.getImageData().size());
    } catch (IOException e) {
      fail("All file is not save in model");
    }
  }

}
