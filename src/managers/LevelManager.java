package managers;

import static utilities.Constants.PATH_FILE_LEVELS;
import static utilities.Constants.PATH_FLOOR_LEVELS;
import static utilities.Helpers.getImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelManager {
  private BufferedImage levelFloor;
  private int[][] lvlData;

  public LevelManager() {
    levelFloor = getImage(PATH_FLOOR_LEVELS);
    lvlData = getLevelData();
  }

  private int[][] getLevelData() {
    int[][] matrix = null;

    try {
      File myObj = new File(PATH_FILE_LEVELS + "01.txt");
      Scanner data = new Scanner(myObj);
      int x = data.nextInt();
      int y = data.nextInt();
      matrix = new int[x][y];

      for (int i = 0; i < x; i++) {
        data.nextLine();
        for (int j = 0; j < y; j++) {
          matrix[i][j] = data.nextInt();
        }
      }

      data.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return matrix;
  }

  public void render(Graphics g) {
    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        // if (lvlData[i][j] == 1) {
        //   g.drawImage(levelFloor, (Game.TILES_SIZE * j), (Game.TILES_SIZE * i), Game.TILES_SIZE, Game.TILES_SIZE, null);
        // }
      }
    }
  }

  public int[][] getLvlData() {
    return lvlData;
  }
}
