package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DIALOG;
import static utilities.Helpers.getImage;
import static utilities.Helpers.resetPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import main.GamePanel;
import utilities.Constants.States;

public class GameWon {
  private BufferedImage dialog;
  private GamePanel gamePanel;
  private Game game;

  public GameWon(Game game) {
    this.game = game;
    dialog = getImage(PATH_DIALOG);

    // Timer timer = new Timer();
    // timer.schedule(new TimerTask() {
    //   @Override
    //   public void run() {
    //     nextLevel();
    //   }
    // }, 2 * 1000);
  }

  public void render(Graphics g, GamePanel gamePanel) {
    this.gamePanel = gamePanel;
    g.drawImage(dialog, (GAME_WIDTH - (TILES_SIZE * 8)) / 2, (GAME_HEIGHT - (TILES_SIZE * 8)) / 2, TILES_SIZE * 8,
        TILES_SIZE * 8, null);
  }

  private void nextLevel() {
    Playing playing = game.getPlaying();
    int getLevels = playing.getLevelManager().getLevels().length;
    int currentlyLvl = game.getPlaying().getLevelManager().getCurrentlyLevel();

    System.out.println("Nivel Actual: " + currentlyLvl + " de " + getLevels);

    if (currentlyLvl + 1 == getLevels) {
      System.out.println("Se Acabo el juego");
    } else {
      Playing newPlaying = new Playing(game, currentlyLvl + 1);

      game.setPlaying(newPlaying);
      System.out.println("Siguiente Nivel: " + game.getPlaying().getLevelManager().getCurrentlyLevel());

      game.setState(States.PLAYING);
      resetPanel(gamePanel);
    }
  }

}
