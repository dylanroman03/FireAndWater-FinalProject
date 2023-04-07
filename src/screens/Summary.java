package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Helpers.getFont;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import entities.Crystal;
import entities.Player;
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
  private JLabel timeLabel;
  private JLabel crystalLabel;
  private Player player;
  private int time;
  private int crystalsScore = 0;
  private int crystals;

  public Summary(Game game) {
    super(game);

    player = game.getPlaying().getPlayer();
    time = game.getPlaying().getTime();

    Crystal[] crystalsArray = game.getPlaying().getCrystalManager().getCrystals(player.getHero());

    crystals = crystalsArray.length;
    for (Crystal elemenCrystal : crystalsArray) {
      if (!elemenCrystal.isVisible()) {
        crystalsScore++;
      }
    }

    initButtons();
  }

  @Override
  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);
    if (flag) {
      title = new JLabel("<html>Felicitaciones <br>" + player.getName() + "</html>");
      title.setForeground(Color.WHITE);
      title.setFont(getFont());

      timeLabel = new JLabel("Tiempo: " + time + " segundos");
      timeLabel.setForeground(Color.WHITE);
      timeLabel.setFont(getFont());

      crystalLabel = new JLabel("Cristales: " + crystalsScore + " de " + crystals);
      crystalLabel.setForeground(Color.WHITE);
      crystalLabel.setFont(getFont());

      gamePanel.add(title);
      gamePanel.add(timeLabel);
      gamePanel.add(crystalLabel);
      gamePanel.add(nextButton);

      nextButton.setBounds();
      title.setBounds((GAME_WIDTH / 2) - TILES_SIZE * 3, (GAME_HEIGHT / 2) - TILES_SIZE * 5,
          TILES_SIZE * 15, TILES_SIZE * 3);
      timeLabel.setBounds((int) (this.x + (TILES_SIZE * 1.5)), (int) (this.y + (this.height / 2.5)),
          this.width - (TILES_SIZE * 3), TILES_SIZE);
      crystalLabel.setBounds((int) (this.x + (TILES_SIZE * 1.5)), (int) this.y + (this.height / 2),
          this.width - (TILES_SIZE * 3), TILES_SIZE);

      gamePanel.revalidate();
      flag = false;
    }

    if (endOfGame) {
      gamePanel.remove(title);
      gamePanel.remove(timeLabel);
      gamePanel.remove(crystalLabel);
      gamePanel.remove(nextButton);

      title = new JLabel("<html> Felicitaciones <br> Finalizaste el juego </html>");
      title.setForeground(Color.WHITE);
      title.setFont(getFont());

      gamePanel.add(title);
      gamePanel.add(menuButton);

      title.setBounds((GAME_WIDTH / 2) - TILES_SIZE * 4, (GAME_HEIGHT / 2) - TILES_SIZE * 5,
          TILES_SIZE * 15, TILES_SIZE * 3);
      menuButton.setBounds();

      gamePanel.revalidate();
      endOfGame = false;
    }
  }

  private void initButtons() {
    nextButton = new Button((int) ((int) this.x + (this.width / 4.2)), (int) (this.y + (this.height / 1.5)),
        "Continuar");
    nextButton.addActionListener(e -> {
      nextLevel();
    });
  }

  private void initCongratulation() {
    menuButton = new Button((int) ((int) this.x + (this.width / 4.2)), (int) (this.y + (this.height / 1.5)),
        "Menu");
    menuButton.addActionListener(e -> {
      game.setState(States.MENU);
      flag = true;
    });

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
