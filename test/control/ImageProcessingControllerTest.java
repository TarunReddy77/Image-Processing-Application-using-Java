package control;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import model.imagemodel.ImageModal;
import model.imagemodel.ImageProcess;
import org.junit.Test;

/**
 * Created ImageProcessingControllerTest class to test all the implementation of controller, like
 * passing data to getting accurate result all.
 */
public class ImageProcessingControllerTest {

  @Test
  public void testControllerCommand() {

    String expectedErrors = "Incorrect loaaad input type!!!\n"
        + "Incorrect brigh input type!!!\n"
        + "Incorrect vertic-flip input type!!!\n"
        + "Incorrect horizon-flip input type!!!\n"
        + "Incorrect greyscale-red input type!!!\n"
        + "Incorrect saave input type!!!\n"
        + "Incorrect gb-split input type!!!\n"
        + "Incorrect gb-combine input type!!!\n"
        + "Incorrect runn input type!!!\n"
        + "Incorrect blue input type!!!\n"
        + "Incorrect sharp input type!!!\n"
        + "Incorrect shepia input type!!!\n"
        + "Incorrect diter input type!!!\n";

    Reader in = new StringReader(
        "load res/assets/snakes.ppm snakes\n" // valid commond which will add in HashMap.
            + "loaaad " // invalid load commond show's error.
            + "brigh " // invalid brightness commond show's error.
            + "vertic-flip " // invalid vertical-flip commond show's error.
            + "horizon-flip " // invalid horizontal-flip commond show's error.
            + "greyscale-red " // invalid greyscale commond show's error.
            + "saave " // invalid save commond show's error.
            + "gb-split " // invalid rgb-split commond show's error.
            + "gb-combine " // invalid rgb-combine commond show's error.
            + "runn "// invalid run commond show's error.
            + "blue "// invalid blur commond show's error.
            + "sharp "// invalid sharpen commond show's error.
            + "shepia "// invalid sepia commond show's error.
            + "diter "// invalid dither commond show's error.
    );

    StringBuffer out = new StringBuffer();

    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();
    imageController.imageControllerTask(imageProcess);

    assertEquals(expectedErrors, out.toString());

    // compare the size of hashMap.
    assertEquals(1, imageProcess.getImageData().size());

    // brightness should be integer
    in = new StringReader("brighten 50.5");
    out = new StringBuffer();
    ImageProcessingController imageControllerBrigthness = new ImageProcessingController(in, out);
    imageControllerBrigthness.imageControllerTask(imageProcess);
    expectedErrors = "null\n"
        + "Incorrect 50.5 input type!!!\n";

    assertEquals(expectedErrors, out.toString());

    assertEquals(1, imageProcess.getImageData().size());

    //add valid command
    in = new StringReader("rgb-split snakes snakes-red snakes-green snakes-blue");
    out = new StringBuffer();
    ImageProcessingController imageControllerAddCommond = new ImageProcessingController(in, out);
    imageControllerAddCommond.imageControllerTask(imageProcess);

    assertEquals(4, imageProcess.getImageData().size());
  }

  @Test
  public void testControllerTask() {

    String expectedErrors = "File \"res/asset/snakes.ppm\" not found !!\n"
        + "Filepath \"res/textFile/script.txt\" not found !!\n"
        + "Filepath not found !!\n"
        + "This is invalid Grayscale Type!!\n"
        + "Unsupported image format: kmp\n"
        + "File \"res/wrongAssets/snakes.ppm\" not found !!\n"
        + "Image width value are inappropriate!!\n"
        + "Image token value are inappropriate!!\n"
        + "Image maxValue value are inappropriate!!\n"
        + "Image pixel value are inappropriate!!\n"
        + "Unsupported image format: kmp\n";

    Reader in = new StringReader("load res/assets/snakes.ppm snakes\n"
        + "load res/asset/snakes.ppm snakes\n"
        + "run res/textFile/script.txt\n"
        + "save res/asse/snakes.ppm snakes\n"
        + "greyscale wrong-component snakes snakes-wrongValue-greyscale\n"
        + "load res/assets/snakes.kmp snakes\n"
        + "load res/wrongAssets/snakes.ppm snakes\n"
        + "load test/assets/fox-width-error.ppm wrong-width-snakes\n"
        + "load test/assets/fox-token-error.ppm wrong-token-snakes\n"
        + "load test/assets/fox-maxvalue-error.ppm wrong-maxvalue-snakes\n"
        + "load test/assets/fox-pixel-error.ppm wrong-pixel-snakes\n"
        + "save res/assets/snakes.kmp snakes\n");

    StringBuffer out = new StringBuffer();

    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();
    imageController.imageControllerTask(imageProcess);

    assertEquals(expectedErrors, out.toString());
    assertEquals(1, imageProcess.getImageData().size());

    //add valid command
    in = new StringReader("rgb-split snakes snakes-red snakes-green snakes-blue");
    out = new StringBuffer();
    ImageProcessingController imageControllerAddCommond = new ImageProcessingController(in, out);
    imageControllerAddCommond.imageControllerTask(imageProcess);

    assertEquals(4, imageProcess.getImageData().size());
  }

  @Test
  public void testInterTypeControllerTask() {
    String expectedErrors = "";

    Reader in = new StringReader("run res/textFile/interTypeTestingFile.txt\n");

    StringBuffer out = new StringBuffer();

    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();
    imageController.imageControllerTask(imageProcess);

    assertEquals(expectedErrors, out.toString());
    assertEquals(4, imageProcess.getImageData().size());
  }

}