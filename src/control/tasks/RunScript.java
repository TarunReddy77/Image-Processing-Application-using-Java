package control.tasks;

import control.ImageProcessingController;
import control.PerformingImageTasks;
import core.utils.ReadScriptFile;
import java.io.IOException;
import java.io.StringReader;
import model.imagemodel.ImageModal;

/**
 * Runscript class will control operations related to executing the script file in which all
 * commands are written.
 */
public class RunScript implements PerformingImageTasks {

  private final String scriptPath;
  private final Appendable out;

  /**
   * Constructs a Save in terms of image file and image name.
   *
   * @param scriptPath the image file in which different operations has been performed.
   * @param out        the result of the number of method has been call.
   */
  public RunScript(String scriptPath, Appendable out) {
    this.scriptPath = scriptPath;
    this.out = out;
  }

  @Override
  public void startControl(ImageModal modelObj) throws IOException {
    StringReader in = new ReadScriptFile(this.scriptPath).getScriptData();
    ImageProcessingController controller = new ImageProcessingController(in, this.out);
    controller.imageControllerTask(modelObj);
  }

}
