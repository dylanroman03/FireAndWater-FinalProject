package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DIALOG;
import static utilities.Constants.PATH_PLAY_BTN;
import static utilities.Helpers.getAnimationsY;
import static utilities.Helpers.getImage;
import static utilities.Helpers.resizeImage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import gui.Button;
import main.Game;
import main.GamePanel;
import main.GameWindow;
import utilities.Constants.States;

public class Menu {
  private BufferedImage dialog;
  private BufferedImage[] playBtnImg;
  private Button playButton;
  private boolean flag = true;
  private Game game;
  private GamePanel gamePanel;

  public Menu(Game game) {
    this.game = game;
    dialog = getImage(PATH_DIALOG);
    playBtnImg = getAnimationsY(PATH_PLAY_BTN);
    initClasses();
  }

  private void initClasses() {
    Image playResized = resizeImage(playBtnImg[0], (int) (playBtnImg[0].getWidth() / 2), (int) (playBtnImg[0].getHeight() / 2));
    playButton = new Button(playResized, playBtnImg, (GAME_WIDTH / 2) - (int) (playBtnImg[0].getWidth() / 3.7), (GAME_HEIGHT / 2) - (int) (playBtnImg[0].getHeight() / 1.7));

    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setPlaying();
      }
    });
  }

  protected void setPlaying() {
    game.setState(States.PLAYING);
    gamePanel.requestFocus();
    gamePanel.removeAll();
    gamePanel.revalidate();
  }

  public void render(Graphics g, GameWindow gameWindow, GamePanel gamePanel) {
    this.gamePanel = gamePanel;
    g.drawImage(dialog, (GAME_WIDTH - (TILES_SIZE * 8)) / 2, (GAME_HEIGHT - (TILES_SIZE * 8)) / 2, TILES_SIZE * 8,
        TILES_SIZE * 8, null);

    if (flag) {
      gamePanel.add(playButton);
      playButton.setBounds();
      gamePanel.add(new JButton("credits"));
      gamePanel.revalidate();
      flag = false;
    }
  }

  public void update(GameWindow gameWindow, GamePanel gamePanel) {
  }
}
