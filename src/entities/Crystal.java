package entities;

import static main.Game.TILES_SIZE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Crystal extends Entity {
  // private BufferedImage[] animations;
  private boolean visible = true;

  public Crystal(float x, float y, int type, BufferedImage animations) {
    super(x, y, TILES_SIZE, TILES_SIZE);
    this.sprites = new BufferedImage[1];
    this.sprites[0] = animations;
    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
  }

  @Override
  public void render(Graphics2D g) {
    if (visible) {
      super.render(g);
    }
  }

  @Override
  public boolean intersect(Entity entity) {
    if (super.intersect(entity)) {
      setVisible(false);
      return true; 
    }
    return false;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }
}
