import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tronco extends JPanel implements ActionListener {

  private static final int ANCHO_VENTANA = 800;
  private static final int ALTO_VENTANA = 600;
  private static final int ANCHO_TRONCO = 100;
  private static final int ALTO_TRONCO = 30;
  private static final int X_TRONCO = ANCHO_VENTANA / 2 - ANCHO_TRONCO / 2;
  private static final int Y_TRONCO_BASE = ALTO_VENTANA - 50;
  private static final int FRECUENCIA_OSCILACION = 10; // Mayor frecuencia = movimiento más suave
  private static final int AMPLITUD_OSCILACION = 30; // Mayor amplitud = mayor oscilación

  private int xTronco;
  private int yTronco;
  private int angulo;
  private Timer timer;

  public Tronco() {
    xTronco = X_TRONCO;
    yTronco = Y_TRONCO_BASE;
    timer = new Timer(FRECUENCIA_OSCILACION, this);
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(102, 51, 0)); // Marrón oscuro
    g.fillRect(xTronco, yTronco, ANCHO_TRONCO, ALTO_TRONCO);
  }

  public void actionPerformed(ActionEvent e) {
    angulo++;
    yTronco = Y_TRONCO_BASE + (int) (AMPLITUD_OSCILACION * Math.sin(Math.toRadians(angulo)));
    repaint();
  }

  public static void main(String[] args) {
    JFrame ventana = new JFrame("Tronco Oscilante");
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setSize(ANCHO_VENTANA, ALTO_VENTANA);
    Tronco tronco = new Tronco();
    ventana.add(tronco);
    ventana.setVisible(true);
  }
}
