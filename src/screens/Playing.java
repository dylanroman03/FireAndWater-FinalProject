package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.GetTimePath;
import static utilities.Helpers.getImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import entities.Player;
import main.Game;
import main.GamePanel;
import managers.BoxManager;
import managers.CrystalManager;
import managers.FireManager;
import managers.FloorManager;
import managers.LevelManager;
import managers.LeverManager;
import managers.PlatformManager;
import managers.SwitchManager;
import utilities.Constants.Heroes;

public class Playing {
  private Player player;
  private LevelManager levelManager;
  private FireManager fireManager;
  private CrystalManager crystalManager;
  private LeverManager leverManager;
  private SwitchManager switchManager;
  private PlatformManager platformManager;
  private FloorManager floorManager;
  private BoxManager boxManager;

  private Game game;
  private GamePanel gamePanel;

  private boolean isPlaying = false;
  private boolean isGameOver = false;
  private boolean isGameWon = false;
  private boolean isGamePaused = false;

  private GameOver gameOver;
  private Summary gameWon;

  private int time;
  private Timer timer = new Timer();

  public Playing(Game game) {
    this.game = game;
    levelManager = new LevelManager();
  }

  public Playing(Game game, int level) {
    this.game = game;
    levelManager = new LevelManager(level);
  }

  private void initClasses() {
    fireManager = new FireManager(levelManager);
    crystalManager = new CrystalManager(levelManager);

    floorManager = new FloorManager(levelManager);
    platformManager = new PlatformManager(levelManager, this);
    boxManager = new BoxManager(levelManager, floorManager);

    leverManager = new LeverManager(levelManager, platformManager);
    switchManager = new SwitchManager(levelManager, platformManager);

    player = new Player(0, (GAME_HEIGHT - (int) (TILES_SIZE * 2.05)), this);
  }

  public void startPlaying(Heroes hero, String name) {
    time = 0;
    timer = new Timer();

    /// Updating time every second
    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        time++;
      }
    };

    timer.schedule(task, 1000, 1000);
    initClasses();
    player.setHero(hero);
    player.setName(name);

    isPlaying = true;
    isGameOver = false;
    isGameWon = false;
  }

  public void render(Graphics g, GamePanel gamePanel) {
    this.gamePanel = gamePanel;

    if (isPlaying) {
      levelManager.render(g);

      platformManager.render(g);
      switchManager.render(g);
      boxManager.render(g);

      fireManager.render(g);
      crystalManager.render(g);
      leverManager.render(g);

      player.render(g);
      floorManager.render(g);
      renderTime(g, time);

      /// Draw a full rectangle with opacity in 0.5 to darken the screen
      if (isGameOver || isGameWon || isGamePaused) {
        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        if (isGameOver) {
          gameOver.render(g, gamePanel);
        } else if (isGameWon) {
          gameWon.render(g, gamePanel);
        } else if (isGamePaused) {
          // gamePaused.render(g);
        }
      }
    }

  }

  public void update() {
    if (isPlaying) {
      fireManager.update();
  
      switchManager.update();
      platformManager.update();
      boxManager.update();
  
      player.update();
    }
  }

  public static void renderTime(Graphics g, int time) {
    int x = Game.TILES_SIZE * 18;

    String number = String.valueOf(time);
    char[] digits1 = number.toCharArray();

    for (char c : digits1) {
      String path = GetTimePath(Character.getNumericValue(c));
      BufferedImage image = getImage(path);

      g.drawImage(image, x, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2, null);
      x += Game.TILES_SIZE;
    }
  }

  public void windowsFocusLost() {
    player.resetDirection();
  }

  public void gameWon() {
    timer.cancel();
    gameWon = new Summary(game);
    isGameWon = true;
  }

  public void gameOver() {
    timer.cancel();
    gameOver = new GameOver(game);
    isGameOver = true;
  }

  /// Getters and Setters

  public LevelManager getLevelManager() {
    return levelManager;
  }

  public void setLevelManager(LevelManager levelManager) {
    this.levelManager = levelManager;
  }

  public FireManager getFireManager() {
    return fireManager;
  }

  public Player getPlayer() {
    return player;
  }

  public CrystalManager getCrystalManager() {
    return crystalManager;
  }

  public SwitchManager getSwitchManager() {
    return switchManager;
  }

  public PlatformManager getPlatformManager() {
    return platformManager;
  }

  public LeverManager getLeverManager() {
    return leverManager;
  }

  public FloorManager getFloorManager() {
    return floorManager;
  }

  public BoxManager getBoxManager() {
    return boxManager;
  }

  public boolean isPlaying() {
    return isPlaying;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
