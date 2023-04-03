package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_BOX;
import static utilities.Helpers.getImage;

import java.awt.image.BufferedImage;

import entities.Box;
import entities.Entity;

/// The Manager to handle the Box
public class BoxManager extends Manager {
  private int[][] lvlData;
  private BufferedImage[] sprites = new BufferedImage[1];
  private FloorManager floorManager;

  public BoxManager(LevelManager levelManager, FloorManager floorManager) {
    this.lvlData = levelManager.getLvlData();
    this.floorManager = floorManager;
    sprites[0] = getImage(PATH_BOX);
    addEntity();
  }

  private void addEntity() {
    int length = 0;
    int e = 0;

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[i].length; j++) {
        if (lvlData[i][j] == 7)
          length++;
      }
    }

    Box[] platformArray = new Box[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] == 7) {
          platformArray[e] = new Box((TILES_SIZE * j), (TILES_SIZE * i), sprites);
          e++;
        }
      }
    }

    entities[0] = platformArray;
  }

  @Override
  public void update() {
    super.update();
    checkIfBoxIsOnFloor();
  }

  private void checkIfBoxIsOnFloor() {
    for (Entity[] entities : entities) {
      for (Entity entity : entities) {
        Box box = (Box) entity;
        box.isOnFLoor(floorManager);
      }
    }
  }

}
