package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
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
	protected double angle = 0;

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
		// Guardar la transformación original para restaurarla más tarde
		AffineTransform oldTransform = g.getTransform();

		// Crear una nueva transformación afín para rotar los gráficos
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angle), hitBox.getCenterX(), hitBox.getCenterY());

		// Guardar la transformación actual para restaurarla más tarde
		AffineTransform oldTransform2 = g.getTransform();

		// Aplicar la transformación a los gráficos para dibujar la imagen
		g.transform(transform);

		// Calcular las coordenadas x e y para dibujar la imagen
		int x = (int) hitBox.getX();
		int y = (int) hitBox.getY();

		// Dibujar la imagen con las coordenadas y el tamaño del rectángulo original
		g.drawImage(sprites[aniIndex], x, y, (int) hitBox.getWidth(), (int) hitBox.getHeight(), null);

		// g.drawImage(sprites[aniIndex], (int) hitBox.x, (int) hitBox.y, (int) width, (int) height, null);

		// Dibujar el rectángulo rotado en rojo
		if (Game.DEBUGING) {
			showHitBox(g, hitBox);
		}

		// Restaurar la transformación actual y la original
		g.setTransform(oldTransform2);
		g.setTransform(oldTransform);
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

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
