package entities;

import static main.Game.DEBUGING;
import static main.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Switch extends Entity {
  private int idPlatform;
  private BufferedImage[] animations;
  private boolean isOn = false;
  private int aniIndex = 1;
  private int aniTick = 0;
	private int aniSpeed = 15;

  public Switch(float x, float y, int width, int height, int idPlatform, BufferedImage[] animations) {
    super(x, y, width, height);
    this.idPlatform = idPlatform;
    this.animations = animations;
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    g.drawImage(animations[aniIndex], (int) hitBox.x, (int) hitBox.y, TILES_SIZE, TILES_SIZE, null);

    if (DEBUGING) {
      showHitBox(g);
    }
  }

  @Override
  public void update() {
    aniTick++;
    if (aniTick >= aniSpeed) {
      aniTick = 0;
      if (isOn && aniIndex < animations.length - 1) {
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
      }
      return true;
    }

    return false;
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
