package entities;

import static main.Game.TILES_SIZE;

import java.awt.image.BufferedImage;

public class Fire extends Entity {
	private int aniTick = 0;
	private int aniSpeed = 15;

  public Fire(float x, float y, int type, BufferedImage[] animations) {
    super(x, y + 5, TILES_SIZE, (int) (TILES_SIZE - 5));
    this.sprites = animations;
    initHitBox(x, y + 5, TILES_SIZE, (int) (TILES_SIZE - 5));
  }

  public void update() {
		updateAnimationTick();
  }

  private void updateAnimationTick() {
    aniTick++;
    if (aniTick >= aniSpeed) {
      aniTick = 0;
      aniIndex++;
      if (aniIndex >= 8) {
        aniIndex = 0;
      }
    }
  }
}
