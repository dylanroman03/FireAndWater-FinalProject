package entities;

import static main.Game.TILES_SIZE;

import java.awt.image.BufferedImage;

public class Switch extends Entity {
  private int idPlatform;
  private boolean isOn = false;
  private int aniTick = 0;

  /**
   * Constructor de la clase {@link Switch}
   * @param x
   * @param y
   * @param idPlatform
   * @param animations
   */
  public Switch(float x, float y, int idPlatform, BufferedImage[] animations) {
    super(x, y + (TILES_SIZE / 2), TILES_SIZE, TILES_SIZE);
    this.idPlatform = idPlatform;
    this.sprites = animations;
    aniIndex = 1;
    initHitBox(x, y + (TILES_SIZE / 2), TILES_SIZE, TILES_SIZE / 2);
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

  
  /** 
   * @param entity
   * @return boolean
   */
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

  /**
   * Enciende el boton
   */
  public void turnOn() {
    this.isOn = true;
  }

  /**
   * Apaga el boton
   */
  public void turnOff() {
    this.isOn = false;
  }

  
  /** 
   * @return int
   */
  public int getIdPlatform() {
    return idPlatform;
  }

}
