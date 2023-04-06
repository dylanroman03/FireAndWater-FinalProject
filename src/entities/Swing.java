package entities;

import static main.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Swing extends Entity {
  private AffineTransform transform = new AffineTransform();
  // public int id = 0;

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

      double newAngle = Math.atan2(-deltaY, deltaX);

      // Aplicar un factor de amortiguación para suavizar el cambio de ángulo
      double dampingFactor = 0.5; // ajusta este valor según sea necesario
      angle = angle + dampingFactor * (newAngle - angle);

      // Encontrar el punto de colisión del objeto con la plataforma
      double objectCenterX = entity.x + entity.width / 2;
      double objectCenterY = entity.y + entity.height / 2;
      double platformCenterX = x + width / 2;
      double platformCenterY = y + height / 2;
      double distanceBetweenCenters = Math
          .sqrt(Math.pow(objectCenterX - platformCenterX, 2) + Math.pow(objectCenterY - platformCenterY, 2));
      double collisionAngle = Math.atan2(objectCenterY - platformCenterY, objectCenterX - platformCenterX);
      double collisionDistance = distanceBetweenCenters
          - (height / 2 + Math.abs(entity.height / 2 * Math.sin(collisionAngle - angle)));
      double collisionX = platformCenterX + collisionDistance * Math.cos(collisionAngle + angle);
      double collisionY = platformCenterY - collisionDistance * Math.sin(collisionAngle + angle);

      // Calcular la nueva posición del objeto
      double objectNewX = collisionX - entity.width / 2;
      double objectNewY = collisionY - entity.height / 2;

      // Actualizar la posición del objeto
      entity.x = (int) objectNewX;
      entity.y = (int) objectNewY;

      System.out.println("Angle: " + angle);
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
    if (angle >= 180) {
      angle = 0;
    } if (angle > 0) {
      angle += angle * 0.05; 
    }

    // Guardar la transformación original
    AffineTransform originalTransform = g.getTransform();

    // Actualizar la transformación de rotación
    transform.setToIdentity();
    transform.rotate(Math.toRadians(angle), hitBox.getCenterX(), hitBox.getCenterY());

    // Aplicar la transformación al objeto Graphics2D
    g.transform(transform);

    // Dibujar la plataforma
    g.setColor(Color.BLUE);
    g.fillRect((int) x, (int) y, (int) width, (int) height);

    // Restaurar la transformación original
    g.setTransform(originalTransform);
  }

  // @Override
  // public void render(Graphics2D g) {
  //   // Guardar la transformación original
  //   AffineTransform originalTransform = g.getTransform();

  //   // Calcular el centro de la plataforma
  //   // double centerX = x + width / 2.0;
  //   // double centerY = y + height / 2.0;

  //   // Actualizar la transformación de rotación
  //   transform.setToIdentity();
  //   transform.rotate(angle, hitBox.getCenterX(), hitBox.getCenterY());

  //   // Aplicar la transformación al objeto Graphics2D
  //   g.transform(transform);

  //   // Dibujar la plataforma
  //   g.setColor(Color.BLUE);
  //   g.fillRect((int) x, (int) y, (int) width, (int) height);

  //   // Restaurar la transformación original
  //   g.setTransform(originalTransform);
  // }

}
