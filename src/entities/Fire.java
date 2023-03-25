package entities;

import static main.Game.TILES_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Fire extends Entity {
  private BufferedImage[] animations;
  private int aniIndex = 0;
	private int aniTick = 0;
	private int aniSpeed = 15;

  public Fire(float x, float y, int type, BufferedImage[] animations) {
    super(x, y, TILES_SIZE, TILES_SIZE);
    this.animations = animations;
    initHitBox(x, y, TILES_SIZE, TILES_SIZE);
  }

  public void render(Graphics g) {
    g.drawImage(animations[aniIndex], (int) (hitBox.x), (int) (hitBox.y), width, height, null);

    if (Game.DEBUGING) {
      showHitBox(g);
    }
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
