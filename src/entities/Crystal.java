package entities;

import static main.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Crystal extends Entity {
  private BufferedImage animations;
  private int type;
  private boolean visible = true;

  public Crystal(float x, float y, int type, BufferedImage animations) {
    super(x, y, TILES_SIZE, TILES_SIZE);
    this.type = type;
    this.animations = animations;
    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
  }

  public void render(Graphics g) {
    if (visible) {
      g.drawImage(animations, (int) (hitBox.x), (int) (hitBox.y), width, height, null);
    }

    if (Game.DEBUGING) {
      showHitBox(g);
    }
  }

  public void update() {
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
