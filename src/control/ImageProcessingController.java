package control;

import control.tasks.Blur;
import control.tasks.Brighten;
import control.tasks.Dither;
import control.tasks.GreyScale;
import control.tasks.HorizontalFlip;
import control.tasks.Load;
import control.tasks.Mosaic;
import control.tasks.RGBCombine;
import control.tasks.RGBSplit;
import control.tasks.RunScript;
import control.tasks.Save;
import control.tasks.Sepia;
import control.tasks.Sharpen;
import control.tasks.VerticalFlip;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import model.imagemodel.ImageModal;

/**
 * Created ImageProcessingController class which controls image processing functions by passing
 * value in model and all.
 */
public class ImageProcessingController implements ImageController {

  protected Readable in;
  protected Appendable out;

  /**
   * Constructs a ImageProcessingController in terms of input and output.
   *
   * @param in  the user input value
   * @param out the output of model execution
   */
  public ImageProcessingController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void imageControllerTask(ImageModal imageProcess) {
    Scanner input = new Scanner(this.in);
    Map<String, Function<Scanner, PerformingImageTasks>> knownCommands = new HashMap<>();
    knownCommands.put("load", userInput -> new Load(userInput.next(), userInput.next()));
    knownCommands.put("brighten",
        userInput -> new Brighten(userInput.nextInt(), userInput.next(), userInput.next()));
    knownCommands.put("vertical-flip",
        userInput -> new VerticalFlip(userInput.next(), userInput.next()));
    knownCommands.put("horizontal-flip",
        userInput -> new HorizontalFlip(userInput.next(), userInput.next()));
    knownCommands.put("greyscale",
        userInput -> new GreyScale(userInput.next(), userInput.next(), userInput.next()));
    knownCommands.put("save", userInput -> new Save(userInput.next(), userInput.next()));
    knownCommands.put("rgb-split",
        userInput -> new RGBSplit(userInput.next(), userInput.next(), userInput.next(),
            userInput.next()));
    knownCommands.put("rgb-combine",
        userInput -> new RGBCombine(userInput.next(), userInput.next(), userInput.next(),
            userInput.next()));
    knownCommands.put("run",
        userInput -> new RunScript(userInput.next(), this.out));
    knownCommands.put("blur",
        userInput -> new Blur(userInput.next(), userInput.next()));
    knownCommands.put("sharpen",
        userInput -> new Sharpen(userInput.next(), userInput.next()));
    knownCommands.put("sepia",
        userInput -> new Sepia(userInput.next(), userInput.next()));
    knownCommands.put("dither",
        userInput -> new Dither(userInput.next(), userInput.next()));
    knownCommands.put("mosaic",
        userInput -> new Mosaic(userInput.nextInt(), userInput.next(), userInput.next()));

    while (input.hasNext()) {
      try {
        PerformingImageTasks cTemp;
        String in = input.next();
        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")
            || in.equalsIgnoreCase("exit")) {
          return;
        }
        Function<Scanner, PerformingImageTasks>
            cmd = knownCommands.getOrDefault(in, null);
        if (cmd == null) {
          throw new IllegalArgumentException("Incorrect " + in + " input type!!!");
        } else {
          cTemp = cmd.apply(input);
          cTemp.startControl(imageProcess);
        }
      } catch (InputMismatchException | IOException | IllegalArgumentException e) {
        try {
          this.out.append(e.getMessage()).append("\n");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    }
  }

}



