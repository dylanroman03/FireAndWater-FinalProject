package entities;

import java.awt.image.BufferedImage;

import main.Game;

public class Switch extends Entity {
  private int idPlatform;
  private boolean isOn = false;
  private int aniTick = 0;
	private int aniSpeed = 15;

  public Switch(float x, float y, int width, int height, int idPlatform, BufferedImage[] animations) {
    super(x, y, width, height);
    this.idPlatform = idPlatform;
    this.sprites = animations;
    aniIndex = 1;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  @Override
  public void update() {
    aniTick++;
    if (aniTick >= aniSpeed) {
      aniTick = 0;
      if (isOn && aniIndex < sprites.length - 1) {
        aniIndex++;
      } else if (!isOn && aniIndex > 0) {
        aniIndex--;
      }
    }
  }

  @Override
  public boolean intersect(Entity entity) {
    if (super.intersect(entity)) {
      if (!isOn) {
        turnOn(); 
        return true;
      }
    }

    return false;
  }

  public void changeState() {
    if (isOn) {
      turnOff();
    } else {
      turnOn();
    }
  }

  public void turnOn() {
    this.isOn = true;
  }

  public void turnOff() {
    this.isOn = false;
  }

  public int getIdPlatform() {
    return idPlatform;
  }

  public boolean isOn() {
    return isOn;
  }

}
