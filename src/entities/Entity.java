package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;

public abstract class Entity {

	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected Rectangle2D.Float hitBox;
	protected BufferedImage[] sprites;
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

	protected void showHitBox(Graphics2D g, Shape rotatedRectangle) {
		g.setColor(Color.RED);
		g.draw(hitBox);
	}

	// Intersect method for collision detection
	public boolean intersect(Entity entity) {
		Player player = (Player) entity;
		Rectangle2D.Float entityHB;

		entityHB = new Rectangle2D.Float(player.getX() + player.xSpeed, player.getY() + player.getAirSpeed(),
				player.width, player.height);

		return hitBox.intersects(entityHB);
	}

	public void render(Graphics2D g) {
		// Dibujar la imagen con las coordenadas y el tamaño del rectángulo original
		g.drawImage(sprites[aniIndex], (int) hitBox.x, (int) hitBox.y, (int) hitBox.getWidth(), (int) hitBox.getHeight(),
				null);

		// Dibujar el rectángulo rotado en rojo
		if (Game.DEBUGING) {
			showHitBox(g, hitBox);
		}
	}

	public void update() {
	}

	public Rectangle2D.Float getHitBox() {
		return hitBox;
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
