package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;

public abstract class Entity {

	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected Rectangle2D.Float hitBox;
	protected BufferedImage[] animations;
	protected int aniIndex = 0;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	protected void initHitBox(float x, float y, float width, float height) {
		hitBox = new Rectangle2D.Float(x, y, width, height);
	}

	protected void showHitBox(Graphics g) {
		g.setColor(Color.red); // Debuging
		g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
	}

	// Intersect method for collision detection
	public boolean intersect(Entity entity) {
		return hitBox.intersects(entity.getHitBox());
	}

	public Rectangle2D.Float getHitBox() {
		return hitBox;
	}

	public void render(Graphics g) {
    g.drawImage(animations[aniIndex], (int) (hitBox.x), (int) (hitBox.y), width, height, null);

    if (Game.DEBUGING) {
      showHitBox(g);
    }
	}

  public void update() {
  }

	public float getY() {
		return hitBox.y;
	}

	public float getX() {
		return hitBox.x;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
