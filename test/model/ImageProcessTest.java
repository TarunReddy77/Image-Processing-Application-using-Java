package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import control.ImageProcessingController;
import core.usecases.GetImageDetails;
import core.utils.ReadImage;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.imagemodel.ImageModal;
import model.imagemodel.ImageProcess;
import org.junit.Before;
import org.junit.Test;

/**
 * Created PPMImageProcessTest class to test all implementation of model of the
 * image_processing_application project.
 */
public class ImageProcessTest {

  List<GetImageDetails> actualFiles = new ArrayList<>();

  List<String> testCorrectFileName = new ArrayList<>(Arrays.asList(
      // image have different height and width.
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
      "snakes-blur.ppm", //12
      "snakes-sharpen.ppm", //13
      "snakes-sepia.ppm", //14
      "snakes-dither.ppm", //15
      "snakes.png", //16
      "snakes.jpg", //17
      "snakes.bmp", //18
      "snakes-red-greyscale-ppm.ppm", //19
      "snakes-tint-jpg.jpg", //20
      "snakes-luma-greyscale-jpg.jpg", //21
      "snakes-vertical-jpg.jpg", //22
      "snakes-green-greyscale-png.png", //23
      "snakes-blue-greyscale-bmp.bmp", //24
      "snakes-sepia-jpg.jpg" //25
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
  public void testScriptCommand() throws IOException {

    List<String> expectedFileName = new ArrayList<>(Arrays.asList(
        "snakes-tint.ppm",
        "snakes-darken.ppm"
    ));

    int[] ansPosition = new int[]{
        0, 3
    };

    Reader in = new StringReader("run res/textFile/scriptfileperfrom.txt");
    StringBuffer out = new StringBuffer();

    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();
    imageController.imageControllerTask(imageProcess);

    // compare the image data store in run file.
    assertEquals(6, imageProcess.getImageData().size());

    // all save commond run inside the run script.
    for (int i = 0; i < expectedFileName.size(); i++) {
      String imagePath = "res/assets/" + expectedFileName.get(i);
      GetImageDetails expectedFile = new ReadImage(imagePath).getImageDetails();

      assertEquals(actualFiles.get(ansPosition[i]), expectedFile);
    }

    //add valid commond
    in = new StringReader("load res/assets/snakes.ppm snakesCopy");
    out = new StringBuffer();
    ImageProcessingController imageControllerAddCommond = new ImageProcessingController(in, out);
    imageControllerAddCommond.imageControllerTask(imageProcess);

    assertEquals(7, imageProcess.getImageData().size());
  }

  @Test
  public void testScriptCommandAfter() throws IOException {

    List<String> expectedFileName = new ArrayList<>(Arrays.asList(
        "snakes-tint.ppm",
        "snakes-darken.ppm"
    ));

    int[] ansPosition = new int[]{
        0, 3
    };

    // load a image
    Reader in = new StringReader("load res/assets/snakes.ppm snakesBefore");
    StringBuffer out = new StringBuffer();

    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();
    imageController.imageControllerTask(imageProcess);

    assertEquals(1, imageProcess.getImageData().size());

    // run script file after have any other commond.
    in = new StringReader("run res/textFile/scriptfileperfrom.txt");
    out = new StringBuffer();
    ImageProcessingController imageControllerRunCommond = new ImageProcessingController(in, out);
    imageControllerRunCommond.imageControllerTask(imageProcess);

    // compare the image data store in run file.
    assertEquals(7, imageProcess.getImageData().size());

    // all save commond run inside the run script.
    for (int i = 0; i < expectedFileName.size(); i++) {
      String imagePath = "res/assets/" + expectedFileName.get(i);
      GetImageDetails expectedFile = new ReadImage(imagePath).getImageDetails();

      assertEquals(actualFiles.get(ansPosition[i]), expectedFile);
    }

    //add valid commond
    in = new StringReader("load res/assets/snakes.ppm snakesAfter");
    out = new StringBuffer();
    ImageProcessingController imageControllerAddCommondAfter = new ImageProcessingController(in,
        out);
    imageControllerAddCommondAfter.imageControllerTask(imageProcess);

    assertEquals(8, imageProcess.getImageData().size());
  }

  @Test
  public void testSaveImage() throws IOException {

    List<String> expectedSavedFileName = new ArrayList<>(Arrays.asList(
        "snakes-red.ppm",  // split and save red component file name
        "snakes-green.ppm", // split and save green component file name
        "snakes-blue.ppm", // split and save blue component file name
        "snakes-tint.ppm", // combine the 3 image to form one image file name
        "snakes-brighter.ppm", // brighter the image and save with this name
        "snakes-darken.ppm", // darken the image and save with this name
        "snakes-value-greyscale.ppm", // value-greyscale the image and save with this name
        "snakes-luma-greyscale.ppm", // luma-greyscale the image and save with this name
        "snakes-intensity-greyscale.ppm", // intensity-greyscale the image and save with this name
        "snakes-red-greyscale.ppm", // red-greyscale the image and save with this name
        "snakes-green-greyscale.ppm", // green-greyscale the image and save with this name
        "snakes-blue-greyscale.ppm", // blue-greyscale the image and save with this name
        "snakes-vertical.ppm", // vertical the image and save with this name
        "snakes-vertical-horizontal.ppm", // vertical-horizontal the image and save with this name
        "snakes-horizontal.ppm", // horizontal the image and save with this name
        "snakes-blur.ppm", // blur the image and save with this name
        "snakes-sharpen.ppm", // sharpen the image and save with this name
        "snakes-greyscale.ppm", // greyscale the image and save with this name
        "snakes-sepia.ppm", // sepia the image and save with this name
        "snakes-dither.ppm" // dither the image and save with this name
    ));

    int[] actualFile = new int[]{
        9, 4, 1, 0, 2, 3, 10, 8, 7, 9, 4, 1, 11, 6, 5, 12, 13, 8, 14, 15
    };

    // run all operation and after that save the file in "res/assets/"
    Reader in = new StringReader("run res/textFile/scriptfile.txt");

    StringBuffer out = new StringBuffer();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();

    imageController.imageControllerTask(imageProcess);

    for (int i = 0; i < expectedSavedFileName.size(); i++) {
      String imagePathDifferentSize = "res/assets/" + expectedSavedFileName.get(i);
      GetImageDetails expectedFile = new ReadImage(imagePathDifferentSize).getImageDetails();

      assertEquals(actualFiles.get(actualFile[i]), expectedFile);
    }
  }

  @Test
  public void testImageProcessError() {

    // load method check :
    List<String> errorTestFileName = new ArrayList<>(Arrays.asList(
        "snakes.ppm", //original file
        "fox-token-error.ppm", //1 image have token other than PPM
        "fox-width-error.ppm", //2 image with negative width
        "fox-height-error.ppm", //3 image with height negative
        "fox-maxvalue-error.ppm", //4 image have max pixel negative
        "fox-pixel-error.ppm", //5 image have pixel value more then max pixel or have negative
        "fox-2x2morepixel.ppm", //6 more value than expected
        "fox-2x2lesspixel.ppm", //7 less value than expected
        "fox.pp", //8 invalid file format
        "foxCopy.ppm", //9 empty image file pass
        "snak.ppm", //10 ppm image not found
        "snak.png", //11 png image not found
        "snak.jpg", //12 jpg image not found
        "snak.bmp" //13 bmp image not found
    ));

    String exceptionCatch =
        "Image token value are inappropriate!!\n" //0 image have token other than PPM
            + "Image width value are inappropriate!!\n" //1 image with negative width
            + "Image height value are inappropriate!!\n" //2 image with height negative
            + "Image maxValue value are inappropriate!!\n" //3 image have max pixel negative
            //4 image have pixel value more then max pixel or have negative
            + "Image pixel value are inappropriate!!\n" // less than expected
            + "Image pixel value are inappropriate!!\n" //6 more value than expected
            + "Pixel not found!!!\n" //7 less value than expected
            + "Unsupported image format: pp\n" //8 invalid file format
            + "Pixel not found!!!\n" //9 empty image file pass
            + "File \"test/assets/snak.ppm\" not found !!\n" //10 ppm image not found
            + "File \"test/assets/snak.png\" not found !!\n" //11 png image not found
            + "File \"test/assets/snak.jpg\" not found !!\n" //12 jpg image not found
            + "File \"test/assets/snak.bmp\" not found !!\n"; //13 bmp image not found

    StringBuilder commond = new StringBuilder();
    for (String s : errorTestFileName) {
      commond.append("load test/assets/").append(s).append(" ").append(s).append("\n");
    }

    Reader in = new StringReader(commond.toString());
    StringBuffer out = new StringBuffer();

    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();
    imageController.imageControllerTask(imageProcess);

    assertEquals(exceptionCatch, out.toString());

    assertEquals(1, imageProcess.getImageData().size());

    assertNull(imageProcess.getImageData().getOrDefault("fox-token-error.ppm",
        null));

    assertNull(imageProcess.getImageData().getOrDefault("fox-width-error.ppm",
        null));

    assertNull(imageProcess.getImageData().getOrDefault("fox-height-error.ppm",
        null));

    assertNull(imageProcess.getImageData().getOrDefault("fox-maxvalue-error.ppm",
        null));

    assertNull(imageProcess.getImageData().getOrDefault("fox-pixel-error.ppm",
        null));

    //run script method :
    in = new StringReader("run res/textFile/command.txt");
    out = new StringBuffer();
    ImageProcessingController imageControllerTextFileError = new ImageProcessingController(in,
        out);
    imageControllerTextFileError.imageControllerTask(imageProcess);
    assertEquals("Filepath \"res/textFile/command.txt\" not found !!\n", out.toString());

    // Save method check
    in = new StringReader("save res/assets/snakes.ppm fo");
    out = new StringBuffer();

    ImageProcessingController imageControllerSave = new ImageProcessingController(in, out);
    imageControllerSave.imageControllerTask(imageProcess);
    assertEquals("File name fo not found!!!\n", out.toString());

    //error in pathname :
    in = new StringReader("save res/asse/snakes.ppm snakes.ppm");
    out = new StringBuffer();
    ImageProcessingController imageControllerFilepathNotFound = new ImageProcessingController(in,
        out);
    imageControllerFilepathNotFound.imageControllerTask(imageProcess);
    assertEquals("Filepath not found !!\n", out.toString());

    //error in file format :
    in = new StringReader("save res/asse/snakes.pp snakes.ppm");
    out = new StringBuffer();
    ImageProcessingController imageControllerFileFormat = new ImageProcessingController(in,
        out);
    imageControllerFileFormat.imageControllerTask(imageProcess);
    assertEquals("Unsupported image format: pp\n", out.toString());

    //error in png save :
    in = new StringReader("save res/asse/snakes.png snakes.ppm");
    out = new StringBuffer();
    ImageProcessingController imageControllerPngSave = new ImageProcessingController(in,
        out);
    imageControllerPngSave.imageControllerTask(imageProcess);
    assertEquals("Filepath not found !!\n", out.toString());

    //error in png save :
    in = new StringReader("save res/asse/snakes.jpg snakes.ppm");
    out = new StringBuffer();
    ImageProcessingController imageControllerJpgSave = new ImageProcessingController(in,
        out);
    imageControllerJpgSave.imageControllerTask(imageProcess);
    assertEquals("Filepath not found !!\n", out.toString());

    //error in png save :
    in = new StringReader("save res/asse/snakes.bmp snakes.ppm");
    out = new StringBuffer();
    ImageProcessingController imageControllerBmpSave = new ImageProcessingController(in,
        out);
    imageControllerBmpSave.imageControllerTask(imageProcess);
    assertEquals("Filepath not found !!\n", out.toString());

    //add valid commond
    in = new StringReader("load res/assets/snakes.ppm snakesAfter");
    out = new StringBuffer();
    ImageProcessingController imageControllerAddCommondAfter = new ImageProcessingController(in,
        out);
    imageControllerAddCommondAfter.imageControllerTask(imageProcess);

    assertEquals(2, imageProcess.getImageData().size());
  }

  @Test
  public void testImageOperationsError() {

    ImageModal imageProcess = new ImageProcess();

    // file not found
    assertNull(imageProcess.getImageData().getOrDefault("snakes", null));

    Reader in = new StringReader(
        "load test/assets/fox-1x2.ppm fox-1x2\n"
            + "load test/assets/fox-2x2.ppm fox-2x2\n"
            + "load test/assets/fox-2x1.ppm fox-2x1\n"
            + "load test/assets/fox-maxvalue.ppm fox-maxvalue");

    StringBuffer out = new StringBuffer();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    assertEquals(4, imageProcess.getImageData().size());

    // combine error
    in = new StringReader("rgb-combine fox-combine fox-2x2 fox-2x2 fox-1x2");
    out = new StringBuffer();
    ImageProcessingController imageControllerWidth = new ImageProcessingController(in, out);
    imageControllerWidth.imageControllerTask(imageProcess);
    assertEquals("Width of all combine image is not same!!\n", out.toString());

    in = new StringReader("rgb-combine fox-combine fox-2x2 fox-2x2 fox-2x1");
    out = new StringBuffer();
    ImageProcessingController imageControllerHeight = new ImageProcessingController(in, out);
    imageControllerHeight.imageControllerTask(imageProcess);
    assertEquals("Height of all combine image is not same!!\n", out.toString());

    in = new StringReader("rgb-combine fox-combine fox-2x2 fox-2x2 fox-maxvalue");
    out = new StringBuffer();
    ImageProcessingController imageControllerMaxValue = new ImageProcessingController(in, out);
    imageControllerMaxValue.imageControllerTask(imageProcess);
    assertEquals("MaxValue of all combine image is not same!!\n", out.toString());

    // greyscale error
    in = new StringReader("greyscale pink-component greyscale snakes-red-greyscale\n");
    out = new StringBuffer();
    ImageProcessingController imageControllerWrongGrayScale = new ImageProcessingController(in,
        out);
    imageControllerWrongGrayScale.imageControllerTask(imageProcess);
    assertEquals("This is invalid Grayscale Type!!\n", out.toString());

    //Error for not finding the file
    String exceptionCatch =
        "File name brighten not found!!!\n"
            + "File name vertical-flip not found!!!\n"
            + "File name greyscale not found!!!\n"
            + "File name rgb-split not found!!!\n"
            + "File name snakes-red not found!!!\n"
            + "File name snakes-green not found!!!\n"
            + "File name snakes-blue not found!!!\n"
            + "File name blur not found!!!\n"
            + "File name sharpen not found!!!\n"
            + "File name sepia not found!!!\n"
            + "File name dither not found!!!\n";

    in = new StringReader("brighten -50 brighten snakes-darken\n"
        + "vertical-flip vertical-flip snakes-vertical\n"
        + "greyscale red-component greyscale snakes-red-greyscale\n"
        + "rgb-split rgb-split snakes-red snakes-green snakes-blue\n"
        + "rgb-combine snakes-tint snakes-red snakes-green snakes-blue\n"
        + "rgb-combine snakes-tint fox-1x2 snakes-green snakes-blue\n"
        + "rgb-combine snakes-tint fox-1x2 fox-1x2 snakes-blue\n"
        + "blur blur snakes\n"
        + "sharpen sharpen snakes\n"
        + "sepia sepia snakes\n"
        + "dither dither snakes\n");

    out = new StringBuffer();
    ImageProcessingController imageControllerFileNotFound = new ImageProcessingController(in, out);
    imageControllerFileNotFound.imageControllerTask(imageProcess);

    assertEquals(exceptionCatch, out.toString());

    assertEquals(4, imageProcess.getImageData().size());

    //add valid commond
    in = new StringReader("rgb-split fox-2x2 snakes-red snakes-green snakes-blue");
    out = new StringBuffer();
    ImageProcessingController imageControllerAddCommond = new ImageProcessingController(in, out);
    imageControllerAddCommond.imageControllerTask(imageProcess);

    assertEquals(7, imageProcess.getImageData().size());
  }

  @Test
  public void testModelDataStore() {

    int[] ansPosition = new int[]{
        0, 9, 4, 1, 0, 3
    };

    String[] expectedFileName = new String[]{
        "snakes", "snakes-red", "snakes-green", "snakes-blue", "snakes-tint", "snakes-darken"
    };

    Reader in = new StringReader("load res/assets/snakes.ppm snakes\n"
        + "rgb-split snakes snakes-red snakes-green snakes-blue\n"
        + "rgb-combine snakes-tint snakes-red snakes-green snakes-blue\n"
        + "save res/assets/snakes-tint.ppm snakes-tint\n"
        + "brighten -50 snakes snakes-darken\n"
        + "save res/assets/snakes-darken.ppm snakes-darken");

    StringBuffer out = new StringBuffer();

    ImageProcessingController imageController = new ImageProcessingController(in, out);
    ImageModal imageProcess = new ImageProcess();

    imageController.imageControllerTask(imageProcess);

    assertEquals(6, imageProcess.getImageData().size());
    for (int i = 0; i < 6; i++) {
      assertEquals(actualFiles.get(ansPosition[i]),
          imageProcess.getImageData().get(expectedFileName[i]));
    }

  }

  @Test
  public void combineTest() throws IOException {

    Reader in = new StringReader("load res/assets/snakes.ppm snakes\n"
        + "brighten 50 snakes snakes-brighter\n"
        + "vertical-flip snakes snakes-vertical\n"
        + "horizontal-flip snakes-vertical snakes-vertical-horizontal\n"
        + "save res/assets/snakes-vertical-horizontal.ppm snakes-vertical-horizontal\n"
        + "greyscale value-component snakes snakes-greyscale\n"
        + "save res/assets/snakes-brighter.ppm snakes-brighter\n"
        + "save res/assets/snakes-greyscale.ppm snakes-greyscale\n"
        + "blur snakes snakes-blur\n"
        + "save res/assets/snakes-blur.ppm snakes-blur\n"
        + "sharpen snakes snakes-sharpen\n"
        + "save res/assets/snakes-sharpen.ppm snakes-sharpen\n"
        + "sepia snakes snakes-sepia\n"
        + "save res/assets/snakes-sepia.ppm snakes-sepia\n"
        + "dither snakes snakes-dither\n"
        + "save res/assets/snakes-dither.ppm snakes-dither\n"
        + "load res/assets/snakes-brighter.ppm snakes\n"
        + "rgb-split snakes snakes-red snakes-green snakes-blue\n"
        + "brighten 50 snakes-red snakes-red\n"
        + "rgb-combine snakes-red-tint snakes-red snakes-green snakes-blue\n"
        + "save res/assets/snakes-red-tint.ppm snakes-red-tint");

    StringBuffer out = new StringBuffer();

    ImageModal imageProcess = new ImageProcess();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    assertEquals(13, imageProcess.getImageData().size());

    // brighter test
    String imagePath = "test/assets/snakes-brighter.ppm";
    GetImageDetails expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-brighter"), expectedFile);

    // vertical test
    imagePath = "test/assets/snakes-vertical.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-vertical"), expectedFile);

    // horizontal test
    imagePath = "test/assets/snakes-horizontal-vertical.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-vertical-horizontal"), expectedFile);

    // greyscale test
    imagePath = "test/assets/snakes-value-greyscale.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-greyscale"), expectedFile);

    // blur test
    imagePath = "test/assets/snakes-blur.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-blur"), expectedFile);

    // sharpen test
    imagePath = "test/assets/snakes-sharpen.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-sharpen"), expectedFile);

    // sepia test
    imagePath = "test/assets/snakes-sepia.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-sepia"), expectedFile);

    // dither test
    imagePath = "test/assets/snakes-dither.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-dither"), expectedFile);

    // override test
    imagePath = "test/assets/overread-snakes.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes"), expectedFile);

    // split test
    imagePath = "test/assets/snakes-red-brighter-50.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-red"), expectedFile);

    // combine test
    imagePath = "test/assets/snakes-change-snakes-red-50-combine.ppm";
    expectedFile = new ReadImage(imagePath).getImageDetails();

    assertEquals(imageProcess.getImageData().get("snakes-red-tint"), expectedFile);
  }

  @Test
  public void multiTypeLoad() {

    Reader in = new StringReader("load res/assets/snakes.ppm snakes-ppm\n"

        + "load res/assets/snakes.bmp snakes-bmp\n"
        + "save res/assets/ppm-snakes-png.png snakes-ppm\n"
        + "save res/assets/ppm-snakes-jpg.jpg snakes-ppm\n"
        + "save res/assets/ppm-snakes-bmp.bmp snakes-ppm");
    StringBuffer out = new StringBuffer();
    ImageModal imageProcess = new ImageProcess();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    // ppm to png, bmp, jpg
    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.png").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/ppm-snakes-png.png").getImageDetails();
      assertEquals(actualFile, expectedFile);
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.jpg").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/ppm-snakes-jpg.jpg").getImageDetails();
      assertEquals(actualFile.toString(), expectedFile.toString());
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.bmp").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/ppm-snakes-bmp.bmp").getImageDetails();
      assertEquals(actualFile, expectedFile);
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    in = new StringReader("load res/assets/snakes.png snakes-png\n"
        + "save res/assets/png-snakes-ppm.ppm snakes-png\n"
        + "save res/assets/png-snakes-jpg.jpg snakes-png\n"
        + "save res/assets/png-snakes-bmp.bmp snakes-png");
    out = new StringBuffer();
    ImageProcessingController imageControllerPng = new ImageProcessingController(in, out);
    imageControllerPng.imageControllerTask(imageProcess);

    // png to ppm, bmp, jpg
    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.ppm").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/png-snakes-ppm.ppm").getImageDetails();
      assertEquals(actualFile, expectedFile);
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.jpg").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/png-snakes-jpg.jpg").getImageDetails();
      assertEquals(actualFile, expectedFile);
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.bmp").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/png-snakes-bmp.bmp").getImageDetails();
      assertEquals(actualFile.toString(), expectedFile.toString());
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    in = new StringReader("load res/assets/snakes.jpg snakes-jpg\n"
        + "save res/assets/jpg-snakes-ppm.ppm snakes-jpg\n"
        + "save res/assets/jpg-snakes-png.png snakes-jpg\n"
        + "save res/assets/jpg-snakes-bmp.bmp snakes-jpg");
    out = new StringBuffer();
    ImageProcessingController imageControllerJpg = new ImageProcessingController(in, out);
    imageControllerJpg.imageControllerTask(imageProcess);

    // jpg to ppm, png, bmp
    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/jpg-snakes.ppm").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/jpg-snakes-ppm.ppm").getImageDetails();
      assertEquals(actualFile.toString(), expectedFile.toString());
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/jpg-snakes.png").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/jpg-snakes-png.png").getImageDetails();
      assertEquals(actualFile, expectedFile);
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/jpg-snakes.bmp").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/jpg-snakes-bmp.bmp").getImageDetails();
      assertEquals(actualFile.toString(), expectedFile.toString());
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    in = new StringReader("load res/assets/snakes.bmp snakes-bmp\n"
        + "save res/assets/bmp-snakes-ppm.ppm snakes-bmp\n"
        + "save res/assets/bmp-snakes-png.png snakes-bmp\n"
        + "save res/assets/bmp-snakes-jpg.jpg snakes-bmp");
    out = new StringBuffer();
    ImageProcessingController imageControllerBmp = new ImageProcessingController(in, out);
    imageControllerBmp.imageControllerTask(imageProcess);

    // bmp to ppm, png, jpg
    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.ppm").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/bmp-snakes-ppm.ppm").getImageDetails();
      assertEquals(actualFile, expectedFile);
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.png").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/bmp-snakes-png.png").getImageDetails();
      assertEquals(actualFile, expectedFile);
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    try {
      GetImageDetails expectedFile = new ReadImage(
          "test/assets/snakes.jpg").getImageDetails();
      GetImageDetails actualFile = new ReadImage(
          "res/assets/bmp-snakes-jpg.jpg").getImageDetails();
      assertEquals(actualFile.toString(), expectedFile.toString());
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }
  }

  @Test
  public void multiTypeLoadOneOperation() {

    Reader in = new StringReader("load res/assets/snakes.ppm snakes-ppm\n"
        + "load res/assets/snakes.png snakes-png\n"
        + "load res/assets/snakes.jpg snakes-jpg\n"
        + "load res/assets/snakes.bmp snakes-bmp\n"
        + "rgb-split snakes-ppm snakes-red snakes-green snakes-blue\n"
        + "save res/assets/snakes-green-split-png.png snakes-green\n"
        + "rgb-combine snakes-tint snakes-red snakes-green snakes-blue\n"
        + "save res/assets/snakes-tint-jpg.jpg snakes-tint\n"
        + "brighten 50 snakes-ppm snakes-brighter\n"
        + "save res/assets/snakes-brighter-bmp.bmp snakes-brighter\n"
        + "brighten -50 snakes-ppm snakes-darken\n"
        + "save res/assets/snakes-darken-bmp.bmp snakes-darken\n"
        + "greyscale value-component snakes-png snakes-value-greyscale\n"
        + "save res/assets/snakes-value-greyscale-ppm.ppm snakes-value-greyscale\n"
        + "greyscale luma-component snakes-png snakes-luma-greyscale\n"
        + "save res/assets/snakes-luma-greyscale-jpg.jpg snakes-luma-greyscale\n"
        + "greyscale intensity-component snakes-png snakes-intensity-greyscale\n"
        + "save res/assets/snakes-intensity-greyscale-bmp.bmp snakes-intensity-greyscale\n"
        + "greyscale red-component snakes-jpg snakes-red-greyscale\n"
        + "save res/assets/snakes-red-greyscale-ppm.ppm snakes-red-greyscale\n"
        + "greyscale green-component snakes-jpg snakes-green-greyscale\n"
        + "save res/assets/snakes-green-greyscale-png.png snakes-green-greyscale\n"
        + "greyscale blue-component snakes-jpg snakes-blue-greyscale\n"
        + "save res/assets/snakes-blue-greyscale-bmp.bmp snakes-blue-greyscale\n"
        + "horizontal-flip snakes-bmp snakes-horizontal\n"
        + "save res/assets/snakes-horizontal-ppm.ppm snakes-horizontal\n"
        + "vertical-flip snakes-horizontal snakes-horizontal-vertical\n"
        + "save res/assets/snakes-horizontal-vertical-png.png snakes-horizontal-vertical\n"
        + "vertical-flip snakes-bmp snakes-vertical\n"
        + "save res/assets/snakes-vertical-jpg.jpg snakes-vertical\n"
        + "blur snakes-ppm snakes-blur\n"
        + "save res/assets/snakes-blur-ppm.ppm snakes-blur\n"
        + "sharpen snakes-png snakes-sharpen\n"
        + "save res/assets/snakes-sharpen-png.png snakes-sharpen\n"
        + "sepia snakes-jpg snakes-sepia\n"
        + "save res/assets/snakes-sepia-jpg.jpg snakes-sepia\n"
        + "dither snakes-bmp snakes-dither\n"
        + "save res/assets/snakes-dither-bpm.bmp snakes-dither");
    StringBuffer out = new StringBuffer();

    ImageModal imageProcess = new ImageProcess();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    assertEquals(23, imageProcess.getImageData().size());

    int[] ansPosition = new int[]{
        0, 16, 17, 18,
        9, 4, 1, 0,
        2, 3, 5, 6,
        11, 12, 13,
        10, 8, 7,
        19, 15
    };

    List<String> expectedFileName = new ArrayList<>(Arrays.asList(
        "snakes-ppm", "snakes-png", "snakes-jpg", "snakes-bmp",
        "snakes-red", "snakes-green", "snakes-blue", "snakes-tint",
        "snakes-brighter", "snakes-darken", "snakes-horizontal", "snakes-horizontal-vertical",
        "snakes-vertical", "snakes-blur", "snakes-sharpen",
        "snakes-value-greyscale", "snakes-luma-greyscale", "snakes-intensity-greyscale",
        "snakes-red-greyscale", "snakes-dither"
    ));

    // all commond save inside hashmap.
    for (int i = 0; i < expectedFileName.size(); i++) {

      GetImageDetails expectedFile = imageProcess.getImageData().get(expectedFileName.get(i));
      assertEquals(actualFiles.get(ansPosition[i]).toString(), expectedFile.toString());
    }

    // all save file compare
    List<String> expectedSavedFileName = new ArrayList<>(Arrays.asList(
        "snakes-green-split-png.png",  // split ppm and save png
        "snakes-tint-jpg.jpg", // combine ppm and save jpg
        "snakes-brighter-bmp.bmp", // brighter ppm to save bmp
        "snakes-darken-bmp.bmp", // darken ppm to save bmp
        "snakes-value-greyscale-ppm.ppm", // greyscale png to save  ppm
        "snakes-luma-greyscale-jpg.jpg", // greyscale png to save jpg
        "snakes-intensity-greyscale-bmp.bmp", // greyscale png to save bmp
        "snakes-red-greyscale-ppm.ppm", // greyscale jpg to save ppm
        "snakes-green-greyscale-png.png", // greyscale jpg to save png
        "snakes-blue-greyscale-bmp.bmp", // greyscale jpg to save bmp
        "snakes-horizontal-ppm.ppm", // horizontal-flip bmp to save ppm
        "snakes-horizontal-vertical-png.png", // vertical-flip bmp to save png
        "snakes-vertical-jpg.jpg", // vertical-flip bmp to save jpg
        "snakes-blur-ppm.ppm", // blur ppm to save ppm
        "snakes-sharpen-png.png", // sharpen png to save png
        "snakes-sepia-jpg.jpg", // sepia jpg to save jpg
        "snakes-dither-bpm.bmp" // dither bmp to save bmp
    ));

    int[] actualFile = new int[]{
        4, 20, 2, 3, 10, 21, 7, 19, 23, 24, 5, 6, 22, 12, 13, 25, 15
    };

    for (int i = 0; i < expectedSavedFileName.size(); i++) {
      String imagePathDifferentSize = "res/assets/" + expectedSavedFileName.get(i);
      GetImageDetails expectedFile;
      try {
        expectedFile = new ReadImage(imagePathDifferentSize).getImageDetails();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      assertEquals(actualFiles.get(actualFile[i]), expectedFile);
    }
  }

  @Test
  public void extraTest() {

    Reader in = new StringReader("load res/assets/snakes.ppm snakes\n"
        + "blur snakes snakes-blur\n"
        + "save res/assets/snakes-blur.ppm snakes-blur\n"
        + "sharpen snakes snakes-sharpen\n"
        + "save res/assets/snakes-sharpen.png snakes-sharpen\n"
        + "sepia snakes snakes-sepia\n"
        + "save res/assets/snakes-sepia.jpg snakes-sepia\n"
        + "dither snakes snakes-dither\n"
        + "dither snakes-dither snakes-dither\n"
        + "save res/assets/snakes-dither.bmp snakes-dither");
    StringBuffer out = new StringBuffer();

    ImageModal imageProcess = new ImageProcess();
    ImageProcessingController imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    assertEquals(actualFiles.get(12), imageProcess.getImageData().get("snakes-blur"));
    assertEquals(actualFiles.get(13), imageProcess.getImageData().get("snakes-sharpen"));
    assertEquals(actualFiles.get(14), imageProcess.getImageData().get("snakes-sepia"));
    assertEquals(actualFiles.get(15), imageProcess.getImageData().get("snakes-dither"));

    in = new StringReader("load res/assets/snakes.png snakes\n"
        + "blur snakes snakes-blur\n"
        + "save res/assets/snakes-blur.ppm snakes-blur\n"
        + "sharpen snakes snakes-sharpen\n"
        + "save res/assets/snakes-sharpen.png snakes-sharpen\n"
        + "sepia snakes snakes-sepia\n"
        + "save res/assets/snakes-sepia.jpg snakes-sepia\n"
        + "dither snakes snakes-dither\n"
        + "dither snakes-dither snakes-dither\n"
        + "save res/assets/snakes-dither.bmp snakes-dither");
    out = new StringBuffer();

    imageProcess = new ImageProcess();
    imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    assertEquals(actualFiles.get(12), imageProcess.getImageData().get("snakes-blur"));
    assertEquals(actualFiles.get(13), imageProcess.getImageData().get("snakes-sharpen"));
    assertEquals(actualFiles.get(14), imageProcess.getImageData().get("snakes-sepia"));
    assertEquals(actualFiles.get(15), imageProcess.getImageData().get("snakes-dither"));

    in = new StringReader("load res/assets/snakes.jpg snakes\n"
        + "blur snakes snakes-blur\n"
        + "save res/assets/snakes-blur.ppm snakes-blur\n"
        + "sharpen snakes snakes-sharpen\n"
        + "save res/assets/snakes-sharpen.png snakes-sharpen\n"
        + "sepia snakes snakes-sepia\n"
        + "save res/assets/snakes-sepia.jpg snakes-sepia\n"
        + "dither snakes snakes-dither\n"
        + "dither snakes-dither snakes-dither\n"
        + "save res/assets/snakes-dither.bmp snakes-dither");
    out = new StringBuffer();

    imageProcess = new ImageProcess();
    imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    try {
      assertEquals(new ReadImage("res/assets/snakes-blur.ppm").getImageDetails(),
          new ReadImage("test/assets/snakes-blur-jpg-test.ppm").getImageDetails());
      assertEquals(new ReadImage("res/assets/snakes-sharpen.png").getImageDetails(),
          new ReadImage("test/assets/snakes-sharpen-jpg-test.png").getImageDetails());
      assertEquals(new ReadImage("res/assets/snakes-sepia.jpg").getImageDetails(),
          new ReadImage("test/assets/snakes-sepia-jpg-test.jpg").getImageDetails());
      assertEquals(new ReadImage("res/assets/snakes-dither.bmp").getImageDetails(),
          new ReadImage("test/assets/snakes-dither-jpg-test.bmp").getImageDetails());
    } catch (IOException e) {
      fail("File was present but then also shown file not found!!!");
    }

    in = new StringReader("load res/assets/snakes.bmp snakes\n"
        + "blur snakes snakes-blur\n"
        + "save res/assets/snakes-blur.ppm snakes-blur\n"
        + "sharpen snakes snakes-sharpen\n"
        + "save res/assets/snakes-sharpen.png snakes-sharpen\n"
        + "sepia snakes snakes-sepia\n"
        + "save res/assets/snakes-sepia.jpg snakes-sepia\n"
        + "dither snakes snakes-dither\n"
        + "dither snakes-dither snakes-dither\n"
        + "save res/assets/snakes-dither.bmp snakes-dither");
    out = new StringBuffer();

    imageProcess = new ImageProcess();
    imageController = new ImageProcessingController(in, out);
    imageController.imageControllerTask(imageProcess);

    assertEquals(actualFiles.get(12), imageProcess.getImageData().get("snakes-blur"));
    assertEquals(actualFiles.get(13), imageProcess.getImageData().get("snakes-sharpen"));
    assertEquals(actualFiles.get(14), imageProcess.getImageData().get("snakes-sepia"));
    assertEquals(actualFiles.get(15), imageProcess.getImageData().get("snakes-dither"));
  }
}