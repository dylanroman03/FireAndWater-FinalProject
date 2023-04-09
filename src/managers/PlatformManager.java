package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_LEVEL_BUILD;
import static utilities.Helpers.getImage;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.Entity;
import entities.Platform;
import entities.Player;
import screens.Playing;

public class PlatformManager extends Manager {
  private int[][] lvlData;
  private BufferedImage platformImage;
  private Playing playing;

  public PlatformManager(LevelManager levelManager, Playing playing) {
    super();
    this.lvlData = levelManager.getLvlData();
    this.playing = playing;
    BufferedImage buildImg = getImage(PATH_LEVEL_BUILD);
    platformImage = buildImg.getSubimage(753, 20, 175, 12);
    addEntity();
  }

  @Override
  public void update() {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        Platform platform = (Platform) entity;
        platform.update(playing.getPlayer());
      }
    }
  }

  private void addEntity() {
    int length = 0;
    int e = 0;

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] > 1000 && lvlData[i][j] < 3000)
          length++;
      }
    }

    Platform[] platformArray = new Platform[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] > 1000 && lvlData[i][j] < 3000) {
          String numStr = Integer.toString(lvlData[i][j]);
          int direction = Character.getNumericValue(numStr.charAt(0));
          int id = Character.getNumericValue(numStr.charAt(1));
          int status = Character.getNumericValue(numStr.charAt(2));
          int height = Character.getNumericValue(numStr.charAt(3));

          platformArray[e] = new Platform((TILES_SIZE * j), (TILES_SIZE * i), platformImage, id, status, direction,
              height);
          e++;
        }
      }
    }

    entities[0] = platformArray;
  }

  public boolean overSome(Player player) {
    boolean some = false;
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        Rectangle2D.Float hitBox = entity.getHitBox();

        if (player.getX() + player.getWidth() > hitBox.x && player.getX() < hitBox.x + entity.getWidth()
            && player.getY() + player.getHeight() + 5 > hitBox.y && player.getY() < hitBox.y + entity.getHeight()) {
          some = true;
        }
      }
    }

    return some;
  }

  public void movePlatform(int idPlatform) {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        Platform platform = (Platform) entity;

        if (platform.id == idPlatform) {
          System.out.println("move platform: " + idPlatform + " platform: " + platform);
          platform.move();
        }
      }
    }
  }

  public Entity[] getPlatforms() {
    return entities[0];
  }

}
