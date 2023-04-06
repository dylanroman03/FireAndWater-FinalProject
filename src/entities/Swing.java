package entities;

import static main.Game.TILES_SIZE;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Swing extends Entity {
  private AffineTransform transform = new AffineTransform();

  public Swing(float x, float y, BufferedImage image) {
    super(x, y, TILES_SIZE * 4, TILES_SIZE / 2);

    this.sprites = new BufferedImage[1];
    this.sprites[0] = image;
    initHitBox(x, y, width, height);
  }

  @Override
  public boolean intersect(Entity entity) {
    if (collidesWith(entity)) {
      // Calcular el ángulo de inclinación a partir de la posición del objeto
      double centerX = x + width / 2.0;
      double centerY = y + height / 2.0;

      double deltaX = entity.getX() - centerX;
      double deltaY = entity.getY() - centerY;

      double newAngle;

      // Determinar donde intersecto el objeto
      if (centerX > entity.getX() && centerY > entity.getY()) {
        System.out.println("Arriba Derecha");
        newAngle = Math.atan2(deltaY, -deltaX);
      } else if (centerX < entity.getX() && centerY > entity.getY()) {
        System.out.println("Arriba Izquierda");
        newAngle = Math.atan2(-deltaY, deltaX);
      } else if (centerX > entity.getX() && centerY < entity.getY()) {
        System.out.println("Abajo Derecha");
        newAngle = Math.atan2(-deltaY, -deltaX);
      } else if (centerX < entity.getX() && centerY < entity.getY()) {
        System.out.println("Abajo Izquierda");
        newAngle = Math.atan2(deltaY, deltaX);
      } else {
        newAngle = Math.atan2(-deltaY, deltaX);
      }

      // Aplicar un factor de amortiguación para suavizar el cambio de ángulo
      double dampingFactor = 0.1;
      angle = angle + dampingFactor * (newAngle - angle);

      return true;
    }

    return false;
  }

  public boolean collidesWith(Entity entity) {
    // Calcular la posición y tamaño de la plataforma con la transformación de
    // rotación
    Shape platformShape = transform.createTransformedShape(new Rectangle2D.Double(x, y, width, height));

    // Calcular la posición y tamaño del Player
    Player player = (Player) entity;
    Rectangle2D.Float entityHB;

    entityHB = new Rectangle2D.Float(player.getX() + player.xSpeed, player.getY() + player.getAirSpeed(),
        player.width, player.height);

    // Verificar si el objeto se superpone con la plataforma
    return platformShape.intersects(entityHB);
  }

  @Override
  public void render(Graphics2D g) {
    if (angle >= 180 || angle <= -180) {
      angle = 0;
    } else if (angle > 0 || angle < 0) {
      angle += angle * 0.05;
    }

    super.render(g);
  }
}
