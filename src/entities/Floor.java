package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Floor extends Entity {
  private float xInit;
  private float yInit;
  BufferedImage image;
  public int id = 0;
  private boolean climbing = false;
  private boolean dropping = false;

  public Floor(float x, float y, BufferedImage image) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    init(image, x, y);
  }

  public Floor(float x, float y, BufferedImage image, int id) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    init(image, x, y);

    this.id = id;
  }

  private void init(BufferedImage image, float x, float y) {
    this.image = image;
    xInit = x;
    yInit = y;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUGING) {
      showHitBox(g);
    }
  }

  public void update() {
    if (climbing) {
      if (yInit - 100 < hitBox.y) {
        hitBox.y -= 2;
      }
    } else if (dropping) {
      if (hitBox.y < yInit) {
        hitBox.y += 10;
      }
    }
  }

  public void setId(int id) {
    this.id = id;
  }

  public void move(boolean up) {
    if (up) {
      climbing = true;
      dropping = false;
    } else {
      climbing = false;
      dropping = true;
    }
  }
}
