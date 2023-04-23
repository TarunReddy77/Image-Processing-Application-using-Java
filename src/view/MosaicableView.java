package view;

import java.util.Arrays;

import javax.swing.*;

import control.IController;
import control.MosaicableControllerImpl;

public class MosaicableView implements ImageView {

  private final JFrameView view;

  public MosaicableView(JFrameView view) {
    this.view = view;
    String[] newOperationButtonNames = Arrays.copyOf(view.operationButtonNames,
            view.operationButtonNames.length + 1);
    newOperationButtonNames[newOperationButtonNames.length - 1] = "Mosaic";
    view.operationButtonNames = newOperationButtonNames;
  }

  @Override
  public void addFeatures(IController controller) {
    view.addFeatures(controller);
    // Mosaic
    view.operationButtons[8].addActionListener(e -> {
      view.subOperationScreen.setBorder(BorderFactory.createTitledBorder("MOSAIC OPERATION"));
      view.setVisibility(
              new int[]{}, // subOperationButtons to be visible
              new boolean[]{false, false}, // defaultLabel to be visible
              new boolean[]{true, false}, // image to be visible
              new boolean[]{true, false}); // Spinner Visibility
    });
    view.subOperationButtons[21].addActionListener((e -> new MosaicableControllerImpl(controller).mosaicImage(100)));
  }

  @Override
  public void loadImage(String imageName) {
    view.loadImage(imageName);
  }

  @Override
  public void errorPopUp(String errorMessage) {
    view.errorPopUp(errorMessage);
  }

  @Override
  public void loadCombineImage(String imageName, int uniqueID) {
    view.loadCombineImage(imageName, uniqueID);
  }

  @Override
  public void clearScreen() {
    view.clearScreen();
  }
}
