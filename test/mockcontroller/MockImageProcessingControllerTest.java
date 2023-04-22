package mockcontroller;

import static org.junit.Assert.assertEquals;

import control.Controller;
import control.IController;
import control.ImageProcessingController;
import core.usecases.GetImageDetails;
import core.utils.ReadImage;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import model.imagemodel.ImageModal;
import model.imagemodel.ImageProcess;
import org.junit.Test;

/**
 * Created MockImageProcessingControllerTest class to test all the implementation of controller,
 * like it is passing the data accurately in the modal.
 */
public class MockImageProcessingControllerTest {

  String[] fileTypeArray = {"ppm", "png", "jpg", "bmp"};

  @Test
  public void testViewController() {

    ImageModal imageProcess = new ImageProcess();
    StringBuilder log = new StringBuilder();

    IController controller = new Controller(imageProcess, new MockViewOperation(log));

    controller.setView();
    assertEquals("addFeatures : class control.Controller\n", log.toString());

    controller.loadImage("res/assets/snakes.ppm");
    assertEquals("addFeatures : class control.Controller\n"
        + "Input : clearScreen\n"
        + "loadImage : 1357563986\n", log.toString());

    controller.combineImage("res/assets/snakes.ppm", 0);
    assertEquals("addFeatures : class control.Controller\n"
        + "Input : clearScreen\n"
        + "loadImage : 1357563986\n"
        + "loadCombineImage : 13575639860 0\n", log.toString());

    controller.loadImage("res/asses/snakes.ppm");
    assertEquals("addFeatures : class control.Controller\n"
        + "Input : clearScreen\n"
        + "loadImage : 1357563986\n"
        + "loadCombineImage : 13575639860 0\n"
        + "Input : File \"res/asses/snakes.ppm\" not found !!\n", log.toString());
  }

  @Test
  public void testLoadController() throws IOException {
    Appendable out = new StringBuilder();

    for (String type : fileTypeArray) {
      Reader in = new StringReader("load res/assets/snakes." + type + " snakes\n");
      ImageProcessingController imageController = new ImageProcessingController(in, out);
      StringBuilder log = new StringBuilder();
      imageController.imageControllerTask(new MockImageOperations(log));
      GetImageDetails imageDetail;
      imageDetail = new ReadImage("res/assets/snakes." + type).getImageDetails();
      assertEquals("Input: " + imageDetail.toString() + " " + "snakes", log.toString());
    }
  }

  @Test
  public void testWrongPathLoader() {
    Appendable out = new StringBuilder();

    for (String type : fileTypeArray) {
      Reader in = new StringReader("load res/wrong/snakes." + type + " snakes\n");
      ImageProcessingController imageController = new ImageProcessingController(in, out);
      StringBuilder log = new StringBuilder();
      imageController.imageControllerTask(new MockImageOperations(log));
      assertEquals("", log.toString());
    }
  }

  @Test
  public void testSaveController() throws IOException {

    Appendable out = new StringBuilder();

    for (String type : fileTypeArray) {
      Reader in = new StringReader(
          "load res/assets/snakes." + type + " snakes-mock\nsave res/assets/snakes-mock." + type
              + " snakes-mock\n");
      ImageProcessingController imageController = new ImageProcessingController(in, out);
      StringBuilder log = new StringBuilder();
      imageController.imageControllerTask(new MockImageOperations(log));
      GetImageDetails imageDetail;
      imageDetail = new ReadImage("res/assets/snakes." + type).getImageDetails();
      assertEquals("Input: " + imageDetail.toString() + " " + "snakes-mockInput: snakes-mock\n",
          log.toString());
    }
  }


  @Test
  public void testMultipleSaveController() throws IOException {
    for (String type : fileTypeArray) {
      Reader in = new StringReader(
          "load res/assets/snakes." + type + " snakes-mock\nsave res/assets/snakes-mock." + type
              + " snakes-mock\nsave res/assets/snakes-mock." + type + " snakes-mock\n");
      StringBuilder out = new StringBuilder();
      ImageProcessingController imageController = new ImageProcessingController(in, out);
      StringBuilder log = new StringBuilder();
      imageController.imageControllerTask(new MockImageOperations(log));
      GetImageDetails imageDetail = new ReadImage("res/assets/snakes." + type).getImageDetails();
      assertEquals(
          "Input: " + imageDetail.toString() + " "
              + "snakes-mockInput: snakes-mock\nInput: snakes-mock\n",
          log.toString());
    }
  }


  @Test(expected = NullPointerException.class)
  public void testWrongPathSaveController() {
    for (String type : fileTypeArray) {
      Reader in = new StringReader(
          "save res/windowssh/snakes-demo." + type + " snakes-demo\n");
      StringBuilder out = new StringBuilder();
      ImageProcessingController imageController = new ImageProcessingController(in, out);
      StringBuilder log = new StringBuilder();
      imageController.imageControllerTask(new MockImageOperations(log));
    }
  }

  @Test
  public void testScriptFileController() throws IOException {
    GetImageDetails imageDetail = new ReadImage("res/assets/snakes.ppm").getImageDetails();
    String expectedOutput =
        "Input: " + imageDetail.toString() + " snakes-mock"
            + "Input: red-component snakes snakes-red\n"
            + "Input: green-component snakes snakes-green\n"
            + "Input: blue-component snakes snakes-blue\n"
            + "Input: snakes-tint snakes-red snakes-green snakes-blue\n"
            + "Input: -50 snakes snakes-darken\n"
            + "Input: snakes-mock\n";

    Reader in = new StringReader("run res/textFile/mockScriptfile.txt");
    StringBuilder out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals(expectedOutput, log.toString());
  }


  @Test
  public void testMixScriptCommandFileController() throws IOException {
    GetImageDetails imageDetail = new ReadImage("res/assets/snakes.ppm").getImageDetails();
    String expectedOutput =
        "Input: " + imageDetail + " new-snakes"
            + "Input: true new-snakes snakes-horizontal\n"
            + "Input: " + imageDetail + " snakes-mock"
            + "Input: red-component snakes snakes-red\n"
            + "Input: green-component snakes snakes-green\n"
            + "Input: blue-component snakes snakes-blue\n"
            + "Input: snakes-tint snakes-red snakes-green snakes-blue\n"
            + "Input: -50 snakes snakes-darken\n"
            + "Input: snakes-mock\n"
            + "Input: " + imageDetail + " snakes"
            + "Input: red-component snakes snakes-red-greyscale\n";

    String inputCommand =
        "load res/assets/snakes.ppm new-snakes\n"
            + "horizontal-flip new-snakes snakes-horizontal\n"
            + "run res/textFile/mockScriptfile.txt\n"
            + "load res/assets/snakes.ppm snakes\n"
            + "greyscale red-component snakes snakes-red-greyscale\n";

    Reader in = new StringReader(inputCommand);
    StringBuilder out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testHorizontalFlipController() {
    Reader in = new StringReader("horizontal-flip snakes snakes-horizontal\n");
    StringBuilder out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: true snakes snakes-horizontal\n", log.toString());
  }

  @Test
  public void testVerticalFlipController() {
    Reader in = new StringReader("vertical-flip snakes snakes-horizontal\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: false snakes snakes-horizontal\n", log.toString());
  }

  @Test
  public void testMixFlipController() throws IOException {
    Appendable out = new StringBuilder();

    for (String type : fileTypeArray) {
      String inputCommand =
          "load res/assets/snakes." + type + " new-snakes\n"
              + "horizontal-flip snakes snakes-horizontal\n"
              + "vertical-flip snakes snakes-horizontal\n";
      GetImageDetails expectedImageDetail;
      expectedImageDetail = new ReadImage("res/assets/snakes." + type).getImageDetails();
      String expectedOutput =
          "Input: " + expectedImageDetail + " new-snakes"
              + "Input: true snakes snakes-horizontal\n"
              + "Input: false snakes snakes-horizontal\n";

      Reader in = new StringReader(inputCommand);
      ImageProcessingController imageController = new ImageProcessingController(in, out);
      StringBuilder log = new StringBuilder();
      imageController.imageControllerTask(new MockImageOperations(log));
      assertEquals(expectedOutput, log.toString());
    }
  }

  @Test
  public void testRedGreyscaleController() {
    Reader in = new StringReader("greyscale red-component snakes snakes-red-greyscale\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: red-component snakes snakes-red-greyscale\n", log.toString());
  }

  @Test
  public void testBlueGreyscaleController() {
    Reader in = new StringReader("greyscale blue-component snakes snakes-blue-greyscale\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: blue-component snakes snakes-blue-greyscale\n", log.toString());
  }

  @Test
  public void testGreenGreyscaleController() {
    Reader in = new StringReader("greyscale green-component snakes snakes-green-greyscale\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: green-component snakes snakes-green-greyscale\n", log.toString());
  }

  @Test
  public void testValueGreyscaleController() {
    Reader in = new StringReader("greyscale value-component snakes snakes-value-greyscale\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: value-component snakes snakes-value-greyscale\n", log.toString());
  }

  @Test
  public void testLumaGreyscaleController() {
    Reader in = new StringReader("greyscale luma-component snakes snakes-luma-greyscale\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: luma-component snakes snakes-luma-greyscale\n", log.toString());
  }

  @Test
  public void testIntensityGreyscaleController() {
    Reader in = new StringReader(
        "greyscale intensity-component snakes snakes-intensity-greyscale\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: intensity-component snakes snakes-intensity-greyscale\n", log.toString());
  }

  @Test
  public void testMixGreyscaleController() {
    String input =
        "greyscale value-component snakes snakes-value-greyscale\n"
            + "greyscale intensity-component snakes-value-greyscale snakes-intensity-greyscale\n"
            + "greyscale luma-component snakes-intensity-greyscale snakes-luma-greyscale\n";

    String expectedOutput =
        "Input: value-component snakes snakes-value-greyscale\n"
            + "Input: intensity-component snakes-value-greyscale snakes-intensity-greyscale\n"
            + "Input: luma-component snakes-intensity-greyscale snakes-luma-greyscale\n";

    Reader in = new StringReader(input);
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testWrongGreyscaleController() {
    Reader in = new StringReader("greyscale alfa-component snakes snakes-intensity-greyscale\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("", log.toString());
  }

  @Test
  public void testSplitRGBController() {
    String input =
        "Input: red-component snakes snakes-red\n"
            + "Input: green-component snakes snakes-green\n"
            + "Input: blue-component snakes snakes-blue\n";

    Reader in = new StringReader("rgb-split snakes snakes-red snakes-green snakes-blue\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals(input, log.toString());
  }

  @Test
  public void testCombineRGBController() {
    Reader in = new StringReader(
        "rgb-combine snakes-combine-tint snakes-red snakes-green snakes-blue\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes-combine-tint snakes-red snakes-green snakes-blue\n",
        log.toString());
  }

  @Test
  public void testPositiveBrightnessController() {
    Reader in = new StringReader("brighten 30 snakes new-snakes-brighter\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: 30 snakes new-snakes-brighter\n", log.toString());
  }

  @Test
  public void testNegativeBrightnessController() {
    Reader in = new StringReader("brighten -25 snakes new-snakes-brighter\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: -25 snakes new-snakes-brighter\n", log.toString());
  }

  @Test
  public void testMixBrightnessController() {
    Reader in = new StringReader(
        "brighten -22 snakes new-snakes-brighter\n brighten 44 snakes new-snakes-brighter\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: -22 snakes new-snakes-brighter\nInput: 44 snakes new-snakes-brighter\n",
        log.toString());
  }

  @Test
  public void testMaxBrightnessController() {
    Reader in = new StringReader("brighten 355 snakes new-snakes-brighter\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: 355 snakes new-snakes-brighter\n", log.toString());
  }

  @Test
  public void testFuzzyBrightnessController() {
    int k;
    for (k = -230; k <= 240; k++) {
      String input = String.format("brighten %d snakes new-snakes-brighter\n", k);
      Reader in = new StringReader(input);
      Appendable out = new StringBuilder();
      ImageProcessingController imageController = new ImageProcessingController(in, out);
      StringBuilder log = new StringBuilder();
      imageController.imageControllerTask(new MockImageOperations(log));
      assertEquals(String.format("Input: %d snakes new-snakes-brighter\n", k), log.toString());
    }
  }

  @Test
  public void testBlurController() {
    Reader in = new StringReader("blur snakes snakes-blur\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-blur\n", log.toString());
  }

  @Test
  public void testMultipleBlurController() {
    Reader in = new StringReader("blur snakes snakes-blur\nblur snakes-blur snakes-blur2\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-blur\nInput: snakes-blur snakes-blur2\n", log.toString());
  }

  @Test
  public void testSharpenController() {
    Reader in = new StringReader("sharpen snakes snakes-sharpen\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-sharpen\n", log.toString());
  }

  @Test
  public void testMultipleSharpenController() {
    Reader in = new StringReader("sharpen snakes snakes-sharpen\nsharpen snakes snakes-sharpen\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-sharpen\nInput: snakes snakes-sharpen\n", log.toString());
  }

  @Test
  public void testSepiaController() {
    Reader in = new StringReader("sepia snakes snakes-sepia\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-sepia\n", log.toString());
  }

  @Test
  public void testMultipleSepiaController() {
    Reader in = new StringReader("sepia snakes snakes-sepia\nsepia snakes snakes-sepia\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-sepia\nInput: snakes snakes-sepia\n", log.toString());
  }

  @Test
  public void testDitherController() {
    Reader in = new StringReader("dither snakes snakes-dither\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-dither\n", log.toString());
  }

  @Test
  public void testMultipleDitherController() {
    Reader in = new StringReader("dither snakes snakes-dither\ndither snakes snakes-dither\n");
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals("Input: snakes snakes-dither\nInput: snakes snakes-dither\n", log.toString());
  }

  @Test
  public void testMixFilterController() {
    String input = "blur snakes snakes-blur\n"
        + "sharpen snakes snakes-sharpen\n"
        + "sepia snakes snakes-sepia\n"
        + "dither snakes snakes-dither\n";

    String expectedOutput = "Input: snakes snakes-blur\n"
        + "Input: snakes snakes-sharpen\n"
        + "Input: snakes snakes-sepia\n"
        + "Input: snakes snakes-dither\n";

    Reader in = new StringReader(input);
    Appendable out = new StringBuilder();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    StringBuilder log = new StringBuilder();
    imageController.imageControllerTask(new MockImageOperations(log));
    assertEquals(expectedOutput, log.toString());
  }
}