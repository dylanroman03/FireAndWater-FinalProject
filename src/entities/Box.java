package entities;

import static main.Game.TILES_SIZE;

import java.awt.image.BufferedImage;

import managers.FloorManager;

public class Box extends Entity {
  FloorManager floorManager;

  public Box(float x, float y, BufferedImage[] sprite, FloorManager floorManager) {
    super(x, y, TILES_SIZE, TILES_SIZE);
    this.sprites = sprite;
    this.floorManager = floorManager;
    initHitBox(x, y, width, height);
  }

  
  /** 
   * @param entity
   * @return boolean
   */
  @Override
  public boolean intersect(Entity entity) {
    // Cast entity to Player
    Player player = (Player) entity;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (hitBox.intersects(player.hitBox.getX() + player.xSpeed, player.hitBox.getY(), 1, 1)) {
          boolean isFloor = intersectFloor(1);

          if (!isFloor) {
            moveLeft();
          }
        }
        break;
      case RUNNING_RIGHT:
        if (hitBox.intersects(player.hitBox.getX() + player.hitBox.getWidth() + player.xSpeed, player.hitBox.getY(), 1,
            hitBox.getHeight())) {
          boolean isFloor = intersectFloor(-1);
          if (!isFloor) {
            moveRight();
          }
        }
        break;
      default:
        break;
    }

    return super.intersect(entity);
  }

  /** 
   * Mueve la caja a la derecha
   */
  private void moveRight() {
    hitBox.x += 1;
  }

  /** 
   * Mueve la caja a la izquierda
   */
  private void moveLeft() {
    hitBox.x -= 1;
  }

  
  /** 
   * Chequea si intersecta con el piso de manera horizontal
   * @param xSpeed
   * @return boolean
   */
  private boolean intersectFloor(int xSpeed) {
    for (Entity[] entities : floorManager.getEntities()) {
      for (Entity floor : entities) {
        if (hitBox.intersects(floor.getX() + xSpeed, floor.getY(), floor.width, floor.height)) {
          return true;
        }
      }
    }

    return false;
  }

  
  /** 
   * Chequea si intersecta con el piso de manera vertical
   * @param floorManager
   */
  public void isOnFLoor(FloorManager floorManager) {
    for (Entity[] entities : floorManager.getEntities()) {
      for (Entity floor : entities) {
        if (hitBox.intersects(floor.getX(), floor.getY() - 1, floor.width, 1)) {
          return;
        }
      }
    }

    hitBox.y += 1;
  }
}
