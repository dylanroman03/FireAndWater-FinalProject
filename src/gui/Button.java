package gui;

import static utilities.Constants.PATH_BUTTONS;
import static utilities.Constants.PATH_BUTTON_SOUND;
import static utilities.Helpers.getAnimationsY;
import static utilities.Helpers.playSound;
import static utilities.Helpers.resizeImage;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
  private BufferedImage[] images;
  private ImageIcon[] icons = new ImageIcon[3];
  private int x, y;

  /**
   * Constructor de la clase {@link Button}
   * 
   * @param x
   * @param y
   * @param message
   */
  public Button(int x, int y, String message) {
    images = getAnimationsY(PATH_BUTTONS);

    Image imageResized = resizeImage(images[0],
        (int) ((images[0].getWidth() / 3) * 1.7),
        (int) ((images[0].getHeight() / 3) * 1.3));

    setIcon(new ImageIcon(imageResized));

    this.x = x;
    this.y = y;

    setSize((int) (imageResized.getWidth(null)), (int) (imageResized.getHeight(null)));
    setBorder(BorderFactory.createEmptyBorder());
    setContentAreaFilled(false);

    setText(message);
    setFont(new Font("MinimalPixel", Font.PLAIN, 60));
    setForeground(java.awt.Color.WHITE);
    setHorizontalTextPosition(JButton.CENTER);
    
    Image imageResizedTwo = resizeImage(images[1],
        (int) ((images[0].getWidth() / 3) * 1.7),
        (int) ((images[0].getHeight() / 3) * 1.3));

    Image imageResizedThree = resizeImage(images[2],
        (int) ((images[0].getWidth() / 3) * 1.7),
        (int) ((images[0].getHeight() / 3) * 1.3));


    icons[0] = new ImageIcon(imageResized); 
    icons[1] = new ImageIcon(imageResizedTwo);
    icons[2] = new ImageIcon(imageResizedThree);

    addActionListener(new ChangeButton());
    addMouseListener(new HoeverButton());
  }

  /**
   * SetBounds segun las coordenadas su tamaño y x, y
   */
  public void setBounds() {
    setBounds(x, y, getWidth(), getHeight());
  }

  /**
   * Clase privada que implementa {@link ActionListener} y reproduce un sonido al
   * presionar el boton
   */
  private class ChangeButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) { 
      playSound(PATH_BUTTON_SOUND);
    }
  }

  private class HoeverButton implements MouseListener {
    @Override
    public void mouseEntered(MouseEvent e) {
      setIcon(icons[2]);
    }

    @Override
    public void mouseExited(MouseEvent e) {
      setIcon(icons[0]);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
      setIcon(icons[1]);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
  }
}
