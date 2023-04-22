package mockcontroller;

import static org.junit.Assert.assertEquals;

import control.Controller;
import control.IController;
import model.imagemodel.ImageModal;
import model.imagemodel.ImageProcess;
import org.junit.Test;

/**
 * Created MockControllerTest class to test all the implementation of View controller, like it is
 * passing the data accurately in the View.
 */
public class MockControllerTest {

  String[] fileTypeArray = {"ppm", "png", "jpg", "bmp"};

  @Test
  public void testViewController() {

    StringBuilder expected = new StringBuilder();
    ImageModal imageProcess = new ImageProcess();
    StringBuilder log = new StringBuilder();
    IController controller = new Controller(imageProcess, new MockViewOperation(log));

    expected.append("addFeatures : class control.Controller\n");
    controller.setView();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 1107024580\n");
    controller.loadImage("res/assets/snakes.ppm");
    assertEquals(expected.toString(), log.toString());

    controller.combineImage("res/assets/snakes.ppm", 0);
    assertEquals("addFeatures : class control.Controller\n"
        + "Input : clearScreen\n"
        + "loadImage : 1107024580\n"
        + "loadCombineImage : 11070245800 0\n", log.toString());

    controller.loadImage("res/asses/snakes.ppm");
    assertEquals("addFeatures : class control.Controller\n"
        + "Input : clearScreen\n"
        + "loadImage : 1107024580\n"
        + "loadCombineImage : 11070245800 0\n"
        + "Input : File \"res/asses/snakes.ppm\" not found !!\n", log.toString());
  }

  @Test
  public void testViewControllerMethod() {

    StringBuilder expected = new StringBuilder();

    ImageModal imageProcess = new ImageProcess();
    StringBuilder log = new StringBuilder();

    IController controller = new Controller(imageProcess, new MockViewOperation(log));

    expected.append("addFeatures : class control.Controller\n");
    controller.setView();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.loadImage("res/assets/snakes.ppm");
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.horizontalImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.verticalImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.greyscaleImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.sepiaImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.blurImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.sharpImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.dither();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.intensityImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.lumaImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.valueImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.redImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.greenImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.blueImage();
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.brightnessImage(50);
    assertEquals(expected.toString(), log.toString());

    expected.append("loadCombineImage : 4472127460 0\n");
    controller.combineImage("res/assets/snakes.ppm", 0);
    assertEquals(expected.toString(), log.toString());

    expected.append("Input : clearScreen\nloadImage : 447212746\n");
    controller.rgbSplit();
    assertEquals(expected.toString(), log.toString());
  }

}