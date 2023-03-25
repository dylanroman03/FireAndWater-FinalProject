package main;

import java.awt.Graphics;

import screens.Characters;
import screens.GameWon;
import screens.Menu;
import screens.Playing;
import utilities.Constants.Heroes;
import utilities.Constants.States;

public class Game implements Runnable {
	private GamePanel gamePanel;
	private boolean isGaming = true;

	public static final int TILES_DEFAULT_SIZE = 15;
	public static final float SCALE = 3f;
	public static final int TILES_WIDTH = 25;
	public static final int TILES_HEIGTH = 11;
	public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public static final int GAME_HEIGHT = TILES_SIZE * TILES_HEIGTH;
	public static final boolean DEBUGING = true;

	private Playing playing;
	private Menu menu;
	private Characters characters;
	private GameWon gameWon;

	private States state = States.PLAYING;

	public Game() {
		playing = new Playing(this);
		menu = new Menu(this);
		characters = new Characters(this);
		gameWon = new GameWon(this);

		gamePanel = new GamePanel(this);
		new GameWindow(gamePanel);
		gamePanel.requestFocus();


		startGameLoop();
	}

	private void startGameLoop() {
		Thread gameThread;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void render(Graphics g) {
		switch (state) {
			case PLAYING:
				playing.render(g, gamePanel);
				break;
			case MENU:
				menu.render(g, gamePanel);
				break;
			case CREDITS:
				break;
			case CHARACTERS:
				characters.render(g, gamePanel);
				break;
			case GAME_WON:
			 	gameWon.render(g, gamePanel);
				break;
			default:
				break;
		}
	}

	public void update() {
		switch (state) {
			case PLAYING:
				playing.update();
				break;
			case MENU:
				menu.update();
				break;
			case CREDITS:
				characters.update();
				break;
			case CHARACTERS:
				break;
			default:
				break;
		}
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

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}


	public Playing getPlaying() {
		return playing;
	}

	public void setPlaying(Playing playing) {
		this.playing = playing;
	}

	public Characters getCharacters() {
		return characters;
	}

	public void setHero(Heroes selectedHero) {
		System.out.println("Hero: " + selectedHero);
		playing.getPlayer().setHero(selectedHero);
	}
}