package entities;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DOOR;
import static utilities.Helpers.getAnimationsX;

import utilities.Constants.Heroes;

public class Door extends Entity {
  private int aniTick = 0;

  public Door(float x, float y, int width, int height, Heroes type) {
    super(x, y, TILES_SIZE, TILES_SIZE);

    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
    animations = getAnimationsX(PATH_DOOR);
  }

  public void setAniTick(int aniTick) {
    this.aniTick = aniTick;
  }

}
