package entities;

import static main.Game.TILES_SIZE;

import java.awt.image.BufferedImage;

public class Platform extends Entity {
  private float yInit;
  private float xInit;
  private Direction dir = Direction.Horizontal;
  public int id;
  private boolean climbing;
  private boolean dropping;

  enum Direction {
    Vertical, Horizontal
  };

  public Platform(float x, float y, BufferedImage image, int id, int status, int direction, int size) {
    super(x, y,
        size > 0 && direction == 1 ?  TILES_SIZE / 2 : size > 0 ? TILES_SIZE * size : TILES_SIZE * 2,
        size > 0 && direction == 1 ? TILES_SIZE * size : (int) (TILES_SIZE / 3));

    sprites = new BufferedImage[1];
    sprites[0] = image;

    
    int xIntSum = width == TILES_SIZE * 2 ? 100 : (width / (TILES_SIZE * 2)) * 100;

    yInit = status == 0 ? y : y + 100;
    xInit = status == 0 ? x : x + xIntSum;

    dir = direction == 1 ? Direction.Vertical : Direction.Horizontal;
    climbing = status == 0 ? false : true;

    initHitBox(x, y, width, height);
    this.id = id;
  }

  public void update(Player player) {
    if (dir == Direction.Vertical) {
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
    } else {
      int size = width == TILES_SIZE * 2 ? 100 : (width / (TILES_SIZE * 2)) * 100;

      if (climbing) {
        if (xInit - size < hitBox.x) {
          if (isPlayerOver(player)) {
            player.setX(player.getX() - 0.5f);
          }
          hitBox.x -= 0.5;
        }
      } else if (dropping) {
        if (hitBox.x < xInit) {
          if (isPlayerOver(player)) {
            player.setX(player.getX() + 0.5f);
          }
          hitBox.x += 0.5;
        }
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
}
