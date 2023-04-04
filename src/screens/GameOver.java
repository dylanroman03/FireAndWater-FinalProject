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

public class GameOver extends Dialog {
  private Button retryButton;
  private Button menuButton;
  private boolean flag = true;

  public GameOver(Game game) {
    super(game);
    initButtons();
  }

  private void initButtons() {
    retryButton = new Button((TILES_SIZE * 7), (GAME_HEIGHT / 2) - TILES_SIZE * 4, "Repetir");
    menuButton = new Button((TILES_SIZE * 7), (GAME_HEIGHT / 2), "Menu");

    // add click funtion for menuButton
    menuButton.addActionListener(e -> {
      game.setState(States.MENU);
      flag = true;
    });

    // add click funtion for retryButton
    retryButton.addActionListener(e -> {
      game.setState(States.PLAYING);
      flag = true;
    });
  }

  @Override
  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);
    if (flag) {
      JLabel title = new JLabel("Game Over");
      title.setForeground(Color.WHITE);
      title.setFont(new Font("MinimalPixel", Font.PLAIN, 60));

      gamePanel.add(title);
      gamePanel.add(retryButton);
      gamePanel.add(menuButton);

      title.setBounds(GAME_WIDTH / 2, GAME_HEIGHT / 4, TILES_SIZE * 6, 100);
      retryButton.setBounds();
      menuButton.setBounds();

      gamePanel.revalidate();
      flag = false;
    }
  }
}
