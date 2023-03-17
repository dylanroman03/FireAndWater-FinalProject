package main;

import java.awt.Graphics;

import entities.Player;
import managers.FireManager;
import managers.FloorManager;
import managers.LevelManager;

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

	private Player player;
	private LevelManager levelManager;
	private FireManager fireManager;

	private FloorManager floorManager;

	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		levelManager = new LevelManager();
		fireManager = new FireManager(levelManager);
		floorManager = new FloorManager(levelManager);
		player = new Player(0, (GAME_HEIGHT - (int) (TILES_SIZE * 2.05)), (TILES_SIZE), (TILES_SIZE ), this);
	}

	private void startGameLoop() {
		Thread gameThread;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void render(Graphics g) {
		levelManager.render(g);
		fireManager.render(g);
		player.render(g);
		floorManager.render(g);
	}

	public void update() {
		fireManager.update();
		player.update();
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
		player.resetDirection();
	}

	public Player getPlayer() {
		return player;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public FireManager getFireManager() {
		return fireManager;
	}
}