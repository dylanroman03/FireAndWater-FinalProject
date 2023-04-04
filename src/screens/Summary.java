package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;

import java.awt.Graphics;

import gui.Button;
import gui.Dialog;
import main.Game;
import main.GamePanel;
import utilities.Constants.States;

public class Summary extends Dialog {
  private Button nextButton;
  private boolean flag = true;
  
  public Summary(Game game) {
    super(game);
    initButtons();
  }

  private void initButtons() {
    nextButton = new Button((TILES_SIZE * 7), (GAME_HEIGHT / 2), "Continuar");
    nextButton.addActionListener(e -> {
      nextLevel();
    });
  }

  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);
    if (flag) {
      gamePanel.add(nextButton);

      nextButton.setBounds(GAME_WIDTH / 2, GAME_HEIGHT / 4, TILES_SIZE * 6, 100);
      gamePanel.revalidate();
      flag = false;
    }
  }

  private void nextLevel() {
    Playing playing = game.getPlaying();
    int getLevels = playing.getLevelManager().getLevels().length;
    int currentlyLvl = game.getPlaying().getLevelManager().getCurrentlyLevel();

    if (currentlyLvl + 1 == getLevels) {
      System.out.println("Se Acabo el juego");
    } else {
      playing.getPlayer();

      Playing newPlaying = new Playing(game, currentlyLvl + 1);
      newPlaying.setPlayer(playing.getPlayer());

      game.setPlaying(newPlaying);
      game.setState(States.PLAYING);
    }
  }

}
