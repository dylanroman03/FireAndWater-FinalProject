import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {

    private Rectangle2D platform;
    private Rectangle2D rectangle;

    private double platformAngle = 0;

    public Test() {
        platform = new Rectangle2D.Double(100, 400, 200, 50);
        rectangle = new Rectangle2D.Double(250, 410, 50, 50);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Rotate the platform to the current angle
        AffineTransform oldTransform = g2.getTransform();
        AffineTransform transform = new AffineTransform();
        double centerX = platform.getX() + platform.getWidth() / 2;
        double centerY = platform.getY() + platform.getHeight() / 2;
        transform.rotate(Math.toRadians(platformAngle), centerX, centerY);
        Shape rotatedPlatform = transform.createTransformedShape(platform);
        g2.setColor(Color.BLUE);
        g2.fill(rotatedPlatform);

        // Check for collision with the rectangle
        if (rectangle.intersects(platform)) {
            // Calculate the angle necessary to make the rectangle hit the platform at a flat angle
            double xDiff = rectangle.getX() + rectangle.getWidth() / 2 - centerX;
            double yDiff = rectangle.getY() + rectangle.getHeight() / 2 - centerY;
            double angle = Math.atan2(yDiff, xDiff);
            platformAngle = Math.toDegrees(angle);

            // Rotate the platform to the new angle
            transform = new AffineTransform();
            transform.rotate(Math.toRadians(platformAngle), centerX, centerY);
            rotatedPlatform = transform.createTransformedShape(platform);
            g2.setColor(Color.RED);
            g2.fill(rotatedPlatform);
        }

        // Draw the rectangle
        g2.setColor(Color.GREEN);
        g2.fill(rectangle);

        // Restore the old transform
        g2.setTransform(oldTransform);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new Test());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}