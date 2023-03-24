package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_BLUE_FIRE;
import static utilities.Constants.PATH_GREEN_FIRE;
import static utilities.Constants.PATH_PURPLE_FIRE;
import static utilities.Helpers.getAnimationsX;

import java.awt.image.BufferedImage;

import entities.Entity;
import entities.Fire;
import utilities.Constants.FireTypes;

public class FireManager extends Manager {
	private BufferedImage[] blueAnimations;
	private BufferedImage[] greenAnimations;
	private BufferedImage[] purpleAnimations;

	public FireManager(LevelManager levelManager) {
		super(3);
		int[][] lvlData = levelManager.getLvlData();

		blueAnimations = getAnimationsX(PATH_BLUE_FIRE);
		purpleAnimations = getAnimationsX(PATH_PURPLE_FIRE);
		greenAnimations = getAnimationsX(PATH_GREEN_FIRE);

		entities[0] = addFires(lvlData, FireTypes.BLUE.ordinal() + 2, blueAnimations);
		entities[1] = addFires(lvlData, FireTypes.PURPLE.ordinal() + 2, purpleAnimations);
		entities[2] = addFires(lvlData, FireTypes.GREEN.ordinal() + 2, greenAnimations);
	}

	public void update() {
		for (Entity[] fireArray : entities) {
			for (Entity entity : fireArray) {
				Fire fire = (Fire) entity;
				fire.update();
			}
		}
	}

	private Fire[] addFires(int[][] lvlData, int type, BufferedImage[] animations) {
		int length = 0;
		int e = 0;

		for (int i = 0; i < lvlData.length; i++) {
			for (int j = 0; j < lvlData[i].length; j++) {
				if (lvlData[i][j] == type)
					length++;
			}
		}

		Fire[] fires = new Fire[length];

		for (int i = 0; i < lvlData.length; i++) {
			for (int j = 0; j < lvlData[0].length; j++) {
				if (lvlData[i][j] == type) {
					fires[e] = new Fire((TILES_SIZE * j), (TILES_SIZE * i), type, animations);
					e++;
				}
			}
		}

		return fires;
	}
}
