package main;

import static utilities.Constants.PATH_BACKGROUND_CERO;
import static utilities.Constants.PATH_TOWN_VILLAGE;
import static utilities.Helpers.getImage;
import static utilities.Helpers.playMusic;
import static utilities.Helpers.resetPanel;
import static utilities.Helpers.stopMusic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import entities.Player;
import screens.Characters;
import screens.Credits;
import screens.Instructions;
import screens.MainMenu;
import screens.Playing;
import utilities.Constants.Heroes;
import utilities.Constants.States;

public class Game implements Runnable {
	private GamePanel gamePanel;
	private boolean isGaming = true;
	private BufferedImage background;
	private Clip music;

	public static final int TILES_DEFAULT_SIZE = 10;
	public static final float SCALE = 3f;
	public static final int TILES_WIDTH = 29;
	public static final int TILES_HEIGTH = 25;
	public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public static final int GAME_HEIGHT = TILES_SIZE * TILES_HEIGTH;
	public static final boolean DEBUGING = false;

	private Playing playing;
	private MainMenu menu;
	private Characters characters;
	private Credits credits;
	private Instructions instructions;

	private States state = States.MENU;

	public Game() {
		initClasses();

		background = getImage(PATH_BACKGROUND_CERO);
		startGameLoop();

		music = playMusic(PATH_TOWN_VILLAGE, music);
	}

	private void startGameLoop() {
		Thread gameThread;
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	/** 
	 * @param g
	 */
	public void render(Graphics2D g) {
		if (state != States.PLAYING) {
			g.drawImage(background, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
		}

		switch (state) {
			case PLAYING:
				stopMusic(music);
				playing.render(g, gamePanel);
				break;
			case MENU:
				if (music != null && !music.isRunning()) {
					music = playMusic(PATH_TOWN_VILLAGE, music);
				}
				menu.render(g, gamePanel);
				break;
			case CREDITS:
				credits.render(g, gamePanel);
				break;
			case CHARACTERS:
				characters.render(g, gamePanel);
				break;
			case INSTRUCTIONS:
				instructions.render(g, gamePanel);
				break;
		}
	}

	public void update() {
		switch (state) {
			case PLAYING:
				playing.update();
				break;
			default:
				break;
		}
	}

	public void initClasses() {
		playing = new Playing(this);
		menu = new MainMenu(this);
		characters = new Characters(this);
		credits = new Credits(this);
		instructions = new Instructions(this);

		gamePanel = new GamePanel(this);
		new GameWindow(gamePanel);
		gamePanel.requestFocus();
	}

	@Override
	public void run() {
		final int FPS_SET = 120;
		final int UPS_SET = 200;

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (isGaming) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
			}
		}

	}

	public void windowsFocusLost() {
		switch (state) {
			case PLAYING:
				playing.windowsFocusLost();
				break;

			default:
				break;
		}
	}

	
	/** 
	 * @return States
	 */
	public States getState() {
		return state;
	}

	
	/** 
	 * @param state
	 */
	public void setState(States state) {
		resetPanel(gamePanel);

		if (state == States.PLAYING) {
			startPlaying();
		}

		this.state = state;
	}

	private void startPlaying() {
		Player player = playing.getPlayer();
		Heroes hero = player.getHero();
		String playerName = player.getName();

		playing.startPlaying(hero, playerName);
	}

	
	/** 
	 * @return Playing
	 */
	public Playing getPlaying() {
		return playing;
	}

	
	/** 
	 * @param playing
	 */
	public void setPlaying(Playing playing) {
		this.playing = playing;
	}

	
	/** 
	 * @return Characters
	 */
	public Characters getCharacters() {
		return characters;
	}

	
	/** 
	 * @param selectedHero
	 * @param name
	 */
	public void setPlayer(Heroes selectedHero, String name) {
		resetPanel(gamePanel);
		playing.startPlaying(selectedHero, name);
		this.state = States.PLAYING;
	}
}