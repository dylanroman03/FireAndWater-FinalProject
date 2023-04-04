package gui;

import static utilities.Constants.PATH_BUTTONS;
import static utilities.Helpers.getAnimationsY;
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

  public Button(Image newImg, BufferedImage[] images, int x, int y) {
    super(new ImageIcon(newImg));

    this.images = images;
    this.x = x;
    this.y = y;

    setSize((int) (images[0].getWidth()), (int) (images[0].getHeight()));
    setBorder(BorderFactory.createEmptyBorder());
    setContentAreaFilled(false);
    addActionListener(new ChangeButton());
  }

  public Button(int x, int y, String message) {
    images = getAnimationsY(PATH_BUTTONS);

    Image playResized = resizeImage(images[0],
        (int) ((images[0].getWidth() / 3) * 1.3),
        (int) ((images[0].getHeight() / 3) * 1.3));

    setIcon(new ImageIcon(playResized));

    this.x = x;
    this.y = y;

    setSize((int) (images[0].getWidth()), (int) (images[0].getHeight()));
    setBorder(BorderFactory.createEmptyBorder());
    setContentAreaFilled(false);

    setText(message);
    setFont(new Font("MinimalPixel", Font.PLAIN, 60));
    setForeground(java.awt.Color.WHITE);
    setHorizontalTextPosition(JButton.CENTER);

    addActionListener(new ChangeButton());
  }

  public void setBounds() {
    setBounds(x, y, getWidth(), getHeight());
  }

  private class ChangeButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("CLICK SOUND");
    }
  }

}
