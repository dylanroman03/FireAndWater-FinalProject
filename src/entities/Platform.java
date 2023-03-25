package entities;

import static main.Game.DEBUGING;
import static main.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Platform extends Entity {
  private float yInit;
  private BufferedImage image;
  public int id;
  private boolean climbing;
  private boolean dropping;

  public Platform(float x, float y, BufferedImage image, int id) {
    super(x, y, TILES_SIZE * 2, (int) (TILES_SIZE / 3));
    this.image = image;
    yInit = y;
    initHitBox(x, y, width, height);

    this.id = id;
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    g.drawImage(image, (int) hitBox.x, (int) hitBox.y, width, height, null);

    if (DEBUGING) {
      showHitBox(g);
    }
  }

  public void update() {
    if (climbing) {
      if (yInit - 100 < hitBox.y) {
        hitBox.y -= 0.5;
      }
    } else if (dropping) {
      if (hitBox.y < yInit) {
        hitBox.y += 0.5;
      }
    }
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
