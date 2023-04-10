package entities;

import static main.Game.DEBUGING;
import static main.Game.TILES_SIZE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Fire extends Entity {
  /**
   * Constructor de la clase {@link Fire}
   * 
   * @param x
   * @param y
   * @param type
   * @param animations
   */
  public Fire(float x, float y, int type, BufferedImage[] animations) {
    super(x, y + 5, TILES_SIZE, TILES_SIZE);
    this.sprites = animations;
    initHitBox(x, y + (TILES_SIZE / 2), TILES_SIZE, (int) (TILES_SIZE / 2));
  }

  @Override
  public void render(Graphics2D g) {
    g.drawImage(sprites[aniIndex], (int) hitBox.x, (int) hitBox.y - (TILES_SIZE / 2),
        (int) hitBox.getWidth(), (int) height, null);

    if (DEBUGING) {
      showHitBox(g, hitBox);
    }
  }
}
