package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_FLOOR_LEVELS;
import static utilities.Helpers.getImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Floor;

public class FloorManager {
  private BufferedImage floorImage;
  private Floor[] floor;
  private int[][] lvlData;

  public FloorManager(LevelManager levelManager) {
    this.lvlData = levelManager.getLvlData();
    floorImage = getImage(PATH_FLOOR_LEVELS);

    addFloor();
  }

  private void addFloor() {
    int length = 0;
    int e = 0;

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] == 1) length++;
      }
    }

    Floor[] floorArray = new Floor[length];

    for (int i = 0; i < lvlData.length; i++) {
     for (int j = 0; j < lvlData[0].length; j++) {
       if (lvlData[i][j] == 1) {
          floorArray[e] = new Floor((TILES_SIZE * j), (TILES_SIZE * i));
          e++;
       }
     } 
    }

    floor = floorArray;
	}

  public void render(Graphics g) {
    for (Floor floorItem : floor) {
      floorItem.render(floorImage, g);
    }
  }
}
