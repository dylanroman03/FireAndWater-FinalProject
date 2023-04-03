package managers;

import java.awt.Graphics;

import entities.Entity;
import utilities.Constants.Heroes;

public class Manager {
  protected Entity[][] entities;

  public Entity[][] getEntities() {
    return entities;
  }

  public Manager() {
    entities = new Entity[1][];
  }

  public Manager(int length) {
    entities = new Entity[length][];
  }

  public void render(Graphics g) {
    for (Entity[] entityArray : entities) {
      for (Entity entity : entityArray) {
        entity.render(g);
      }
    }
  }

  public void update() {
    for (Entity[] entitiesArray : entities) {
      for (Entity entity : entitiesArray) {
        entity.update();
      }
    }
  }

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