package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_LEVEL_BUILD;
import static utilities.Helpers.getImage;

import java.awt.image.BufferedImage;

import entities.Entity;
import entities.Platform;
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
        if (lvlData[i][j] > 1000  && lvlData[i][j] < 3000) {
          String numStr = Integer.toString(lvlData[i][j]);
          int direction = Character.getNumericValue(numStr.charAt(0));
          int id = Character.getNumericValue(numStr.charAt(1));
          int status = Character.getNumericValue(numStr.charAt(2));
          int height = Character.getNumericValue(numStr.charAt(3));

          platformArray[e] = new Platform((TILES_SIZE * j), (TILES_SIZE * i), platformImage, id, status, direction, height);
          e++;
        }
      }
    }

    entities[0] = platformArray;
  }

  public void movePlatform(int idPlatform) {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        Platform platform = (Platform) entity;

        if (platform.id == idPlatform) {
          platform.move();
        }
      }
    }
  }

  public Entity[] getPlatforms() {
    return entities[0];
  }

}
