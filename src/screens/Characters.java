package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_CHARACTERS_LIST;
import static utilities.Constants.PATH_LINE;
import static utilities.Constants.PATH_PLAY_BTN;
import static utilities.Helpers.getAnimationsX;
import static utilities.Helpers.getAnimationsY;
import static utilities.Helpers.getImage;
import static utilities.Helpers.resizeImage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.Button;
import main.Game;
import main.GamePanel;
import utilities.Constants.Heroes;

public class Characters {
  private BufferedImage[][] characters = new BufferedImage[2][];
  private Game game;
  private int aniTick = 0;
  private Heroes selectedHero = Heroes.PINK_MONSTER;
  private Rectangle[] charRects = new Rectangle[2];
  private JTextField input;
  private boolean flag = true;
  private Button button;
  private GamePanel gamePanel;

  public Characters(Game game) {
    this.game = game;
    loadImages();
    initRectangles();
    initComponents();
  }

  public void render(Graphics g, GamePanel gamePanel) {
    this.gamePanel = gamePanel;

    for (int i = 0; i < characters.length; i++) {
      g.drawImage(characters[i][aniTick], (GAME_WIDTH / 2) - (TILES_SIZE * i * 3),
          (GAME_HEIGHT / 7), TILES_SIZE * 3, TILES_SIZE * 3, null);
    }

    g.drawImage(getImage(PATH_LINE), (GAME_WIDTH / 2) - (TILES_SIZE *
        selectedHero.ordinal() * 3), (GAME_HEIGHT / 2) - TILES_SIZE, TILES_SIZE * 3,
        TILES_SIZE / 2, null);

    if (flag) {
      JLabel label = new JLabel("Ingresa tu nombre de Usuario");

      gamePanel.add(input);
      gamePanel.add(label);
      gamePanel.add(button);

      input.setBounds((GAME_WIDTH / 2) - (TILES_SIZE * 3), GAME_HEIGHT - (TILES_SIZE * 5), TILES_SIZE * 6, 30);
      label.setBounds((GAME_WIDTH / 2) - (int) (TILES_SIZE * 2.3), GAME_HEIGHT - (TILES_SIZE * 4), TILES_SIZE * 5, 30);
      button.setBounds();

      gamePanel.revalidate();
      flag = false;
    }
  }

  public void update() {
  }

  private void initRectangles() {
    for (int i = 0; i < characters.length; i++) {
      charRects[i] = new Rectangle((GAME_WIDTH / 2) - (TILES_SIZE * i * 3), (GAME_HEIGHT / 7), TILES_SIZE * 3,
          TILES_SIZE * 3);
    }
  }

  private void initComponents() {
    input = new JTextField();

    BufferedImage[] btnImages = getAnimationsY(PATH_PLAY_BTN);
    Image playResized = resizeImage(btnImages[0], (int) (btnImages[0].getWidth() / 2), (int) (btnImages[0].getHeight() / 2));
    button = new Button(playResized, btnImages, (GAME_WIDTH / 2) - (int) (TILES_SIZE * 2.3), GAME_HEIGHT - (TILES_SIZE * 3));

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        goPlaying();
        flag = true;
      }
    });
  }

  protected void goPlaying() {
    game.setPlayer(selectedHero, input.getText());
  }

  private void loadImages() {
    for (int i = 0; i < characters.length; i++) {
      characters[i] = getAnimationsX(PATH_CHARACTERS_LIST[i][4]);
    }
  }

  public void mouseClick(MouseEvent e) {
    int i = 0;
    for (Rectangle rect : charRects) {
      if (rect.contains(e.getX(), e.getY())) {
        selectedHero = Heroes.values()[i];
      }

      i++;
    }
  }

}
