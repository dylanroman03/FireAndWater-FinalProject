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

  public Platform(float x, float y, BufferedImage image, int id, int status) {
    super(x, y, TILES_SIZE * 2, (int) (TILES_SIZE / 3));
    this.image = image;
    yInit = status == 0 ? y : y + 100;
    climbing= status == 0 ? false : true;
    initHitBox(x, y, width, height);

    this.id = id;
    System.out.println(id);
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    g.drawImage(image, (int) hitBox.x, (int) hitBox.y, width, height, null);

    if (DEBUGING) {
      showHitBox(g);
    }
  }

  public void update(Player player) {
    if (climbing) {
      if (yInit - 100 < hitBox.y) {
        if(isPlayerOver(player)) {
          player.setY(player.getY() - 0.5f);
        }
        hitBox.y -= 0.5;
      }
    } else if (dropping) {
      if (hitBox.y < yInit) {
        if (isPlayerOver(player)) {
          player.setY(player.getY() + 0.5f);
        }
        hitBox.y += 0.5;
      }
    }
  }

  private boolean isPlayerOver(Player player) {
    return player.getX() + player.getWidth() > hitBox.x && player.getX() < hitBox.x + width
        && player.getY() + player.getHeight() + 5 > hitBox.y;
  }

  public void move() {
    if (climbing) {
      climbing = false;
      dropping = true;
    } else {
      climbing = true;
      dropping = false;
    }
  }

}
