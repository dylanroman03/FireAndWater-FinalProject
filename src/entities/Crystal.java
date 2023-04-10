package entities;

import static main.Game.TILES_SIZE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Crystal extends Entity {
  private boolean visible = true;

  /**
   * Constructor de la clase {@link Crystal}
   * @param x
   * @param y
   * @param type
   * @param animations
   */
  public Crystal(float x, float y, int type, BufferedImage animations) {
    super(x, y, TILES_SIZE, TILES_SIZE);
    this.sprites = new BufferedImage[1];
    this.sprites[0] = animations;
    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
  }

  
  /** 
   * Verifica si {@link Crystal#visible} es true, si lo es, llama a {@link Entity#render(Graphics2D)}
   * @param g
   */
  @Override
  public void render(Graphics2D g) {
    if (visible) {
      super.render(g);
    }
  }

  
  /** 
   * @param entity
   * @return boolean
   */
  @Override
  public boolean intersect(Entity entity) {
    if (super.intersect(entity) && visible) {
      visible = false;
      return true; 
    }
    return false;
  }

  
  /** 
   * @return boolean
   */
  public boolean isVisible() {
    return visible;
  }

}
