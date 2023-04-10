package entities;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DOOR;
import static utilities.Constants.PATH_KEY_BLUE;
import static utilities.Constants.PATH_KEY_PINK;
import static utilities.Helpers.getAnimationsX;
import static utilities.Helpers.getImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utilities.Constants.Heroes;

public class Door extends Entity {
  private BufferedImage key;

  /**
   * Constructor de la clase {@link Door}
   * 
   * @param x
   * @param y
   * @param width
   * @param height
   * @param type
   */
  public Door(float x, float y, int width, int height, Heroes type) {
    super(x, y, TILES_SIZE, TILES_SIZE);

    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
    sprites = getAnimationsX(PATH_DOOR);

    switch (type) {
      case DUDE_MONSTER:
        key = getImage(PATH_KEY_BLUE);
        break;
      case PINK_MONSTER:
        key = getImage(PATH_KEY_PINK);
        break;
    }
  }

  /**
   * @param aniTick
   */
  public void setAniIndex(int aniTick) {
    aniIndex = aniTick;
  }

  @Override
  public void render(Graphics2D g) {
    super.render(g);
    g.drawImage(key, (int) hitBox.x, (int) hitBox.y - TILES_SIZE, TILES_SIZE, TILES_SIZE, null);
  }

}
