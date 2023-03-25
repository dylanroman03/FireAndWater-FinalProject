package entities;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DOOR;
import static utilities.Helpers.getAnimationsX;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.Constants.Heroes;

public class Door extends Entity {
  private BufferedImage[] animations;
  private int aniTick = 0;

  public Door(float x, float y, int width, int height, Heroes type) {
    super(x, y, TILES_SIZE, TILES_SIZE);

    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
    animations = getAnimationsX(PATH_DOOR);
  }

  public void render(Graphics g) {
    g.drawImage(animations[aniTick], (int) (hitBox.x), (int) (hitBox.y), width, height, null);

    if (Game.DEBUGING) {
      showHitBox(g);
    }
  }

  public void setAniTick(int aniTick) {
    this.aniTick = aniTick;
  }

}
