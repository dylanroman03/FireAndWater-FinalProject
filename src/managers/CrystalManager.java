package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_BLUE_CRYSTAL;
import static utilities.Constants.PATH_PURPLE_CRYSTAL;
import static utilities.Helpers.getImage;

import java.awt.image.BufferedImage;

import entities.Crystal;
import utilities.Constants.Heroes;

public class CrystalManager extends Manager {
	private BufferedImage blueCoinImg;
	private BufferedImage purpleCoinImg;

	public CrystalManager(LevelManager levelManager) {
		super(2);
		int[][] lvlData = levelManager.getLvlData();

		blueCoinImg = getImage(PATH_BLUE_CRYSTAL);
		purpleCoinImg = getImage(PATH_PURPLE_CRYSTAL);

		entities[0] = addCrystals(lvlData, 6, purpleCoinImg);
		entities[1] = addCrystals(lvlData, 5, blueCoinImg);
	}

	private Crystal[] addCrystals(int[][] lvlData, int type, BufferedImage image) {
		int length = 0;
		int e = 0;

		for (int i = 0; i < lvlData.length; i++) {
			for (int j = 0; j < lvlData[i].length; j++) {
				if (lvlData[i][j] == type)
					length++;
			}
		}

		Crystal[] crystals = new Crystal[length];

		for (int i = 0; i < lvlData.length; i++) {
			for (int j = 0; j < lvlData[0].length; j++) {
				if (lvlData[i][j] == type) {
					crystals[e] = new Crystal((TILES_SIZE * j), (TILES_SIZE * i), type, image);
					e++;
				}
			}
		}

		return crystals;
	}

  public Crystal[] getCrystals(Heroes hero) {
		switch (hero) {
			case PINK_MONSTER:
				return (Crystal[]) this.entities[0];
			case DUDE_MONSTER:
				return (Crystal[]) this.entities[1];
		}

		return null;
  }
}
