package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_BOX;
import static utilities.Helpers.getImage;
import static utilities.Helpers.getQunatity;

import java.awt.image.BufferedImage;

import entities.Box;
import entities.Entity;

public class BoxManager extends Manager {
  private BufferedImage[] sprites = new BufferedImage[1];
  private FloorManager floorManager;

  /**
   * Constructor de la clase {@link BoxManager}
   * @param levelManager
   * @param floorManager
   */
  public BoxManager(LevelManager levelManager, FloorManager floorManager) {
    lvlData = levelManager.getLvlData();
    this.floorManager = floorManager;
    sprites[0] = getImage(PATH_BOX);
    addEntity();
  }

  /**
   * Agrega las cajas al arreglo de entidades
   */
  private void addEntity() {
    int length = getQunatity(lvlData, 7);
    int e = 0;

    Box[] platformArray = new Box[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] == 7) {
          platformArray[e] = new Box((TILES_SIZE * j), (TILES_SIZE * i), sprites, floorManager);
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

  /**
   * Chequea si las cajas estan en el piso
   */
  private void checkIfBoxIsOnFloor() {
    for (Entity[] entities : entities) {
      for (Entity entity : entities) {
        Box box = (Box) entity;
        box.isOnFLoor(floorManager);
      }
    }
  }

}
