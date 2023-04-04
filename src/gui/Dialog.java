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

  public Dialog(Game game) {
    this.game = game;
    dialog = getImage(PATH_DIALOG);
  }

  public void render(Graphics g, GamePanel gamePanel) {
    g.drawImage(dialog, (GAME_WIDTH - (TILES_SIZE * 13)) / 2, (GAME_HEIGHT - (TILES_SIZE * 13)) / 2,
        TILES_SIZE * 13, TILES_SIZE * 13, null);
  }

}
