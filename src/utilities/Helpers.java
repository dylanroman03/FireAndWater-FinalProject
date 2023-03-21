package utilities;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import entities.Player;

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

  public static boolean canMove(Player player, float x, float y, int[][] lvlData) {
    float height = player.getHitBox().height;
    float width = player.getHitBox().width;

    // switch (player.getPlayerAction()) {
    // case RUNNING_LEFT:
    // if (x <= 0) {
    // return false;
    // }
    // if (!isSolid(x, y, lvlData) && (!isSolid(x, y + height, lvlData))) {
    // return true;
    // }
    // break;
    // case RUNNING_RIGHT:
    // if (x + width >= GAME_WIDTH) {
    // return false;
    // }

    // if (!isSolid(x + width, y, lvlData) && (!isSolid(x + width, y + height,
    // lvlData))) {
    // return true;
    // }
    // break;
    // case JUMP:
    // if (y <= 0) {
    // return false;
    // }

    // if (!isSolid(x, y, lvlData) && (!isSolid(x + width, y, lvlData))) {
    // return true;
    // }
    // break;
    // case DOWN:
    // if (y + height >= GAME_HEIGTH) {
    // return false;
    // }

    // if (!isSolid(x, y + height, lvlData) && (!isSolid(x + width, y + height,
    // lvlData))) {
    // return true;
    // }
    // break;
    // default:
    // break;
    // }
    if (x < 0 || x >= GAME_WIDTH || y < 0 || y >= GAME_HEIGHT) {
      return false;
    } else if (!isSolid(x, y, lvlData) && (!isSolid(x + width, y + height, lvlData))
        && (!isSolid(x + width, y, lvlData)) && (!isSolid(x, y + height, lvlData))) {
      return true;
    }

    return false;
  }

  public static boolean isSolid(float x, float y, int[][] lvlData) {
    float xIndex = x / TILES_SIZE;
    float yIndex = y / TILES_SIZE;

    if (xIndex >= lvlData[0].length || yIndex >= lvlData.length) {
      return true;
    }

    int value = lvlData[(int) yIndex][(int) xIndex];

    if (x < 0 || x >= GAME_WIDTH || y < 0 || y >= GAME_HEIGHT || value == 1) {
      return true;
    }

    return false;
  }
}
