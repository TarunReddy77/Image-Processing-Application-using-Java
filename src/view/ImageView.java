package view;

import control.IController;

/**
 * The ImageView interface is responsible for defining the methods that must be implemented by any
 * class that will act as a view for the image processing application.
 */
public interface ImageView {

  /**
   * Adds the features of the application to the view.
   *
   * @param controller the controller instance to use
   */
  void addFeatures(IController controller);

  /**
   * Loads the image with the given name into the view.
   *
   * @param imageName the name of the image to load
   */
  void loadImage(String imageName);

  /**
   * Displays an error pop-up with the given error message.
   *
   * @param errorMessage the error message to display
   */
  void errorPopUp(String errorMessage);

  /**
   * Loads the image with the given name and unique ID into the view as a combined image.
   *
   * @param imageName the name of the image to load
   * @param uniqueID  the unique ID of the image
   */
  void loadCombineImage(String imageName, int uniqueID);

  /**
   * Clears the image and histogram screen.
   */
  void clearScreen();
}