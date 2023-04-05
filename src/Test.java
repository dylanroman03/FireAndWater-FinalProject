import static utilities.Constants.PATH_BOX;
import static utilities.Helpers.getImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Test extends JPanel implements ActionListener {

  private static final int ANCHO_VENTANA = 800;
  private static final int ALTO_VENTANA = 600;
  private static final int FRECUENCIA_OSCILACION = 20; // Mayor frecuencia = movimiento más suave

  private int angulo = 45;
  private Timer timer;
  protected Rectangle2D.Float rectangle;
  private BufferedImage image;

  public Test() {
    image = getImage(PATH_BOX);
    rectangle = new Rectangle2D.Float(400.0f, 200.0f, 50.0f, 100.0f);
    timer = new Timer(FRECUENCIA_OSCILACION, this);
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Cast the graphics object to Graphics2D for advanced rendering
    Graphics2D g2 = (Graphics2D) g;

    // Save the original transform to restore later
    AffineTransform oldTransform = g2.getTransform();

    // Get the center coordinates of the rectangle
    double centerX = rectangle.getCenterX();
    double centerY = rectangle.getCenterY();

    // Create a new affine transform to rotate the graphics
    AffineTransform transform = new AffineTransform();
    transform.rotate(Math.toRadians(angulo), centerX, centerY);

    // Create a new shape by applying the transform to the original rectangle
    Shape rotatedRectangle = transform.createTransformedShape(rectangle);

    // Set the color and draw the rotated rectangle
    g2.setColor(Color.RED);
    g2.draw(rotatedRectangle);

    // Save the current transform to restore later
    AffineTransform oldTransform2 = g2.getTransform();

    // Apply the transform to the graphics to draw the image
    g2.transform(transform);

    // Get the bounding rectangle of the rotated rectangle
    Rectangle2D bounds = rotatedRectangle.getBounds2D();

    // Calculate the x and y coordinates for drawing the image
    int x = (int) bounds.getX() + (int) (bounds.getWidth() - rectangle.getWidth()) / 2;
    int y = (int) bounds.getY() + (int) (bounds.getHeight() - rectangle.getHeight()) / 2;

    // Draw the image using the calculated x, y, width, and height
    g2.drawImage(image, x, y, (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);

    Rectangle2D.Float originalRectangle = new Rectangle2D.Float();
    originalRectangle.setFrame(bounds.getX(), bounds.getY(), rectangle.getWidth(), rectangle.getHeight());
    rectangle.setFrame(originalRectangle);

    // Restore the previous transform then the original transform
    g2.setTransform(oldTransform2);
    g2.setTransform(oldTransform);
  }

  public void actionPerformed(ActionEvent e) {
    // angulo++;
    repaint();
  }

  public static void main(String[] args) {
    JFrame ventana = new JFrame("Tronco Oscilante");
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setSize(ANCHO_VENTANA, ALTO_VENTANA);
    Test tronco = new Test();
    ventana.add(tronco);
    ventana.setVisible(true);
  }
}
