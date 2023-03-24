package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Floor extends Entity {
  int xInit;
  int yInit;
  BufferedImage image;

  public Floor(float x, float y, BufferedImage image) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    this.image = image;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUGING) {
      showHitBox(g);
    }
  }
}
