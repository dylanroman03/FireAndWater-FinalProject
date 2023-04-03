package utilities;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import entities.Entity;
import main.GamePanel;
import screens.Playing;

public class Helpers {
  private Helpers() {
  }

  public static BufferedImage getImage(String path) {
    BufferedImage img = null;

    try {
      InputStream is = new FileInputStream(path);
      img = ImageIO.read(is);
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return img;
  }

  public static BufferedImage[] getAnimationsX(String path) {
    int length = Integer.parseInt(path.substring(path.length() - 5, path.length() - 4));
    BufferedImage[] animations = new BufferedImage[length];
    BufferedImage image = getImage(path);

    for (int i = 0; i < animations.length; i++) {
      animations[i] = image.getSubimage(i * (image.getWidth() / length), 0, image.getWidth() / length,
          image.getHeight());
    }

    return animations;
  }

  public static BufferedImage[] getAnimationsY(String path) {
    int length = Integer.parseInt(path.substring(path.length() - 5, path.length() - 4));
    BufferedImage[] animations = new BufferedImage[length];
    BufferedImage image = getImage(path);

    for (int i = 0; i < animations.length; i++) {
      animations[i] = image.getSubimage(0, i * (image.getHeight() / length), image.getWidth(),
          image.getHeight() / length);
    }

    return animations;
  }

  public static Image resizeImage(BufferedImage image, int width, int height) {
    return new ImageIcon(image).getImage().getScaledInstance(width, height, 4);
  }

  public static boolean canMove(Playing playing, float x, float y) {
    float height = playing.getPlayer().getHitBox().height;
    float width = playing.getPlayer().getHitBox().width;

    if (x < 0 || x >= GAME_WIDTH || y < 0 || y >= GAME_HEIGHT) {
      return false;
    } else if (!isSolid(x, y, playing) && (!isSolid(x + width, y + height, playing))
        && (!isSolid(x + width, y, playing)) && (!isSolid(x, y + height, playing))) {
      return true;
      // } else {
      // switch (playing.getPlayer().getPlayerAction()) {
      // case RUNNING_LEFT:
      // if (!isSolid(x, y, playing))
      // if (!isSolid(x, y + height, playing))
      // return true;
      // break;
      // case RUNNING_RIGHT:
      // if (!isSolid(x + width, y, playing))
      // if (!isSolid(x + width, y + height, playing))
      // return true;
      // break;
      // case JUMP:
      // if (!isSolid(x, y, playing))
      // if (!isSolid(x + width, y, playing))
      // return true;
      // break;
      // case DOWN:
      // if (!isSolid(x, y + height, playing))
      // if (!isSolid(x + width, y + height, playing))
      // return true;
      // break;
      // default:
      // if (!isSolid(x, y + height, playing))
      // if (!isSolid(x + width, y + height, playing))
      // return false;
      // }
    }

    return false;
  }

  public static boolean isSolid(float x, float y, Playing playing) {
    float xIndex = x / TILES_SIZE;
    float yIndex = y / TILES_SIZE;
    int[][] lvlData = playing.getLevelManager().getLvlData();

    if (xIndex >= lvlData[0].length || yIndex >= lvlData.length) {
      return true;
    }

    int value = lvlData[(int) yIndex][(int) xIndex];

    if (x < 0 || x >= GAME_WIDTH || y < 0 || y >= GAME_HEIGHT || value == 1 || value > 700 && value < 800) {
      return true;
    }

    for (Entity platform : playing.getPlatformManager().getPlatforms()) {
      if (platform.getHitBox().intersects(new Rectangle2D.Float(x, y, 1, 1))) {
        return true;
      }
    }

    if (playing.getBoxManager().someIntersect(playing.getPlayer().getHero(), playing.getPlayer())) {
      return true;  
    }

    return false;
  }

  public static void resetPanel(GamePanel gamePanel) {
    gamePanel.requestFocus();
    gamePanel.removeAll();
    gamePanel.revalidate();
  }
}
