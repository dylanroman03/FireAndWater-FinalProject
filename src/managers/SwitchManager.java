package managers;
import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_SWITCH;
import static utilities.Helpers.getAnimationsY;
import static utilities.Helpers.getQunatity;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import entities.Entity;
import entities.Switch;
import utilities.Constants.Heroes;

public class SwitchManager extends Manager {
  private PlatformManager platformManager;
  private BufferedImage[] animations;

  public SwitchManager(LevelManager levelManager, PlatformManager platforManager) {
    super();
    this.lvlData = levelManager.getLvlData();
    this.platformManager = platforManager;
    animations = getAnimationsY(PATH_SWITCH);
    addLever();
  }

  private void addLever() {
    int length = getQunatity(lvlData, 80, 90);
    int e = 0;

    Switch[] leverArray = new Switch[length];

    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        if (lvlData[i][j] > 80 && lvlData[i][j] < 90) {
          String numStr = Integer.toString(lvlData[i][j]);
          int digit = Character.getNumericValue(numStr.charAt(1));

          leverArray[e] = new Switch((TILES_SIZE * j), (TILES_SIZE * i), digit, animations);
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
        Switch switchEntity = (Switch) entity;
        if (switchEntity.intersect(player)) {
          Timer timer = new Timer();
          timer.schedule(new TimerTask() {
            @Override
            public void run() {
              platformManager.movePlatform(switchEntity.getIdPlatform());
            }
          }, 4 * 1000);

          timer.schedule(new TimerTask() {
            @Override
            public void run() {
              switchEntity.turnOff();
              platformManager.movePlatform(switchEntity.getIdPlatform());
            }
          }, 8 * 1000);

          return true;
        }
      }
    }

    return false;
  }

}
