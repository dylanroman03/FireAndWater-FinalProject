package entities;

import static main.Game.TILES_SIZE;

import java.awt.image.BufferedImage;

public class Fire extends Entity {
  /**
   * Constructor de la clase {@link Fire}
   * @param x
   * @param y
   * @param type
   * @param animations
   */
  public Fire(float x, float y, int type, BufferedImage[] animations) {
    super(x, y + 5, TILES_SIZE, (int) (TILES_SIZE - 5));
    this.sprites = animations;
    initHitBox(x, y + 5, TILES_SIZE, (int) (TILES_SIZE - 5));
  }
}
