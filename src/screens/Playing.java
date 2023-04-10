package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.GetTimePath;
import static utilities.Constants.PATH_BATTLE_MUSIC;
import static utilities.Helpers.getImage;
import static utilities.Helpers.playMusic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

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
import managers.SwingManager;
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
  private SwingManager swingManager;

  private Clip music;

  
  /** 
   * @return Clip
   */
  public Clip getMusic() {
    return music;
  }

  private Game game;

  private boolean isPlaying = false;
  private boolean isGameOver = false;
  private boolean isGameWon = false;

  private GameOver gameOver;
  private Summary summary;

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
    music = playMusic(PATH_BATTLE_MUSIC, music);

    fireManager = new FireManager(levelManager);
    crystalManager = new CrystalManager(levelManager);

    floorManager = new FloorManager(levelManager);
    platformManager = new PlatformManager(levelManager, this);
    boxManager = new BoxManager(levelManager, floorManager);
    swingManager = new SwingManager(levelManager);

    leverManager = new LeverManager(levelManager, platformManager);
    switchManager = new SwitchManager(levelManager, platformManager);

    player = new Player(0, (GAME_HEIGHT - (int) (TILES_SIZE * 2.05)), this);
  }

  
  /** 
   * @param hero
   * @param name
   */
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

  
  /** 
   * @param g
   * @param gamePanel
   */
  public void render(Graphics2D g, GamePanel gamePanel) {
    if (isPlaying) {
      levelManager.render(g);

      platformManager.render(g);

      floorManager.render(g);
      switchManager.render(g);
      boxManager.render(g);
      swingManager.render(g);

      fireManager.render(g);
      crystalManager.render(g);
      leverManager.render(g);

      player.render(g);
      renderTime(g, time);

      /// Draw a full rectangle with opacity in 0.5 to darken the screen
      if (isGameOver || isGameWon) {
        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        if (isGameOver) {
          gameOver.render(g, gamePanel);
        } else if (isGameWon) {
          summary.render(g, gamePanel);
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
      swingManager.update();
      player.update();
    }
  }

  
  /** 
   * @param g
   * @param time
   */
  private static void renderTime(Graphics g, int time) {
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
    summary = new Summary(game);
    isGameWon = true;
  }

  public void gameOver() {
    timer.cancel();
    gameOver = new GameOver(game);
    isGameOver = true;
  }

  
  /** 
   * @return LevelManager
   */
  public LevelManager getLevelManager() {
    return levelManager;
  }

  
  /** 
   * @return FireManager
   */
  public FireManager getFireManager() {
    return fireManager;
  }

  
  /** 
   * @return Player
   */
  public Player getPlayer() {
    return player;
  }

  
  /** 
   * @return CrystalManager
   */
  public CrystalManager getCrystalManager() {
    return crystalManager;
  }

  
  /** 
   * @return SwitchManager
   */
  public SwitchManager getSwitchManager() {
    return switchManager;
  }

  
  /** 
   * @return PlatformManager
   */
  public PlatformManager getPlatformManager() {
    return platformManager;
  }

  
  /** 
   * @return LeverManager
   */
  public LeverManager getLeverManager() {
    return leverManager;
  }

  
  /** 
   * @return FloorManager
   */
  public FloorManager getFloorManager() {
    return floorManager;
  }

  
  /** 
   * @return BoxManager
   */
  public BoxManager getBoxManager() {
    return boxManager;
  }

  
  /** 
   * @return SwingManager
   */
  public SwingManager getSwingManager() {
    return swingManager;
  }

  
  /** 
   * @return boolean
   */
  public boolean isPlaying() {
    return isPlaying;
  }

  
  /** 
   * @param levelManager
   */
  public void setLevelManager(LevelManager levelManager) {
    this.levelManager = levelManager;
  }

  
  /** 
   * @param player
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  
  /** 
   * @return int
   */
  public int getTime() {
    return time;
  }
}
