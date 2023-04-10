package entities;

import static utilities.Constants.PATH_LEVER_PULL;
import static utilities.Helpers.playSound;

import java.awt.image.BufferedImage;

import main.Game;
import managers.PlatformManager;;

public class Lever extends Entity {
  private boolean isOn = false;
  private int idPlatform;

  public Lever(float x, float y, int width, int height, BufferedImage[] animations, int idPlatform, int isOn) {
    super(x, y, width, height);
    this.idPlatform = idPlatform;
    this.sprites = animations;
    this.isOn = isOn == 0 ? false : true;
    this.aniIndex = isOn == 0 ? 1 : 0;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  private void turnOn() {
    this.isOn = true;
    aniIndex = 0;
  }

  private void turnOff() {
    this.isOn = false;
    aniIndex = 1;
  }

  
  /** 
   * @param entity
   * @param platformManager
   * @return boolean
   */
  public boolean intersect(Entity entity, PlatformManager platformManager) {
    Player player = (Player) entity;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (hitBox.intersects(player.hitBox.x + player.xSpeed, player.hitBox.y, 1, 1)) {
          if (isOn) {
            turnOff();
            platformManager.movePlatform(idPlatform);
            playSound(PATH_LEVER_PULL);
          }
          return true;
        }
        break;
      case RUNNING_RIGHT:
        if (hitBox.intersects(player.hitBox.x + player.hitBox.width + player.xSpeed, player.hitBox.y, 1, hitBox.height)) {
          if (!isOn) {
            turnOn();
            platformManager.movePlatform(idPlatform);
            playSound(PATH_LEVER_PULL);
          }
          return true;
        }
        break;
      default:
        break;
    }

    return super.intersect(entity);
  }

}
