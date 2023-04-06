package entities;

import static main.Game.TILES_SIZE;

import java.awt.image.BufferedImage;

import managers.FloorManager;

public class Box extends Entity {

  public Box(float x, float y, BufferedImage[] sprite) {
    super(x, y, TILES_SIZE, TILES_SIZE);
    this.sprites = sprite;
    initHitBox(x, y, width, height);
  }

  @Override
  public boolean intersect(Entity entity) {
    // Cast entity to Player
    Player player = (Player) entity;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (hitBox.intersects(player.hitBox.getX() + player.xSpeed, player.hitBox.getY(), 1, 1)) {
          moveLeft();
        }
        break;
      case RUNNING_RIGHT:
        if (hitBox.intersects(player.hitBox.getX() + player.hitBox.getWidth() + player.xSpeed, player.hitBox.getY(), 1,
            hitBox.getHeight())) {
          moveRight();
        }
        break;
      default:
        break;
    }

    return super.intersect(entity);
  }

  private void moveRight() {
    hitBox.x += 1;
  }

  private void moveLeft() {
    hitBox.x -= 1;
  }

  public void isOnFLoor(FloorManager floorManager) {
    boolean some = false;
    for (Entity[] entities : floorManager.getEntities()) {
      for (Entity floor : entities) {
        if (hitBox.intersects(floor.getX(), floor.getY() - 1, floor.width, 1)) {
          some = true;
        }
      }
    }

    if (!some) {
      hitBox.y += 1;
    }
  }
}
