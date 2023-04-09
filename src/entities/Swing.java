package entities;

import static main.Game.TILES_SIZE;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;

enum Direction {Left, Right, LeftDown, RightDown};

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
    Direction dir;
    Player player = (Player) entity;

    // Calcular el ángulo de inclinación a partir de la posición del objeto
    double centerX = x + width / 2.0;
    double centerY = y + height / 2.0;

    double deltaX = entity.getX() - centerX;
    double deltaY = entity.getY() - centerY;

    double newAngle;

    // Determinar donde intersecto el objeto
    if (centerX > entity.getX() && centerY > entity.getY()) {
      newAngle = Math.atan2(deltaY, -deltaX);
      dir = Direction.Left;
    } else if (centerX < entity.getX() && centerY > entity.getY()) {
      dir = Direction.Right;
      newAngle = Math.atan2(-deltaY, deltaX);
    } else if (centerX > entity.getX() && centerY < entity.getY()) {
      dir = Direction.LeftDown;
      newAngle = Math.atan2(-deltaY, -deltaX);
    } else if (centerX < entity.getX() && centerY < entity.getY()) {
      dir = Direction.RightDown;
      newAngle = Math.atan2(deltaY, deltaX);
    } else {
      newAngle = Math.atan2(-deltaY, deltaX);
      dir = Direction.Right;
    }

    if (hitBox.intersects(entity.getHitBox())) {
      switch (dir) {
        case Left:
          System.out.println("LEFT");
          player.setX(player.hitBox.x - 0.5f) ;
          player.setY(player.hitBox.y - 0.5f);
          break;
        case LeftDown:
          player.setX(player.hitBox.x - 0.5f);
          player.setY(player.hitBox.y + 0.5f);
          break;
        case Right:
          System.out.println("RIGHT");
          player.setX(player.hitBox.x + 0.5f);
          player.setY(player.hitBox.y - 0.5f);
          break;
        case RightDown:
          player.setX(player.hitBox.x + 0.5f);
          player.setY(player.hitBox.y + 0.5f);
          break;
      }
    }

    if (collidesWith(entity)) {

      // Aplicar un factor de amortiguación para suavizar el cambio de ángulo
      double dampingFactor = 0.1;
      angle = angle + dampingFactor * (newAngle - angle);

      player.setAngle(newAngle);

      return true;
    }

    return false;
  }

  public boolean collidesWith(Entity entity) {
    Rectangle2D r1 = getHitBox();

    Player player = (Player) entity;
    Rectangle2D r2 = new Rectangle2D.Float(player.getX() + player.xSpeed, player.getY() + player.getAirSpeed(),
        player.width, player.height);

    Point2D[] pts1 = {
        new Point2D.Double(r1.getMinX(), r1.getMinY()),
        new Point2D.Double(r1.getMaxX(), r1.getMinY()),
        new Point2D.Double(r1.getMaxX(), r1.getMaxY()),
        new Point2D.Double(r1.getMinX(), r1.getMaxY())
    };

    Point2D[] pts2 = {
        new Point2D.Double(r2.getMinX(), r2.getMinY()),
        new Point2D.Double(r2.getMaxX(), r2.getMinY()),
        new Point2D.Double(r2.getMaxX(), r2.getMaxY()),
        new Point2D.Double(r2.getMinX(), r2.getMaxY())
    };

    AffineTransform at1 = new AffineTransform();
    at1.rotate(Math.toRadians(angle), hitBox.getCenterX(), hitBox.getCenterY());

    AffineTransform at2 = new AffineTransform();

    at1.transform(pts1, 0, pts1, 0, pts1.length);
    at2.transform(pts2, 0, pts2, 0, pts2.length);

    Area area1 = new Area(new Polygon(
        new int[] { (int) pts1[0].getX(), (int) pts1[1].getX(), (int) pts1[2].getX(), (int) pts1[3].getX() },
        new int[] { (int) pts1[0].getY(), (int) pts1[1].getY(), (int) pts1[2].getY(), (int) pts1[3].getY() },
        4));
    Area area2 = new Area(new Polygon(
        new int[] { (int) pts2[0].getX(), (int) pts2[1].getX(), (int) pts2[2].getX(), (int) pts2[3].getX() },
        new int[] { (int) pts2[0].getY(), (int) pts2[1].getY(), (int) pts2[2].getY(), (int) pts2[3].getY() },
        4));

    area1.intersect(area2);
    return !area1.isEmpty();

    // // Calcular la posición y tamaño de la plataforma con la transformación de
    // // rotación
    // Shape platformShape = transform.createTransformedShape(new
    // Rectangle2D.Double(x, y, width, height));

    // // Calcular la posición y tamaño del Player
    // Player player = (Player) entity;
    // Rectangle2D.Float entityHB;

    // entityHB = new Rectangle2D.Float(player.getX() + player.xSpeed, player.getY()
    // + player.getAirSpeed(),
    // player.width, player.height);

    // // Verificar si el objeto se superpone con la plataforma
    // return platformShape.intersects(entityHB);
  }

  @Override
  public void render(Graphics2D g) {
    if (angle >= 180 || angle <= -180) {
      angle = 0;
    } else if (angle > 0 || angle < 0) {
      angle += angle * 0.08;
    }

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

    // Dibujar el rectángulo rotado en rojo
    if (Game.DEBUGING) {
      showHitBox(g, hitBox);
    }

    // Restaurar la transformación actual y la original
    g.setTransform(oldTransform2);
    g.setTransform(oldTransform);
  }
}
