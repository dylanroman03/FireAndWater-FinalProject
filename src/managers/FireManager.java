package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_BLUE_FIRE;
import static utilities.Constants.PATH_GREEN_FIRE;
import static utilities.Constants.PATH_PURPLE_FIRE;
import static utilities.Helpers.getAnimationsX;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.Fire;
import utilities.Constants.FireTypes;
import utilities.Constants.Heroes;

public class FireManager {
	private BufferedImage[] blueAnimations;
	private BufferedImage[] greenAnimations;
	private BufferedImage[] purpleAnimations;
	Fire[][] fires = new Fire[3][];

	public FireManager(LevelManager levelManager) {
		int[][] lvlData = levelManager.getLvlData();

		blueAnimations = getAnimationsX(PATH_BLUE_FIRE);
		purpleAnimations = getAnimationsX(PATH_PURPLE_FIRE);
		greenAnimations = getAnimationsX(PATH_GREEN_FIRE);

		fires[0] = addFires(lvlData, FireTypes.BLUE.ordinal() + 2, blueAnimations);
		fires[1] = addFires(lvlData, FireTypes.PURPLE.ordinal() + 2, purpleAnimations);
		fires[2] = addFires(lvlData, FireTypes.GREEN.ordinal() + 2, greenAnimations);

	}

	public void render(Graphics g) {
		for (Fire[] fireArray : fires) {
			for (Fire fire : fireArray) {
				fire.render(g);
			}
		}
	}

	public void update() {
		for (Fire[] fireArray : fires) {
			for (Fire fire : fireArray) {
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

	public boolean intersectFire(Heroes hero, Rectangle2D hBox) {
		Fire[] fires;

		if (hero == Heroes.PINK_MONSTER) {
			fires = this.fires[0];
		} else {
			fires = this.fires[1];
		}

		for (Fire fire : fires) {
			if (fire.getHitBox().intersects(hBox)) {
				return true;
			}
		}

		return false;
	}

}
