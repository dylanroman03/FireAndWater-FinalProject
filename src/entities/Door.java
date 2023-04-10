package entities;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DOOR;
import static utilities.Helpers.getAnimationsX;

import utilities.Constants.Heroes;

public class Door extends Entity {
  public Door(float x, float y, int width, int height, Heroes type) {
    super(x, y, TILES_SIZE, TILES_SIZE);

    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
    sprites = getAnimationsX(PATH_DOOR);
  }

  
  /** 
   * @param aniTick
   */
  public void setAniIndex(int aniTick) {
    aniIndex = aniTick;
  }

}
