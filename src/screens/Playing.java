package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.TILES_SIZE;
import static utilities.Helpers.resetPanel;

import java.awt.Graphics;

import entities.Player;
import main.Game;
import main.GamePanel;
import managers.CrystalManager;
import managers.FireManager;
import managers.FloorManager;
import managers.LevelManager;
import managers.LeverManager;
import managers.PlatformManager;
import managers.SwitchManager;
import utilities.Constants.States;

public class Playing {
  private Player player;
  private LevelManager levelManager;
  private FireManager fireManager;
  private CrystalManager crystalManager;
  private LeverManager leverManager;
  private SwitchManager switchManager;
  private PlatformManager platformManager;
  private FloorManager floorManager;

  private Game game;
  private GamePanel gamePanel;

  public Playing(Game game) {
    this.game = game;
    levelManager = new LevelManager();

    initClasses();
  }

  public Playing(Game game, int level) {
    this.game = game;
    levelManager = new LevelManager(level);

    initClasses();
  }

  private void initClasses() {
    fireManager = new FireManager(levelManager);
    crystalManager = new CrystalManager(levelManager);

    floorManager = new FloorManager(levelManager);
    platformManager = new PlatformManager(levelManager, this);

    leverManager = new LeverManager(levelManager, platformManager);
    switchManager = new SwitchManager(levelManager, platformManager);

    player = new Player(0, (GAME_HEIGHT - (int) (TILES_SIZE * 2.05)), (TILES_SIZE), (TILES_SIZE), this);
  }

  public void render(Graphics g, GamePanel gamePanel) {
    this.gamePanel = gamePanel;

    levelManager.render(g);

    fireManager.render(g);
    crystalManager.render(g);
    leverManager.render(g);

    platformManager.render(g);
    switchManager.render(g);

    player.render(g);
    floorManager.render(g);
  }

  public void update() {
    fireManager.update();

    switchManager.update();
    platformManager.update();

    player.update();
  }

  public void windowsFocusLost() {
    player.resetDirection();
  }

  public void nextLevel() {
    game.setState(States.GAME_WON);
    resetPanel(gamePanel);
  }

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
}
