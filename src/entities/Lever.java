package entities;

import static main.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import managers.FloorManager;

public class Lever extends Entity {
  private boolean isOn = false;
  private int idPlatform;
  private BufferedImage[] leverImages;
  private int aniTick = 1;

  public Lever(float x, float y, int width, int height, int idPlatform, BufferedImage[] leverImages) {
    super(x, y, width, height);
    this.idPlatform = idPlatform;
    this.leverImages = leverImages;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  public void turnOn() {
    this.isOn = true;
    aniTick = 0;
  }

  public void turnOff() {
    this.isOn = false;
    aniTick = 1;
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    g.drawImage(leverImages[aniTick], (int) hitBox.x, (int) hitBox.y, TILES_SIZE, TILES_SIZE, null);
    // g.setColor(Color.BLACK);
    // g.fillRect((int) hitBox.x, (int) hitBox.y, 20, 60); // base of the lever
    // if (isOn) {
    //   g.setColor(Color.RED);
    //   g.fillOval((int) (hitBox.x) + 5, (int) (hitBox.y) + 5, 10, 10); // handle in on position
    // } else {
    //   g.setColor(Color.GRAY);
    //   g.fillOval((int) (hitBox.x) + 5, (int) (hitBox.y) + 45, 10, 10); // handle in off position
    // }
  }

  public boolean intersect(Entity entity, FloorManager floorManager) {
    Player player = (Player) entity;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (player.hitBox.intersects(hitBox.x + hitBox.width, hitBox.y, 1, hitBox.height)) {
          if (isOn) {
            turnOff();
            floorManager.movePlatform(idPlatform, false);
          }
          return true;
        }
        break;
      case RUNNING_RIGHT:
        if (player.hitBox.intersects(hitBox.x, hitBox.y, 1, hitBox.height)) {
          if (!isOn) {
            turnOn();
            floorManager.movePlatform(idPlatform, true);
          }
          return true;
        }
        break;
      case DOWN:
        return super.intersect(entity);
      default:
      break;
    }

    return false;
  }

}
