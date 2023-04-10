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
  private double angle = 0;

  public Swing(float x, float y, BufferedImage image) {
    super(x, y, TILES_SIZE * 4, TILES_SIZE / 2);

    this.sprites = new BufferedImage[1];
    this.sprites[0] = image;
    initHitBox(x, y, width, height);
  }

  
  /** 
   * @param entity
   * @return boolean
   */
  @Override
  public boolean intersect(Entity entity) {
    Direction dir;
    Player player = (Player) entity;

    double centerX = x + width / 2.0;
    double centerY = y + height / 2.0;

    double deltaX = entity.getX() - centerX;
    double deltaY = entity.getY() - centerY;

    double newAngle;

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
          player.setX(player.hitBox.x - 0.5f) ;
          player.setY(player.hitBox.y - 0.5f);
          break;
        case LeftDown:
          player.setX(player.hitBox.x - 0.5f);
          player.setY(player.hitBox.y + 0.5f);
          break;
        case Right:
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
      double dampingFactor = 0.1;
      angle = angle + dampingFactor * (newAngle - angle);
      return true;
    }

    return false;
  }

  
  /** 
   * @param entity
   * @return boolean
   */
  private boolean collidesWith(Entity entity) {
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
  }

  
  /** 
   * @param g
   */
  @Override
  public void render(Graphics2D g) {
    if (angle >= 180 || angle <= -180) {
      angle = 0;
    } else if (angle > 0 || angle < 0) {
      angle += angle * 0.08;
    }

    AffineTransform oldTransform = g.getTransform();

    AffineTransform transform = new AffineTransform();
    transform.rotate(Math.toRadians(angle), hitBox.getCenterX(), hitBox.getCenterY());

    AffineTransform oldTransform2 = g.getTransform();

    g.transform(transform);

    int x = (int) hitBox.getX();
    int y = (int) hitBox.getY();

    g.drawImage(sprites[aniIndex], x, y, (int) hitBox.getWidth(), (int) hitBox.getHeight(), null);

    if (Game.DEBUGING) {
      showHitBox(g, hitBox);
    }

    g.setTransform(oldTransform2);
    g.setTransform(oldTransform);
  }
}
