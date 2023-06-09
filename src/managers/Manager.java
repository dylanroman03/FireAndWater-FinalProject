package managers;

import java.awt.Graphics2D;

import entities.Entity;
import utilities.Constants.Heroes;

public class Manager {
  protected Entity[][] entities;
  protected int[][] lvlData;

  
  /** 
   * @return Entity[][]
   */
  public Entity[][] getEntities() {
    return entities;
  }

  /**
   * Constructor de la clase {@link Manager}
   */
  public Manager() {
    entities = new Entity[1][];
  }

  /**
   * Constructor de la clase {@link Manager} especificando el tamaño del arreglo de entidades
   * @param length
   */
  public Manager(int length) {
    entities = new Entity[length][];
  }

  
  /** 
   * @param g
   */
  public void render(Graphics2D g) {
    for (Entity[] entityArray : entities) {
      for (Entity entity : entityArray) {
        entity.render(g);
      }
    }
  }

  /**
   * Actualiza las entidades llamando {@link Entity#update()}
   */
  public void update() {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        entity.update();
      }
    }
  }

  
  /** 
   * Chequea si alguna entidad colisiona con el Player
   * @param hero
   * @param heroEntity
   * @return boolean
   */
  public boolean someIntersect(Heroes hero, Entity heroEntity) {
    Entity[][] entityToCompare;

    if (entities.length > 2) {
      entityToCompare = new Entity[2][];
      entityToCompare[1] = this.entities[2];
    } else {
      entityToCompare = new Entity[1][];
    }

    if (entities.length > 1) {
      if (hero == Heroes.PINK_MONSTER) {
        entityToCompare[0] = this.entities[0];
      } else {
        entityToCompare[0] = this.entities[1];
      }
    } else {
      entityToCompare[0] = this.entities[0];
    }

    for (Entity[] entityArray : entityToCompare) {
      for (Entity entity : entityArray) {
        if (entity.intersect(heroEntity)) {
          return true;
        }
      }
    }

    return false;
  }

}