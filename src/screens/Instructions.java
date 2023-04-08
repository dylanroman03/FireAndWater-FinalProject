package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_DUDE_MONSTER;
import static utilities.Constants.PATH_KEYS;
import static utilities.Constants.PATH_PINK_MONSTER;
import static utilities.Helpers.getImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import gui.Button;
import main.Game;
import main.GamePanel;
import utilities.Constants.States;

public class Instructions {
  private BufferedImage[] characters = new BufferedImage[2];
  private BufferedImage[][] keys = new BufferedImage[2][4];
  private JLabel body;
  private boolean flag = true;
  private Game game;

  public Instructions(Game game) {
    this.game = game;

    characters[0] = getImage(PATH_PINK_MONSTER);
    characters[1] = getImage(PATH_DUDE_MONSTER);
    getKeys();
    initComponents();
  }

  private void getKeys() {
    BufferedImage image = getImage(PATH_KEYS);
    int[] keysYDude = { 4, 4, 2, 2 };
    int[] keysXDude = { 6, 2, 0, 3 };

    for (int i = 0; i < keys[0].length; i++) {
      keys[1][i] = image.getSubimage(i * 16, 0, 16, 16);
    }

    for (int i = 0; i < keys[0].length; i++) {
      keys[0][i] = image.getSubimage(keysXDude[i] * 16, keysYDude[i] * 16, 16, 16);
    }
  }

  private void initComponents() {
    body = new JLabel(
        "<html>En este juego, te encontraras en una cueva llena de peligros y desafios. Tu objetivo es llegar a la meta correspondiente, pero ten cuidado de no tocar las llamas que no sean de tu color. Si tocas una llama del color equivocado, perderas una vida.<br> Para ayudarte en tu camino, hay gemas de colores que puedes recolectar. Toma todas las gemas de tu color que puedas para aumentar tu puntaje. Pero cuidado, las gemas del otro color no puden ser recolectadas. Hay plataformas que debes activar con palancas y botones. Ten cuidado, las plataformas activadas con botones volveran a su estado original despues de un tiempo, asi que debes ser rapido para llegar a la plataforma.<br>Ademas, hay columpios que se inclinan, asi que ten cuidado</html>");
  }

  public void render(Graphics2D g, GamePanel gamePanel) {
    for (int i = 0; i < characters.length; i++) {
      g.drawImage(characters[i], (TILES_SIZE * 5) + (TILES_SIZE * i * 15),
          TILES_SIZE * 2, TILES_SIZE * 2, TILES_SIZE * 2, null);
    }

    for (int i = 0; i < keys.length; i++) {
      for (int j = 0; j < keys[0].length; j++) {
        g.drawImage(keys[i][j],(TILES_SIZE * 15 * i) + (TILES_SIZE * 2) + (TILES_SIZE * 2 * j), TILES_SIZE * 5,
            TILES_SIZE * 2, TILES_SIZE * 2, null);
      }
    }

    if (flag) {
      body.setForeground(Color.BLACK);
      body.setFont(new Font("MinimalPixel", Font.PLAIN, 40));
      body.setForeground(Color.WHITE);

      Button button = new Button((GAME_WIDTH / 2) - (TILES_SIZE * 3), GAME_HEIGHT - (TILES_SIZE * 3), "Menu");

      button.addActionListener(e -> {
        game.setState(States.MENU);
        flag = true;
      });

      gamePanel.add(body);
      gamePanel.add(button);

      body.setBounds(TILES_SIZE * 1, TILES_SIZE * 5, GAME_WIDTH - (TILES_SIZE * 2), GAME_HEIGHT - (TILES_SIZE * 5));
      button.setBounds();

      gamePanel.revalidate();
      flag = false;
    }

    // g.setColor(Color.BLACK);
  }

}
