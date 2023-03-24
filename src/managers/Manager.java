package managers;

import java.awt.Graphics;

import entities.Entity;
import utilities.Constants.Heroes;

public class Manager {
  protected Entity[][] entities;

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

  public boolean someIntersect(Heroes hero, Entity hBox) {
		Entity[] entityToCompare;

    if (entities.length > 1) {
      if (hero == Heroes.PINK_MONSTER) {
        entityToCompare = this.entities[0];
      } else {
        entityToCompare = this.entities[1];
      }
    } else {
      entityToCompare = this.entities[0];
    }


		for (Entity entity : entityToCompare) {
			if (entity.intersect(hBox)) {
				return true;
			}
		}

		return false;
	}

}