package gui;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DIALOG;
import static utilities.Helpers.getImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import main.GamePanel;

public class Dialog {
  private BufferedImage dialog;
  protected Game game;
  protected int width = TILES_SIZE * 13;;
  protected int height = TILES_SIZE * 13;;
  protected int x = (GAME_WIDTH / 2) - (width / 2);
  protected int y = (GAME_HEIGHT / 2) - (height / 2);

  public Dialog(Game game) {
    this.game = game;
    dialog = getImage(PATH_DIALOG);
  }

  public Dialog(Game game, int width, int height) {
    this.game = game;
    this.width = width;
    this.height = height;

    this.x = (GAME_WIDTH / 2) - (width / 2);
    this.y = (GAME_HEIGHT / 2) - (height / 2);
    dialog = getImage(PATH_DIALOG);
  }

  public void render(Graphics g, GamePanel gamePanel) {
    g.drawImage(dialog, x, y, width, height, null);
  }

}
