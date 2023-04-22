package core.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * CreateHistogram class generates and displays a histogram for a given data array. The histogram is
 * displayed as a line chart with x and y axes.
 */
public class CreateHistogram extends JPanel {

  private final long[] rData;
  private final long[] gData;
  private final long[] bData;
  private final long[] intensityData;
  private final long maxCount;

  /**
   * Constructs a new CreateHistogram object with the given data array.
   *
   * @param data the data array for the histogram
   */
  public CreateHistogram(long[][] data) {
    this.rData = data[0];
    this.gData = data[1];
    this.bData = data[2];
    this.intensityData = data[3];
    maxCount = getMaxCount(data);
    setPreferredSize(new Dimension(600, 500));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int width = getWidth();
    int height = getHeight();
    int padding = 100;

    Graphics2D histogramObject = (Graphics2D) g.create();

    // Set anti-aliasing for smoother lines
    histogramObject.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Draw x-axis
    histogramObject.drawLine(padding,
        height - padding, width - padding, height - padding);

    // Draw y-axis
    histogramObject.drawLine(padding, padding, padding, height - padding);

    // Draw x-axis labels
    for (int i = 0; i <= 255; i += 15) {
      int x = padding + i * (width - 2 * padding) / 255;
      histogramObject.drawString(Integer.toString(i), x, height - (padding + 50) / 2);
    }
    histogramObject.drawString("255", width - padding, height - (padding + 50) / 2);

    // Draw y-axis labels
    for (long i = 0; i <= maxCount; i += maxCount / 10) {
      long y = height - padding - i * (height - 2 * padding) / maxCount;
      histogramObject.drawString(Integer.toString((int) i), padding / 2, y);
    }

    // Add icons and text
    histogramObject.setColor(Color.BLACK);
    histogramObject.drawString("Red Channel: ", padding, height - 40);
    histogramObject.setColor(Color.RED);
    histogramObject.fillRect(padding + 110, height - 49, 10, 10); // add red box
    histogramObject.setColor(Color.BLACK);
    histogramObject.drawString("Green Channel: ", padding, height - 10);
    histogramObject.setColor(Color.GREEN);
    histogramObject.fillRect(padding + 110, height - 19, 10, 10); // add Green box
    histogramObject.setColor(Color.BLACK);
    histogramObject.drawString("Blue Channel: ", (padding + 35) * 2, height - 40);
    histogramObject.setColor(Color.BLUE);
    histogramObject.fillRect(padding + 270, height - 49, 10, 10); // add Blue box
    histogramObject.setColor(Color.BLACK);
    histogramObject.drawString("Intensity: ", (padding + 35) * 2, height - 10);
    histogramObject.setColor(Color.BLACK);
    histogramObject.fillRect(padding + 270, height - 19, 10, 10); // add PINK box

    // Draw histogram line charts for R, G, and B channels
    histogramObject.setColor(Color.RED);
    histogramObject.setStroke(new BasicStroke(2f));
    long xOld = padding;
    long yOld = height - padding;
    drawLine(width, height, padding, histogramObject, xOld, yOld, rData);

    histogramObject.setColor(Color.GREEN);
    histogramObject.setStroke(new BasicStroke(2f));
    yOld = height - padding;
    drawLine(width, height, padding, histogramObject, xOld, yOld, gData);

    histogramObject.setColor(Color.BLUE);
    histogramObject.setStroke(new BasicStroke(2f));
    yOld = height - padding;
    drawLine(width, height, padding, histogramObject, xOld, yOld, bData);

    histogramObject.setColor(Color.BLACK);
    histogramObject.setStroke(new BasicStroke(2f));
    yOld = height - padding;
    drawLine(width, height, padding, histogramObject, xOld, yOld, intensityData);

    histogramObject.dispose();
  }

  /**
   * Returns the maximum count value in the given data array.
   *
   * @param data the data array to search for maximum count value
   * @return the maximum count value in the data array
   */
  private long getMaxCount(long[][] data) {
    long max = 0;
    for (long[] outer : data) {
      for (long inner : outer) {
        if (inner > max) {
          max = inner;
        }
      }
    }
    return max;
  }

  /**
   * Draws a line on the specified Graphics2D object, using data points from an integer array.
   *
   * @param width           the width of the component on which to draw the line
   * @param height          the height of the component on which to draw the line
   * @param padding         the padding around the edges of the component
   * @param histogramObject the Graphics2D object on which to draw the line
   * @param rData           an array of integers representing the data points to be plotted on the
   *                        line
   */
  private void drawLine(long width, long height, long padding, Graphics2D histogramObject,
      long xOld, long yOld,
      long[] rData) {
    for (int i = 0; i < rData.length; i++) {
      long x = padding + i * (width - 2 * padding) / 255;
      long y = height - padding - rData[i] * (height - 2 * padding) / maxCount;
      histogramObject.drawLine((int) xOld, (int) yOld, (int) x, (int) y);
      xOld = x;
      yOld = y;
    }
  }

}