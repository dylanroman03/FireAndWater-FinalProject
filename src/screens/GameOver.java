package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.TILES_SIZE;
import static utilities.Helpers.getFont;

import java.awt.Color;
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
    retryButton = new Button((int) ((int) this.x + (this.width / 4.2)), (int) (this.y + (this.height / 2.5)),
        "Repetir");
    menuButton = new Button((int) ((int) this.x + (this.width / 4.2)), (int) (this.y + (this.height / 1.5)),
        "Menu");

    // add click funtion for menuButton
    menuButton.addActionListener(e -> {
      click(States.MENU);
    });

    // add click funtion for retryButton
    retryButton.addActionListener(e -> {
      click(States.PLAYING);
    });
  }

  private void click(States state) {
    game.setState(state);
    flag = true;
  }

  @Override
  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);
    if (flag) {
      JLabel title = new JLabel("Game Over");
      title.setForeground(Color.WHITE);
      title.setFont(getFont());

      gamePanel.add(title);
      gamePanel.add(retryButton);
      gamePanel.add(menuButton);

      title.setBounds((int) (this.x + (this.width / 2) - (TILES_SIZE * 2.5)), (GAME_HEIGHT / 2) - TILES_SIZE * 5,
          TILES_SIZE * 6, TILES_SIZE * 3);
      retryButton.setBounds();
      menuButton.setBounds();

      gamePanel.revalidate();
      flag = false;
    }
  }
}
