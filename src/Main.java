import control.Controller;
import control.IController;
import control.ImageController;
import control.ImageProcessingController;
import control.MosaicControllerImpl;
import core.utils.ReadScriptFile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import model.imagemodel.ImageModal;
import model.imagemodel.ImageProcess;
import model.imageviewmodel.ImageReadModel;
import model.imageviewmodel.ReadModel;
import model.operationmodel.ImageMosaic;
import model.operationmodel.ImageMosaicImpl;
import view.ImageView;
import view.JFrameView;

/**
 * Main class for the Image Processing Application. This class is responsible for starting the
 * application and initializing the Model, View, and Controller.
 */
public class Main {

  /**
   * Main method of the application where the program starts. If command-line arguments are
   * provided, it creates an ImageProcessingController to execute the commands. If no arguments are
   * provided, it initializes the Model, View, and Controller to launch the application.
   *
   * @param args an array of strings containing command-line arguments, if any.
   */
  public static void main(String[] args) {
    try {
      ImageMosaic imageMosaic = new ImageMosaicImpl();

      if (args.length != 0) {
        ImageController controller;
        if (args[0].equals("-file")) {
          StringReader in = new ReadScriptFile(args[1]).getScriptData();
          controller = new ImageProcessingController(in, System.out);
          controller.imageControllerTask(imageMosaic);
        } else if (args[0].equals("-text")) {
          controller = new ImageProcessingController(new InputStreamReader(System.in), System.out);
          controller.imageControllerTask(imageMosaic);
        } else {
          throw new IOException("Invalid parameter input passed!!!");
        }
      } else {
        ReadModel readModel = new ImageReadModel(imageMosaic);
        ImageView imageView = new JFrameView(readModel);
        IController controller = new Controller(imageMosaic, imageView);
        IController mosaicController = new MosaicControllerImpl(controller);
        mosaicController.setView();
      }
    } catch (IOException | ArrayIndexOutOfBoundsException ignored) {
    }
  }
}
