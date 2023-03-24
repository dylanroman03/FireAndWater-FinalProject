package managers;

import static main.Game.TILES_SIZE;

import entities.Entity;
import entities.Lever;
import utilities.Constants.Heroes;

public class LeverManager extends Manager {
  private int[][] lvlData;
  private FloorManager floorManager;

  public LeverManager(LevelManager levelManager, FloorManager floorManager) {
    super(1);
    this.lvlData = levelManager.getLvlData();
    this.floorManager = floorManager;
    addLever();
  }

  private void addLever() {
    int length = 0;
    int e = 0;

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] > 70 && lvlData[i][j] < 80) length++;
      }
    }

    Lever[] leverArray = new Lever[length];

    for (int i = 0; i < lvlData.length; i++) {
     for (int j = 0; j < lvlData[0].length; j++) {
       if (lvlData[i][j] > 70 && lvlData[i][j] < 80) {
          String numStr = Integer.toString(lvlData[i][j]);
          char digit = numStr.charAt(1);

          leverArray[e] = new Lever((TILES_SIZE * j), (TILES_SIZE * i), TILES_SIZE, TILES_SIZE, digit);
          e++;
       }
     } 
    }

    entities[0] = leverArray;
	}

  @Override
  public boolean someIntersect(Heroes hero, Entity player) {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        Lever lever = (Lever) entity;
        lever.intersect(player, floorManager);
      }
    }

    return false;
  }

  
}
