package gui;

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

    setSize((int) (images[0].getWidth() / 2), (int) (images[0].getHeight() / 2));
    setBorder(BorderFactory.createEmptyBorder());
    setContentAreaFilled(false);
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
