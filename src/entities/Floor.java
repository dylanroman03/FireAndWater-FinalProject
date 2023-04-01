package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Floor extends Entity {
  private BufferedImage backImage;
  private BufferedImage image;
  public int id = 0;

  public Floor(float x, float y, BufferedImage image, BufferedImage backImg) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    this.backImage = backImg;
    // init(image, x, y);
    this.image = image;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  // private void init(BufferedImage image, float x, float y) {
  //   this.image = image;
  //   initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  // }

  public void render(Graphics g) {
    if (backImage != null) {
      g.drawImage(backImage, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);
    }
    g.drawImage(image, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUGING) {
      showHitBox(g);
    }
  }
}
