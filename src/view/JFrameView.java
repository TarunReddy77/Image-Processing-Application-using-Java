package view;

import control.ExtensibleController;
import control.IController;
import core.usecases.GetImageDetails;
import core.utils.CreateHistogram;
import core.utils.CustomButton;
import core.utils.CustomSpinnerUI;
import core.utils.CustomSubButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.imageviewmodel.ReadModel;

/**
 * This class represents the view of the image processing application. It extends the JFrame class
 * and implements the ImageView and ChangeListener interfaces.
 */
public class JFrameView extends JFrame implements ImageView {

  private final ReadModel readModel;
  private final JLabel[] defaultLabel;
  private final CustomButton[] operationButtons;
  private final CustomSubButton[] subOperationButtons;
  private final CustomButton[] operationSaveLoad;
  private final JLabel[] operationCombineImageLabel;
  private final String[] jPanelCombineScreenName = new String[]{
      "IMAGE 1", "IMAGE 2", "IMAGE 3"
  };
  private final JPanel outputScreen;
  private final JPanel combineScreen;
  private final JLabel imageLabel;
  private final JLabel histogramLabel;
  private final JPanel histogramPanel;
  private final FileNameExtensionFilter filter;
  private final long[][] pixelChannelValue;
  private final JSpinner spinner;
  private int spinnerManualValue;
  private final JPanel subOperationScreen;

  // For Operation Screen, Add buttons to operationScreen.
  private final String[] operationButtonNames = new String[]{
      "Flip", "Color Transform", "Filter", "Dither", "Brightness", "Split", "Combine", "GrayScale",
      "Mosck"
  };

  // Sub Operation button
  private final String[] subOperationButtonNames = new String[]{
      "Horizontal", "Vertical", "GrayScale", "Sepia", "Blur", "Sharp", "Intensity", "Luma",
      "Value", "Red", "Green", "Blue", "Image 1", "Image 2", "Image 3", "Red Component",
      "Green Component", "Blue Component", "Increase", "Decrease", "Manual"
  };

  /**
   * Creates and returns a JLabel object to display the title of the application.
   *
   * @return a JLabel object representing the title of the application
   */
  private JLabel titleLabel() {
    JLabel titleLabel = new JLabel("IMAGE PROCESSING APPLICATION");
    titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24)); // Set font size and style
    titleLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally
    titleLabel.setVerticalAlignment(JLabel.CENTER); // Center the text vertically
    return titleLabel;
  }

  /**
   * Constructs a new JFrameView object.
   *
   * @param readModel the model to be used by the view
   */
  public JFrameView(ReadModel readModel) {
    super("Photo Editor");
    this.readModel = readModel;
    filter = new FileNameExtensionFilter(
        "PPM, PNG, JPG, and BMP Images", "jpg", "ppm", "png", "bmp");
    spinnerManualValue = 5;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Create main panel with GridBagLayout.
    JPanel mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
    mainPanel.setPreferredSize(screenSize);
    setSize(1000, 700);

    getContentPane().add(mainPanel, BorderLayout.CENTER);

    // Create titlePanel with GridLayout, Add Title to titlePanel.
    JPanel titlePanel = new JPanel(new GridLayout(1, 1, 0, 0));
    titlePanel.add(titleLabel(), BorderLayout.CENTER);

    // Create mainScreen with GridBagLayout.
    JPanel mainScreen = new JPanel(new GridBagLayout());

    // Add titlePanel and mainScreen to main panel with GridBagConstraints.
    GridBagConstraints c = new GridBagConstraints();
    gridLayout(c, 0, 0, 1, 0.05, GridBagConstraints.BOTH,
        new int[]{5, 2, 5, 2});

    mainPanel.add(titlePanel, c);

    c.gridy = 1;
    c.weighty = 0.95;
    mainPanel.add(mainScreen, c);

    // Create upperScreen and lowerScreen with GridBagLayout.
    JPanel upperScreen = new JPanel(new GridBagLayout());
    JPanel lowerScreen = new JPanel(new GridBagLayout());

    GridBagConstraints k = new GridBagConstraints();
    gridLayout(k, 0, 0, 1, 0.9, GridBagConstraints.BOTH,
        new int[]{5, 2, 5, 2});

    mainScreen.add(upperScreen, k);

    k.gridy = 1;
    k.weighty = 0.1;
    mainScreen.add(lowerScreen, k);

    // Create combineScreen with GridBagLayout.
    combineScreen = new JPanel(new GridBagLayout());
    combineScreen.setBorder(BorderFactory.createTitledBorder("COMBINE DISPLAY"));

    // Create Photo screen with GridBagLayout.
    outputScreen = new JPanel(new GridLayout(1, 2));
    outputScreen.setBorder(BorderFactory.createTitledBorder("DISPLAY"));

    // Create operationScreen with GridBagLayout.
    JPanel operationScreen = new JPanel(new GridBagLayout());
    operationScreen.setBorder(BorderFactory.createTitledBorder("OPERATIONS"));

    GridBagConstraints o = new GridBagConstraints();
    gridLayout(o, 0, 0, 0.95, 1, GridBagConstraints.BOTH,
        new int[]{5, 2, 5, 2});

    upperScreen.add(outputScreen, o);

    upperScreen.add(combineScreen, o);
    combineScreen.setVisible(false);

    o.gridx = 1;
    o.weightx = 0.05;
    upperScreen.add(operationScreen, o);

    // Create photoScreen and histogramScreen with GridBagLayout.
    JPanel photoScreen = new JPanel(new GridBagLayout());
    photoScreen.setBorder(BorderFactory.createTitledBorder(" IMAGE DISPLAY "));

    JPanel histogramScreen = new JPanel(new GridBagLayout());
    histogramScreen.setBorder(BorderFactory.createTitledBorder(" HISTOGRAM DISPLAY "));

    GridBagConstraints a = new GridBagConstraints();
    gridLayout(a, 0, 0, 0.5, 1, GridBagConstraints.BOTH,
        new int[]{0, 0, 0, 0});

    outputScreen.add(photoScreen, a);

    a.gridx = 1;
    outputScreen.add(histogramScreen, a);

    // Create subOperationScreen and uploadScreen with GridBagLayout.
    subOperationScreen = new JPanel(new GridBagLayout());
    subOperationScreen.setBorder(BorderFactory.createTitledBorder("SUB OPERATIONS"));

    JPanel uploadScreen = new JPanel(new GridBagLayout());
    uploadScreen.setBorder(BorderFactory.createTitledBorder("LOAD/SAVE IMAGE"));

    GridBagConstraints r = new GridBagConstraints();
    gridLayout(r, 0, 0, 0.9, 1, GridBagConstraints.BOTH,
        new int[]{5, 2, 5, 2});

    lowerScreen.add(subOperationScreen, r);

    r.gridx = 1;
    r.weightx = 0.1;
    lowerScreen.add(uploadScreen, r);

    // Add main panel to frame
    add(mainPanel);

    // Set frame properties
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // Start write an inner code for individual Panel.

    // Add Image to photoScreen.
    imageLabel = new JLabel("No image loaded!!!");
    imageLabel.setHorizontalAlignment(JLabel.CENTER);

    JScrollPane scrollPanePhotoScreen = new JScrollPane(imageLabel);
    scrollPanePhotoScreen.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPanePhotoScreen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    GridBagConstraints img = new GridBagConstraints();
    gridLayout(img, 0, 0, 1, 1, GridBagConstraints.BOTH,
        new int[]{5, 2, 5, 2});

    photoScreen.add(scrollPanePhotoScreen, img);

    // For Histogram Screen.
    histogramLabel = new JLabel("No image loaded!!!");
    histogramLabel.setHorizontalAlignment(JLabel.CENTER);
    histogramScreen.add(histogramLabel);

    histogramPanel = new JPanel();
    JScrollPane scrollPaneHistogramScreen = new JScrollPane(histogramPanel);
    scrollPaneHistogramScreen.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPaneHistogramScreen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    histogramScreen.add(scrollPaneHistogramScreen, img);

    pixelChannelValue = new long[4][256];
    for (long[] longs : pixelChannelValue) {
      Arrays.fill(longs, 0);
    }

    operationButtons = new CustomButton[operationButtonNames.length];

    GridBagConstraints t = new GridBagConstraints();
    gridLayout(t, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL,
        new int[]{1, 12, 1, 12});

    for (int i = 0; i < operationButtonNames.length; i++) {
      t.gridy = i;
      operationButtons[i] = new CustomButton(operationButtonNames[i]);
      operationScreen.add(operationButtons[i], t);
      operationButtons[i].setEnabled(false);
      operationButtons[i].setToolTipText(
          "Select " + operationButtonNames[i] + " to Perform it's Sub Operations");
    }

    // For Sub Operation Screen, start text and dither text
    defaultLabel = new JLabel[2];

    defaultLabel[0] = new JLabel("Please click on any Operation");
    defaultLabel[1] = new JLabel("Dither is Performed!!");
    subOperationScreen.add(defaultLabel[0]);
    subOperationScreen.add(defaultLabel[1]);
    defaultLabel[1].setVisible(false);

    spinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
    // set the preferred size of the spinner
    spinner.setPreferredSize(new Dimension(50, 25));
    // apply a custom UI to the spinner
    spinner.setUI(new CustomSpinnerUI());
    spinner.setToolTipText("Do Increment or Decrement after Selecting the Value");

    subOperationButtons = new CustomSubButton[subOperationButtonNames.length];
    String[] operationSaveLoadName = new String[]{"Upload", "Save"};
    operationSaveLoad = new CustomButton[operationSaveLoadName.length];

    GridBagConstraints p = new GridBagConstraints();
    p.insets = new java.awt.Insets(0, 3, 0, 3);
    p.fill = GridBagConstraints.HORIZONTAL;

    for (int i = 0; i < subOperationButtonNames.length; i++) {
      subOperationButtons[i] = new CustomSubButton(subOperationButtonNames[i]);
      subOperationScreen.add(subOperationButtons[i], p);
      subOperationButtons[i].setVisible(false);
      // Add a Tip Text to  buttons
      subOperationButtons[i].setToolTipText(
          "Select " + subOperationButtonNames[i] + " to Perform Operation");
    }
    // to add unique Tip Text for Manual Button.
    subOperationButtons[20].setToolTipText(
        "Select Manual to Add Manual Brightness Value");
    // to add unique Tip Text for Brightness related buttons.
    subOperationButtons[18].setToolTipText(
        "Select  to " + subOperationButtonNames[18] + " Brightness by " + spinnerManualValue);
    subOperationButtons[19].setToolTipText(
        "Select  to " + subOperationButtonNames[19] + " Brightness by " + spinnerManualValue);

    subOperationScreen.add(spinner, p);
    spinner.setVisible(false);

    // For Upload Save Screen
    GridBagConstraints u = new GridBagConstraints();
    gridLayout(u, 0, 0, 0.5, 1, GridBagConstraints.HORIZONTAL,
        new int[]{0, 10, 0, 10});

    for (int i = 0; i < operationSaveLoadName.length; i++) {
      u.gridx = i;
      operationSaveLoad[i] = new CustomButton(operationSaveLoadName[i]);
      uploadScreen.add(operationSaveLoad[i], u);
      operationSaveLoad[i].setToolTipText("Please Select " + operationSaveLoadName[i]);
    }
    operationSaveLoad[0].setToolTipText("Please Upload Photo to Unable all other Operations");

    operationSaveLoad[1].setEnabled(false);

    // For Combine Screen Pop-Up, Create Combine Containers with GridBagLayout.
    JPanel[] jPanelCombineScreen = new JPanel[jPanelCombineScreenName.length];
    operationCombineImageLabel = new JLabel[jPanelCombineScreenName.length];
    JScrollPane[] scrollPaneCombineScreen = new JScrollPane[jPanelCombineScreenName.length];

    GridBagConstraints y = new GridBagConstraints();
    gridLayout(y, 0, 0, 1, 1, GridBagConstraints.BOTH,
        new int[]{5, 2, 5, 2});

    for (int i = 0; i < jPanelCombineScreenName.length; i++) {
      y.gridx = i;
      jPanelCombineScreen[i] = new JPanel(new GridBagLayout());
      jPanelCombineScreen[i].setBorder(
          BorderFactory.createTitledBorder(jPanelCombineScreenName[i]));
      combineScreen.add(jPanelCombineScreen[i], y);

      operationCombineImageLabel[i] = new JLabel("No image loaded!!!");
      operationCombineImageLabel[i].setHorizontalAlignment(JLabel.CENTER);

      scrollPaneCombineScreen[i] = new JScrollPane(operationCombineImageLabel[i]);
      scrollPaneCombineScreen[i].setHorizontalScrollBarPolicy(
          JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      scrollPaneCombineScreen[i].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      jPanelCombineScreen[i].add(scrollPaneCombineScreen[i], img);
    }
  }

  @Override
  public void addFeatures(ExtensibleController controller) {

    // Load.
    operationSaveLoad[0].addActionListener(e -> {
      String imagePath = getAbsolutePath();
      if (imagePath != null) {
        controller.loadImage(imagePath);
      }
      // make default screen
      setVisibility(new int[0], new boolean[]{true, false}, new boolean[]{true, false},
          new boolean[]{false, false});
    });

    // Save.
    operationSaveLoad[1].addActionListener(evt -> {
      String imagePath = getAbsolutePath();
      if (imagePath != null) {
        controller.saveImage(imagePath);
      }
    });

    // Flip
    operationButtons[0].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("FLIP OPERATION"));
      setVisibility(
          new int[]{0, 1}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{true, false},// image to be visible
          new boolean[]{false, false}); // Spinner Visibility
    });

    // Color Transformation
    operationButtons[1].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("COLOR TRANSFER OPERATION"));
      setVisibility(
          new int[]{2, 3}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{true, false}, // image to be visible
          new boolean[]{false, false}); // Spinner Visibility
    });

    // Filter
    operationButtons[2].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("FILTER OPERATION"));
      setVisibility(
          new int[]{4, 5}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{true, false}, // image to be visible
          new boolean[]{false, false}); // Spinner Visibility
    });

    // Dither
    operationButtons[3].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("DITHER OPERATION"));
      setVisibility(
          new int[0], // subOperationButtons to be visible
          new boolean[]{false, true}, // defaultLabel to be visible
          new boolean[]{true, false}, // image to be visible
          new boolean[]{false, false}); // Spinner Visibility
    });

    // Brightness
    operationButtons[4].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("BRIGHTNESS OPERATION"));
      setVisibility(
          new int[]{18, 19}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{true, false}, // image to be visible
          new boolean[]{true, false}); // Spinner Visibility
    });

    // Manual Brightness
    subOperationButtons[20].addActionListener(e -> setVisibility(
        new int[]{18, 19}, // subOperationButtons to be visible
        new boolean[]{false, false}, // defaultLabel to be visible
        new boolean[]{true, false}, // image to be visible
        new boolean[]{false, true})); // Spinner Visibility

    // Split
    operationButtons[5].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("SPLIT OPERATION"));
      setVisibility(
          new int[]{15, 16, 17}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{true, false}, // image to be visible
          new boolean[]{false, false}); // Spinner Visibility
    });

    // Combine
    operationButtons[6].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("COMBINE OPERATION"));
      setVisibility(
          new int[]{12, 13, 14}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{false, true}, // image to be visible
          new boolean[]{false, false}); // Spinner Visibility
    });

    // Greyscale
    operationButtons[7].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("GRAYSCALE OPERATION"));
      setVisibility(
          new int[]{6, 7, 8, 9, 10, 11}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{true, false}, // image to be visible
          new boolean[]{false, false}); // Spinner Visibility
    });

    // Mosaic
    operationButtons[8].addActionListener(e -> {
      subOperationScreen.setBorder(BorderFactory.createTitledBorder("MOSAIC OPERATION"));
      setVisibility(
          new int[]{}, // subOperationButtons to be visible
          new boolean[]{false, false}, // defaultLabel to be visible
          new boolean[]{true, false}, // image to be visible
          new boolean[]{true, false}); // Spinner Visibility
    });

    // horizontal
    subOperationButtons[0].addActionListener(e -> controller.horizontalImage());

    // Vertical
    subOperationButtons[1].addActionListener(e -> controller.verticalImage());

    // GrayScale
    subOperationButtons[2].addActionListener(e -> controller.greyscaleImage());

    // Sepia
    subOperationButtons[3].addActionListener(e -> controller.sepiaImage());

    // Blur
    subOperationButtons[4].addActionListener(e -> controller.blurImage());

    // Sharp
    subOperationButtons[5].addActionListener(e -> controller.sharpImage());

    // Dither
    operationButtons[3].addActionListener(e -> controller.dither());

    //Combine
    subOperationButtons[12].addActionListener(e -> combineImage(controller, 0));
    subOperationButtons[13].addActionListener(e -> combineImage(controller, 1));
    subOperationButtons[14].addActionListener(e -> combineImage(controller, 2));

    // Split
    operationButtons[5].addActionListener(e -> controller.rgbSplit());

    // Intensity
    subOperationButtons[6].addActionListener(e -> controller.intensityImage());

    // Luma
    subOperationButtons[7].addActionListener(e -> controller.lumaImage());

    // Value
    subOperationButtons[8].addActionListener(e -> controller.valueImage());

    // Red
    subOperationButtons[9].addActionListener(e -> controller.redImage());

    // Green
    subOperationButtons[10].addActionListener(e -> controller.greenImage());

    // Blue
    subOperationButtons[11].addActionListener(e -> controller.blueImage());

    // Split
    subOperationButtons[15].addActionListener(e -> controller.rgbSplitImageShow(0));
    subOperationButtons[16].addActionListener(e -> controller.rgbSplitImageShow(1));
    subOperationButtons[17].addActionListener(e -> controller.rgbSplitImageShow(2));

    // Brightness
    spinner.addChangeListener(e -> {
      spinnerManualValue = (int) spinner.getValue();
      subOperationButtons[18].setToolTipText(
          "Select  to " + subOperationButtonNames[18] + " Brightness by " + spinnerManualValue);
      subOperationButtons[19].setToolTipText(
          "Select  to " + subOperationButtonNames[19] + " Brightness by " + spinnerManualValue);
    });

    subOperationButtons[18].addActionListener(e -> controller.brightnessImage(spinnerManualValue));
    subOperationButtons[19].addActionListener(e -> controller.brightnessImage(-spinnerManualValue));

    subOperationButtons[21].addActionListener((e -> controller.mosckImage(100)));


  }

  @Override
  public void loadImage(String imageName) {
    outputScreen.setVisible(true);
    combineScreen.setVisible(false);

    ImageIcon imageIcon = new ImageIcon(saveImage(imageName));
    histogramPanel.add(new CreateHistogram(pixelChannelValue));
    imageLabel.setIcon(imageIcon);

    for (int i = 0; i < operationButtonNames.length; i++) {
      operationButtons[i].setEnabled(true);
    }
    operationSaveLoad[1].setEnabled(true);
  }

  @Override
  public void loadCombineImage(String imageName, int uniqueID) {
    operationCombineImageLabel[uniqueID].setText("");
    operationCombineImageLabel[uniqueID].setIcon(new ImageIcon(saveImage(imageName)));
  }

  @Override
  public void clearScreen() {
    imageLabel.setText("");
    histogramLabel.setText("");
    histogramPanel.removeAll();
    for (long[] longs : pixelChannelValue) {
      Arrays.fill(longs, 0);
    }
    for (int i = 0; i < jPanelCombineScreenName.length; i++) {
      operationCombineImageLabel[i].setIcon(null);
      operationCombineImageLabel[i].setText("No image loaded!!!");
    }
  }

  @Override
  public void errorPopUp(String errorMessage) {
    JOptionPane.showMessageDialog(JFrameView.this, errorMessage, "Message",
        JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Displays a file chooser dialog box and returns the absolute path of the selected file.
   *
   * @return The absolute path of the selected file or null if the dialog is cancelled.
   */
  private String getAbsolutePath() {
    final JFileChooser fChooser = new JFileChooser(".");
    fChooser.setFileFilter(this.filter);
    int retValue = fChooser.showOpenDialog(JFrameView.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      return f.getAbsolutePath();
    } else {
      return null;
    }
  }

  /**
   * Creates a BufferedImage of the given image data and returns it.
   *
   * @param imageName The name of the image data to create a BufferedImage of.
   * @return The BufferedImage of the given image data.
   */
  private BufferedImage saveImage(String imageName) {
    GetImageDetails imageData = this.readModel.getImageData().get(imageName);
    BufferedImage saveImage = new BufferedImage(imageData.getWidth(), imageData.getHeight(),
        BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < imageData.getHeight(); i++) {
      for (int j = 0; j < imageData.getWidth(); j++) {
        Color rgba = imageData.getPixelValue(i, j).colorInstance();
        saveImage.setRGB(j, i, rgba.getRGB());
        pixelChannelValue[0][rgba.getRed()] += 1;
        pixelChannelValue[1][rgba.getGreen()] += 1;
        pixelChannelValue[2][rgba.getBlue()] += 1;
        pixelChannelValue[3][(rgba.getRed() + rgba.getGreen() + rgba.getBlue()) / 3] += 1;
      }
    }
    return saveImage;
  }

  /**
   * Sets the visibility of various UI elements based on the provided parameters.
   *
   * @param visibleSubButtons An array of integers representing the indices of sub-operation buttons
   *                          to be set visible.
   * @param hiddenLabels      An array of booleans representing whether the default labels should be
   *                          hidden (true) or shown (false).
   * @param screensVisible    An array of booleans representing whether the different screens should
   *                          be visible (true) or hidden (false).
   */
  private void setVisibility(int[] visibleSubButtons, boolean[] hiddenLabels,
      boolean[] screensVisible, boolean[] spinnerVisibility) {
    // default set all invisible
    for (CustomSubButton subOperationButton : subOperationButtons) {
      subOperationButton.setVisible(false);
    }

    for (int i : visibleSubButtons) {
      subOperationButtons[i].setVisible(true);
    }

    defaultLabel[0].setVisible(hiddenLabels[0]);
    defaultLabel[1].setVisible(hiddenLabels[1]);

    outputScreen.setVisible(screensVisible[0]);
    combineScreen.setVisible(screensVisible[1]);

    subOperationButtons[20].setVisible(spinnerVisibility[0]);
    spinner.setVisible(spinnerVisibility[1]);
  }

  /**
   * Combines the image of this object with another image using the specified mode. If this object
   * has a valid image path, it will be used for the operation.
   *
   * @param controller the controller used for the operation
   * @param mode       the mode used for the combination (e.g. blending, overlay, etc.)
   */
  private void combineImage(IController controller, int mode) {
    String imagePath = getAbsolutePath();
    if (imagePath != null) {
      controller.combineImage(imagePath, mode);
    }
  }

  /**
   * Sets the constraints for a component in a grid layout.
   *
   * @param grid    the GridBagConstraints object to be modified
   * @param xAxis   the column index of the component
   * @param yAxis   the row index of the component
   * @param xWeight the horizontal weight of the component
   * @param yWeight the vertical weight of the component
   * @param fill    the fill behavior of the component
   * @param insets  an array of integers specifying the top, left, bottom, and right insets of the
   *                component, in that order
   */
  private void gridLayout(GridBagConstraints grid, int xAxis, int yAxis, double xWeight,
      double yWeight, int fill, int[] insets) {
    grid.gridx = xAxis;
    grid.gridy = yAxis;
    grid.weightx = xWeight;
    grid.weighty = yWeight;
    grid.fill = fill;
    grid.insets = new java.awt.Insets(insets[0], insets[1], insets[2], insets[3]);
  }
}
