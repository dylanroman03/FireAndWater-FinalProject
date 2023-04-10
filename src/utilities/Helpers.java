package utilities;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;

import main.GamePanel;
import screens.Playing;

public class Helpers {
  private Helpers() {
  }

  
  /** 
   * @param path
   * @return BufferedImage
   */
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

  
  /** 
   * @param path
   * @return BufferedImage[]
   */
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

  
  /** 
   * @param path
   * @return BufferedImage[]
   */
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

  
  /** 
   * @param image
   * @param width
   * @param height
   * @return Image
   */
  public static Image resizeImage(BufferedImage image, int width, int height) {
    return new ImageIcon(image).getImage().getScaledInstance(width, height, 4);
  }

  
  /** 
   * @param playing
   * @param x
   * @param y
   * @return boolean
   */
  public static boolean canMove(Playing playing, float x, float y) {
    float height = playing.getPlayer().getHitBox().height;
    float width = playing.getPlayer().getHitBox().width;

    if (x < 0 || x >= GAME_WIDTH || y < 0 || y >= GAME_HEIGHT) {
      return false;
    } else if (!isSolid(x, y, playing) && (!isSolid(x + width, y + height, playing))
        && (!isSolid(x + width, y, playing)) && (!isSolid(x, y + height, playing))) {
      return true;
    }

    return false;
  }

  
  /** 
   * @param x
   * @param y
   * @param playing
   * @return boolean
   */
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
    if (playing.getPlatformManager().someIntersect(playing.getPlayer().getHero(), playing.getPlayer())) {
      return true;
    }

    if (playing.getBoxManager().someIntersect(playing.getPlayer().getHero(), playing.getPlayer())) {
      return true;
    }

    if (playing.getSwingManager().someIntersect(playing.getPlayer().getHero(), playing.getPlayer())) {
      return true;
    }

    return false;
  }

  
  /** 
   * @return Font
   */
  public static Font getFont() {
    return new Font("MinimalPixel", Font.PLAIN, 60);
  }

  
  /** 
   * @param gamePanel
   */
  public static void resetPanel(GamePanel gamePanel) {
    gamePanel.requestFocus();
    gamePanel.removeAll();
    gamePanel.revalidate();
  }

  
  /** 
   * @param filename
   * @param clip
   * @return Clip
   */
  public static Clip playMusic(String filename, Clip clip) {
    try {
      File file = new File(filename);
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(file));
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (Exception e) {
      System.out.println("Error al reproducir la música: " + e.getMessage());
    }

    return clip;
  }

  
  /** 
   * @param clip
   */
  public static void stopMusic(Clip clip) {
    if (clip != null && clip.isRunning()) {
      clip.stop();
    }
  }

  
  /** 
   * @param path
   */
  public static void playSound(String path) {
    try {
      File file = new File(path);
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(file));
      clip.addLineListener(new LineListener() {
        public void update(LineEvent evt) {
          if (evt.getType() == LineEvent.Type.STOP) {
            clip.close();
          }
        }
      });
      
      clip.start();
    } catch (Exception e) {
      System.out.println("Error al reproducir el sonido: " + e.getMessage());
    }
  }

  
  /** 
   * @param lvlData
   * @param value
   * @return int
   */
  public static int getQunatity(int[][] lvlData, int value) {
    int length = 0;
    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] == value)
          length++;
      }
    }

    return length;
  }

  
  /** 
   * @param lvlData
   * @param start
   * @param end
   * @return int
   */
  public static int getQunatity(int[][] lvlData, int start, int end) {
    int length = 0;
    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] >= start && lvlData[i][j] <= end)
          length++;
      }
    }

    return length;
  }
}
