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
	protected int aniSpeed = 15;
	private int aniTick = 0;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Initialize the hitbox of the entity
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	protected void initHitBox(float x, float y, float width, float height) {
		hitBox = new Rectangle2D.Float(x, y, width, height);
	}

	
	/** 
	 * Renderiza el cuadro del hitbox, solo es llamada si {@link Game#DEBUGING} es true
	 * @param g
	 * @param rotatedRectangle
	 */
	protected void showHitBox(Graphics2D g, Shape rotatedRectangle) {
		g.setColor(Color.RED);
		g.draw(hitBox);
	}

	
	/** 
	 * Chequea si el hitbox de la entidad intersecta con el hitbox {@link Player}, tomando en cuenta su aceleración
	 * @param entity
	 * @return boolean
	 */
	public boolean intersect(Entity entity) {
		Player player = (Player) entity;
		Rectangle2D.Float entityHB;

		entityHB = new Rectangle2D.Float(player.getX() + player.xSpeed, player.getY() + player.getAirSpeed(),
				player.width, player.height);

		return hitBox.intersects(entityHB);
	}

	
	/** 
	 * Render the entity
	 * @param g
	 */
	public void render(Graphics2D g) {
		g.drawImage(sprites[aniIndex], (int) hitBox.x, (int) hitBox.y, (int) hitBox.getWidth(), (int) hitBox.getHeight(),
				null);

		if (Game.DEBUGING) {
			showHitBox(g, hitBox);
		}
	}

	/**
	 * Update the {@link Entity#aniIndex} para cambiar la imagen del sprite
	 */
	public void update() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= sprites.length) {
				aniIndex = 0;
			}
		}
	}

	
	/** 
	 * @return Float
	 */
	public Rectangle2D.Float getHitBox() {
		return hitBox;
	}

	
	/** 
	 * @return float
	 */
	public float getY() {
		return hitBox.y;
	}

	
	/** 
	 * @return float
	 */
	public float getX() {
		return hitBox.x;
	}

	
	/** 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	
	/** 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}
}
