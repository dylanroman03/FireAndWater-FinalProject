package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_FLOOR_LEVELS;
import static utilities.Helpers.getImage;

import java.awt.image.BufferedImage;

import entities.Floor;

public class FloorManager extends Manager {
  private BufferedImage floorImage;
  private int[][] lvlData;

  public FloorManager(LevelManager levelManager) {
    super(1);
    this.lvlData = levelManager.getLvlData();
    floorImage = getImage(PATH_FLOOR_LEVELS);
    addFloor();
  }

  private void addFloor() {
    int length = 0;
    int e = 0;

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] == 1)
          length++;
      }
    }

    Floor[] floorArray = new Floor[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] == 1) {
          floorArray[e] = new Floor((TILES_SIZE * j), (TILES_SIZE * i), floorImage);
          e++;
        }
      }
    }

    entities[0] = floorArray;
  }
}
