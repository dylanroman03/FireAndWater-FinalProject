package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_LEVEL_BUILD;
import static utilities.Helpers.getImage;

import java.awt.image.BufferedImage;

import entities.Entity;
import entities.Platform;

public class PlatformManager extends Manager {
  private int[][] lvlData;
  private BufferedImage platformImage;

  public PlatformManager(LevelManager levelManager) {
    super(1);
    this.lvlData = levelManager.getLvlData();
    BufferedImage buildImg = getImage(PATH_LEVEL_BUILD);
    platformImage = buildImg.getSubimage(753, 20, 175, 12);
    addEntity();
  }

  private void addEntity() {
    int length = 0;
    int e = 0;

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] > 10 && lvlData[i][j] < 20)
          length++;
      }
    }

    Platform[] platformArray = new Platform[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] > 10 && lvlData[i][j] < 20) {
          String numStr = Integer.toString(lvlData[i][j]);
          char digit = numStr.charAt(1);

          platformArray[e] = new Platform((TILES_SIZE * j), (TILES_SIZE * i), platformImage, digit);
          e++;
        }
      }
    }

    entities[0] = platformArray;
  }

  public void movePlatform(int idPlatform, boolean climbing) {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        Platform platform = (Platform) entity;

        if (platform.id == idPlatform) {
          platform.move(climbing);
        }
      }
    }
  }

  public Entity[] getPlatforms() {
    return entities[0];
  }

}
