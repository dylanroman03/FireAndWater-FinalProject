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
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
  private BufferedImage[] images;
  private int x, y;


  /**
   * Constructor de la clase {@link Button}
   * @param x
   * @param y
   * @param message
   */
  public Button(int x, int y, String message) {
    images = getAnimationsY(PATH_BUTTONS);

    Image playResized = resizeImage(images[0],
        (int) ((images[0].getWidth() / 3) * 1.7),
        (int) ((images[0].getHeight() / 3) * 1.3));

    setIcon(new ImageIcon(playResized));

    this.x = x;
    this.y = y;

    setSize((int) (playResized.getWidth(null)), (int) (playResized.getHeight(null)));
    setBorder(BorderFactory.createEmptyBorder());
    setContentAreaFilled(false);

    setText(message);
    setFont(new Font("MinimalPixel", Font.PLAIN, 60));
    setForeground(java.awt.Color.WHITE);
    setHorizontalTextPosition(JButton.CENTER);

    addActionListener(new ChangeButton());
  }

  /**
   * SetBounds segun las coordenadas su tamaño y x, y
   */
  public void setBounds() {
    setBounds(x, y, getWidth(), getHeight());
  }

  /**
   * Clase privada que implementa {@link ActionListener} y reproduce un sonido al presionar el boton
   */
  private class ChangeButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
    	playSound(PATH_BUTTON_SOUND);
    }
  }

}
