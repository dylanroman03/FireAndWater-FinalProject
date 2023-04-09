package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_LEVEL_BUILD;
import static utilities.Helpers.getImage;

import java.awt.image.BufferedImage;

import entities.Swing;

public class SwingManager extends Manager {
  BufferedImage swingImage;

  public SwingManager(LevelManager levelManager) {
    super();

    int[][] lvlData = levelManager.getLvlData();

    BufferedImage buildImg = getImage(PATH_LEVEL_BUILD);
    swingImage = buildImg.getSubimage(753, 20, 175, 12);

    addEntities(lvlData);
  }

  private void addEntities(int[][] lvlData) {
		int length = 0;
		int e = 0;

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] == 300)
          length++;
      }
    }

    Swing[] swings = new Swing[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] == 300) {
          swings[e] = new Swing((TILES_SIZE * j), (TILES_SIZE * i), swingImage);
          e++;
        }
      }
    }

    this.entities[0] = swings;
  }

}
