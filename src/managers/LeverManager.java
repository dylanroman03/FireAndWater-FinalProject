package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_LEVER;
import static utilities.Helpers.getAnimationsX;
import static utilities.Helpers.getQunatity;

import java.awt.image.BufferedImage;

import entities.Entity;
import entities.Lever;
import utilities.Constants.Heroes;

public class LeverManager extends Manager {
  private PlatformManager platformManager;
  private BufferedImage[] leverImages = new BufferedImage[2];

  public LeverManager(LevelManager levelManager, PlatformManager platforManager) {
    super();
    this.lvlData = levelManager.getLvlData();
    this.platformManager = platforManager;
    leverImages = getAnimationsX(PATH_LEVER);
    addLever();
  }

  private void addLever() {
    int length = getQunatity(lvlData, 700, 800);
    int e = 0;

    Lever[] leverArray = new Lever[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] > 700 && lvlData[i][j] < 800) {
          String numStr = Integer.toString(lvlData[i][j]);
          int digit = Character.getNumericValue(numStr.charAt(1));
          int isOn = Character.getNumericValue(numStr.charAt(2));

          leverArray[e] = new Lever((TILES_SIZE * j), (TILES_SIZE * i), TILES_SIZE, TILES_SIZE, leverImages, digit,
              isOn);
          e++;
        }
      }
    }

    entities[0] = leverArray;
  }

  
  /** 
   * @param hero
   * @param player
   * @return boolean
   */
  @Override
  public boolean someIntersect(Heroes hero, Entity player) {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        Lever lever = (Lever) entity;
        if (lever.intersect(player, platformManager)) {
          return true;
        }
      }
    }

    return false;
  }

}
