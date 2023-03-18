package gui;

import static main.Game.GAME_HEIGHT;
import static main.Game.TILES_SIZE;

import java.awt.Graphics;

import entities.Player;
import main.Game;
import managers.FireManager;
import managers.FloorManager;
import managers.LevelManager;

public class Playing {
  private Player player;
  private LevelManager levelManager;
  private FireManager fireManager;

  private FloorManager floorManager;
  private Game game;

  public Playing(Game game) {
    this.game = game;
    initClasses();
  }

  private void initClasses() {
    levelManager = new LevelManager();
    fireManager = new FireManager(levelManager);
    floorManager = new FloorManager(levelManager);
    player = new Player(0, (GAME_HEIGHT - (int) (TILES_SIZE * 2.05)), (TILES_SIZE), (TILES_SIZE), this);
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

  public void windowsFocusLost() {
    player.resetDirection();
  }

  public LevelManager getLevelManager() {
    return levelManager;
  }

  public FireManager getFireManager() {
    return fireManager;
  }

  public Player getPlayer() {
    return player;
  }
}
