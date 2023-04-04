package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import gui.Button;
import gui.Dialog;
import main.Game;
import main.GamePanel;
import utilities.Constants.States;

public class Summary extends Dialog {
  private Button nextButton;
  private Button menuButton;
  private boolean flag = true;
  private boolean endOfGame = false;
  private JLabel title;

  public Summary(Game game) {
    super(game);
    initButtons();
  }

  @Override
  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);
    if (flag) {
      gamePanel.add(nextButton);
      nextButton.setBounds();

      gamePanel.revalidate();
      flag = false;
    }

    if (endOfGame) {
      gamePanel.remove(nextButton);

      gamePanel.add(title);
      gamePanel.add(menuButton);

      title.setBounds(GAME_WIDTH / 2, GAME_HEIGHT / 4, TILES_SIZE * 6, 100);
      menuButton.setBounds();

      gamePanel.revalidate();
      endOfGame = false;
    }
  }

  private void initButtons() {
    nextButton = new Button((TILES_SIZE * 7), (GAME_HEIGHT / 2), "Continuar");
    nextButton.addActionListener(e -> {
      nextLevel();
    });
  }

  private void initCongratulation() {
    menuButton = new Button((TILES_SIZE * 7), (GAME_HEIGHT / 2), "Menu");
    menuButton.addActionListener(e -> {
      game.setState(States.MENU);
      flag = true;
    });

    title = new JLabel("Felicitaciones Finalizaste el juego");
    title.setForeground(Color.WHITE);
    title.setFont(new Font("MinimalPixel", Font.PLAIN, 60));

    endOfGame = true;
  }

  private void nextLevel() {
    Playing playing = game.getPlaying();
    int getLevels = playing.getLevelManager().getLevels().length;
    int currentlyLvl = game.getPlaying().getLevelManager().getCurrentlyLevel();

    if (currentlyLvl + 1 == getLevels) {
      initCongratulation();
      endOfGame = true;
    } else {
      playing.getPlayer();

      Playing newPlaying = new Playing(game, currentlyLvl + 1);
      newPlaying.setPlayer(playing.getPlayer());

      game.setPlaying(newPlaying);
      game.setState(States.PLAYING);
    }
  }

}
