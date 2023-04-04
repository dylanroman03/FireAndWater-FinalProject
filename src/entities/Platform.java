package entities;

import static main.Game.TILES_SIZE;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Platform extends Entity {
  private float yInit;
  public int id;
  private boolean climbing;
  private boolean dropping;

  public Platform(float x, float y, BufferedImage image, int id, int status) {
    super(x, y, TILES_SIZE * 2, (int) (TILES_SIZE / 3));
    sprites = new BufferedImage[1];
    sprites[0] = image;
    yInit = status == 0 ? y : y + 100;
    climbing = status == 0 ? false : true;
    initHitBox(x, y, width, height);

    this.id = id;
    System.out.println(id);
  }

  public void update(Player player) {
    if (climbing) {
      if (yInit - 100 < hitBox.y) {
        if (isPlayerOver(player)) {
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
        && player.getY() + player.getHeight() + 5 > hitBox.y && player.getY() < hitBox.y + height;
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

  @Override
  public boolean intersect(Entity entity) {
    // Cast entity to Player
    Player player = (Player) entity;
    Rectangle2D.Float entityHB;

    entityHB = new Rectangle2D.Float(player.getX() + player.xSpeed, player.getY() + player.getAirSpeed(),
        player.width, player.height);

    return hitBox.intersects(entityHB);
  }

}
