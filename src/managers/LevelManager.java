package managers;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utilities.Constants.PATH_BACKGROUND;
import static utilities.Constants.PATH_BACKGROUND_TWO;
import static utilities.Constants.PATH_FILE_LEVELS;
import static utilities.Helpers.getImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entities.Door;
import entities.Entity;
import main.Game;
import utilities.Constants.Heroes;

public class LevelManager {
  private int[][] lvlData;
  private Door[] doors = new Door[2];
  private String[] levels = {"01.txt", "02.txt"};
  private int currentlyLevel = 0;
  private BufferedImage background;
  private BufferedImage backgroundTwo;

  /**
   * Constructor de la clase {@link LevelManager}
   */
  public LevelManager() {
    background = getImage(PATH_BACKGROUND);
    backgroundTwo = getImage(PATH_BACKGROUND_TWO);
    lvlData = getLevelData();
    initDoors();
  }

  /**
   * Constructor de la clase {@link LevelManager} dando un nivel en especifico
   * @param level
   */
  public LevelManager(int level) {
    background = getImage(PATH_BACKGROUND);
    backgroundTwo = getImage(PATH_BACKGROUND_TWO);
    currentlyLevel = level;
    lvlData = getLevelData();
    initDoors();
  }

  
  /** 
   * @return int[][]
   */
  private int[][] getLevelData() {
    int[][] matrix = null;

    try {
      File myObj = new File(PATH_FILE_LEVELS + levels[currentlyLevel]);
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

  
  /** 
   * @param g
   */
  public void render(Graphics2D g) {
    g.drawImage(background, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    g.drawImage(backgroundTwo, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    
    for (Door door : doors) {
      door.render(g);
    }
  }

  
  /** 
   * @param hero
   * @param entity
   * @return boolean
   */
  public boolean intersectDoor(Heroes hero, Entity entity) {
    Door door = doors[hero.ordinal()];
    if (door.intersect(entity)) {
      return true;
    }
    return false;
  }

  /**
   * Inicializa las puertas
   */
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

  
  /** 
   * @return String[]
   */
  public String[] getLevels() {
    return levels;
  }

  
  /** 
   * @return int
   */
  public int getCurrentlyLevel() {
    return currentlyLevel;
  }

  
  /** 
   * @return int[][]
   */
  public int[][] getLvlData() {
    return lvlData;
  }
}
