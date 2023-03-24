package entities;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import managers.FloorManager;

public class Lever extends Entity {
  boolean isOn = false;
  int idPlatform;

  public Lever(float x, float y, int width, int height, int idPlatform) {
    super(x, y, width, height);
    this.idPlatform = idPlatform;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  public void turnOn() {
    this.isOn = true;
  }

  public void turnOff() {
    this.isOn = false;
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    g.setColor(Color.BLACK);
    g.fillRect((int) hitBox.x, (int) hitBox.y, 20, 60); // base of the lever
    if (isOn) {
      g.setColor(Color.RED);
      g.fillOval((int) (hitBox.x) + 5, (int) (hitBox.y) + 5, 10, 10); // handle in on position
    } else {
      g.setColor(Color.GRAY);
      g.fillOval((int) (hitBox.x) + 5, (int) (hitBox.y) + 45, 10, 10); // handle in off position
    }
  }

  public boolean intersect(Entity entity, FloorManager floorManager) {
    if(super.intersect(entity) && !isOn) {
      turnOn();
      floorManager.movePlatform(idPlatform);
      return true;
    } 
    return false;
  }

}
