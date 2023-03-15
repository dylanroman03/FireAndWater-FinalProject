package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Floor extends Entity {
  int xInit;
  int yInit;

  public Floor(float x, float y) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  public void render(BufferedImage levelFloor, Graphics g) {
    g.drawImage(levelFloor, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUGING) {
      showHitBox(g);
    }
  }
}
