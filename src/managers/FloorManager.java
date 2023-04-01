package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_FLOOR_LEVELS;
import static utilities.Constants.PATH_LEVEL_BUILD;
import static utilities.Helpers.getImage;

import java.awt.image.BufferedImage;

import entities.Floor;

public class FloorManager extends Manager {
  private BufferedImage sandImg;
  private BufferedImage floorImg;
  private BufferedImage cornerLOneImg;
  private BufferedImage cornerLTwoImg;
  private BufferedImage cornerROneImg;
  private BufferedImage cornerRTwoImg;
  private BufferedImage borderRImg;
  private BufferedImage backFloorImg;
  private int[][] lvlData;

  public FloorManager(LevelManager levelManager) {
    super(1);
    this.lvlData = levelManager.getLvlData();
    sandImg = getImage(PATH_FLOOR_LEVELS);
    cornerLOneImg = getImage(PATH_LEVEL_BUILD).getSubimage(16, 240, 30, 37);
    cornerLTwoImg = getImage(PATH_LEVEL_BUILD).getSubimage(46, 240, 15, 37);

    cornerROneImg = getImage(PATH_LEVEL_BUILD).getSubimage(624, 240, 30, 37);
    cornerRTwoImg = getImage(PATH_LEVEL_BUILD).getSubimage(654, 240, 15, 37);

    borderRImg = getImage(PATH_LEVEL_BUILD).getSubimage(257, 175, 31, 40);
    
    floorImg = getImage(PATH_LEVEL_BUILD).getSubimage(130, 240, 30, 30);
    backFloorImg = getImage(PATH_LEVEL_BUILD).getSubimage(38, 38, 25, 20);
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
          BufferedImage img;
          BufferedImage back = backFloorImg;

          if (i != 0  && lvlData[i - 1][j] == 1) { // checking if the tile is under a floor
            img = backFloorImg;
          } else if (j == 0 || lvlData[i][j - 1] != 1) { // checking if the tile is a corner
            img = cornerLOneImg;
          } else if (j == 1 || lvlData[i][j - 2] != 1) {
            img = cornerLTwoImg;
          } else if (j == lvlData[0].length - 1) {
            img = cornerRTwoImg;
          } else if (j == lvlData[0].length - 2) {
            img = cornerROneImg;
          } else if (lvlData[i][j + 1] != 1) {
            img = borderRImg;
          } else {
            img = floorImg;
          }

          floorArray[e] = new Floor((TILES_SIZE * j), (TILES_SIZE * i), img, back);
          e++;
        }
      }
    }

    entities[0] = floorArray;
  }
}
