package control;

import static org.junit.Assert.assertEquals;

import model.imagemodel.ImageModal;
import model.imagemodel.ImageProcess;
import model.imageviewmodel.ImageReadModel;
import model.imageviewmodel.ReadModel;
import org.junit.Test;
import view.ImageView;
import view.JFrameView;

/**
 * Created ImageViewControllerTest class to test all the implementation of view controller, like
 * passing data to getting accurate result all.
 */
public class ImageViewControllerTest {

  @Test
  public void testControllerCommand() {

    ImageModal imageProcess = new ImageProcess();
    ReadModel readModel = new ImageReadModel(imageProcess);
    ImageView imageView = new JFrameView(readModel);
    IController controller = new Controller(imageProcess, imageView);

    controller.setView();

    controller.loadImage("res/assets/snakes.ppm");
    // compare the size of hashMap.
    assertEquals(1, imageProcess.getImageData().size());

    controller.horizontalImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.verticalImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.greyscaleImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.sepiaImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.blurImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.sharpImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.dither();
    assertEquals(1, imageProcess.getImageData().size());

    controller.intensityImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.lumaImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.valueImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.redImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.greenImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.blueImage();
    assertEquals(1, imageProcess.getImageData().size());

    controller.brightnessImage(50);
    assertEquals(1, imageProcess.getImageData().size());

    controller.combineImage("res/assets/snakes.ppm", 0);
    controller.combineImage("res/assets/snakes.ppm", 1);
    controller.combineImage("res/assets/snakes.ppm", 2);
    assertEquals(4, imageProcess.getImageData().size());

    controller.rgbSplit();
    assertEquals(4, imageProcess.getImageData().size());

    controller.rgbSplitImageShow(0);
    assertEquals(4, imageProcess.getImageData().size());

    controller.saveImage("res/assets/snakes.png");
    assertEquals(4, imageProcess.getImageData().size());
  }

  @Test
  public void testControllerTask() {
    ImageModal imageProcess = new ImageProcess();
    ReadModel readModel = new ImageReadModel(imageProcess);
    ImageView imageView = new JFrameView(readModel);
    IController controller = new Controller(imageProcess, imageView);

    controller.loadImage("res/assts/snakes.ppm");
    // compare the size of hashMap.
    assertEquals(0, imageProcess.getImageData().size());

    controller.horizontalImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.verticalImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.greyscaleImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.sepiaImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.blurImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.sharpImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.dither();
    assertEquals(0, imageProcess.getImageData().size());

    controller.intensityImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.lumaImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.valueImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.redImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.greenImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.blueImage();
    assertEquals(0, imageProcess.getImageData().size());

    controller.brightnessImage(50);
    assertEquals(0, imageProcess.getImageData().size());

    controller.combineImage("res/asets/snakes.ppm", 0);
    controller.combineImage("res/asets/snakes.ppm", 1);
    controller.combineImage("res/asets/snakes.ppm", 2);
    assertEquals(0, imageProcess.getImageData().size());

    controller.rgbSplit();
    assertEquals(0, imageProcess.getImageData().size());

    controller.rgbSplitImageShow(0);
    assertEquals(0, imageProcess.getImageData().size());

    controller.saveImage("res/assets/snakes.png");
    assertEquals(0, imageProcess.getImageData().size());
  }
}
