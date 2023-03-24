package managers;

import static utilities.Constants.PATH_FILE_LEVELS;
import static utilities.Constants.PATH_FLOOR_LEVELS;
import static utilities.Helpers.getImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entities.Door;
import entities.Entity;
import main.Game;
import utilities.Constants.Heroes;

public class LevelManager {
  private BufferedImage levelFloor;
  private int[][] lvlData;
  private Door[] doors = new Door[2];

  public LevelManager() {
    levelFloor = getImage(PATH_FLOOR_LEVELS);
    lvlData = getLevelData();
    initDoors();
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
    // for (int i = 0; i < lvlData.length; i++) {
    //   for (int j = 0; j < lvlData[0].length; j++) {
    //     if (lvlData[i][j] == 8) {
    //     }
    //   }
    // }
    for (Door door : doors) {
      door.render(g);
    }
  }

  public void intersectDoor(Heroes hero, Entity entity) {
    Door door = doors[hero.ordinal()];
    if (door.intersect(entity)) {
      door.setAniTick(1);
    }
  }

  private void initDoors() {
    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] == 8) {
          doors[Heroes.PINK_MONSTER.ordinal()] = new Door((Game.TILES_SIZE * j), (Game.TILES_SIZE * i), Game.TILES_SIZE, Game.TILES_SIZE, Heroes.PINK_MONSTER);
        } else if(lvlData[i][j] == 9) {
          doors[Heroes.DUDE_MONSTER.ordinal()] = new Door((Game.TILES_SIZE * j), (Game.TILES_SIZE * i), Game.TILES_SIZE, Game.TILES_SIZE, Heroes.DUDE_MONSTER);
        }
      }
    }
  }

  public int[][] getLvlData() {
    return lvlData;
  }
}
