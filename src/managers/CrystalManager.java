package managers;

import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_BLUE_CRYSTAL;
import static utilities.Constants.PATH_PURPLE_CRYSTAL;
import static utilities.Helpers.getImage;
import static utilities.Helpers.getQunatity;

import java.awt.image.BufferedImage;

import entities.Crystal;
import utilities.Constants.Heroes;

public class CrystalManager extends Manager {
	private BufferedImage blueCoinImg;
	private BufferedImage purpleCoinImg;

	/**
	 * Constructor de la clase {@link CrystalManager}
	 * @param levelManager
	 */
	public CrystalManager(LevelManager levelManager) {
		super(2);
		lvlData = levelManager.getLvlData();

		blueCoinImg = getImage(PATH_BLUE_CRYSTAL);
		purpleCoinImg = getImage(PATH_PURPLE_CRYSTAL);

		entities[0] = addCrystals(lvlData, 6, purpleCoinImg);
		entities[1] = addCrystals(lvlData, 5, blueCoinImg);
	}

	
	/** 
	 * Agrega los cristales al arreglo de entidades
	 * @param lvlData
	 * @param type
	 * @param image
	 * @return Crystal[]
	 */
	private Crystal[] addCrystals(int[][] lvlData, int type, BufferedImage image) {
		int length = getQunatity(lvlData, type);
		int e = 0;

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

  
	/** 
	 * @param hero
	 * @return Crystal[]
	 */
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
