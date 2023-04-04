package screens;

import static utilities.Helpers.resetPanel;

import java.awt.Graphics;

import gui.Dialog;
import main.Game;
import main.GamePanel;
import utilities.Constants.States;

public class GameWon extends Dialog {
  private GamePanel gamePanel;

  public GameWon(Game game) {
    super(game);
    this.game = game;
    // dialog = getImage(PATH_DIALOG);
  }

  // public void render(Graphics g, GamePanel gamePanel) {
  // this.gamePanel = gamePanel;
  // g.drawImage(dialog, (GAME_WIDTH - (TILES_SIZE * 8)) / 2, (GAME_HEIGHT -
  // (TILES_SIZE * 8)) / 2, TILES_SIZE * 8,
  // TILES_SIZE * 8, null);
  // }

  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);
    this.gamePanel = gamePanel;
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
