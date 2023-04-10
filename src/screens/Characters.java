package screens;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static main.Game.TILES_SIZE;
import static utilities.Constants.PATH_CHARACTERS_LIST;
import static utilities.Constants.PATH_LINE;
import static utilities.Helpers.getAnimationsX;
import static utilities.Helpers.getFont;
import static utilities.Helpers.getImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.Button;
import main.Game;
import main.GamePanel;
import utilities.Constants.Heroes;

public class Characters {
  private BufferedImage[][] characters = new BufferedImage[2][];
  private Game game;
  private int aniTick = 0;
  private Heroes selectedHero = Heroes.PINK_MONSTER;
  private Rectangle[] charRects = new Rectangle[2];
  private JTextField input;
  private boolean flag = true;
  private Button button;
  private boolean showMessage = false;

  /**
   * Constructor de la clase {@link Characters}
   * @param game
   */
  public Characters(Game game) {
    this.game = game;
    loadImages();
    initRectangles();
    initComponents();
  }

  
  /** 
   * Renderiza la pantalla de selección de personajes
   * @param g
   * @param gamePanel
   */
  public void render(Graphics g, GamePanel gamePanel) {
    for (int i = 0; i < characters.length; i++) {
      g.drawImage(characters[i][aniTick], (GAME_WIDTH / 2) - (TILES_SIZE * i * 6),
          (GAME_HEIGHT / 7), TILES_SIZE * 6, TILES_SIZE * 6, null);
    }

    g.drawImage(getImage(PATH_LINE), (GAME_WIDTH / 2) - (TILES_SIZE *
        selectedHero.ordinal() * 6), (GAME_HEIGHT / 2) - TILES_SIZE, TILES_SIZE * 6,
        TILES_SIZE / 2, null);

    if (flag) {
      JLabel label = new JLabel("Ingresa tu nombre de Usuario");
      label.setForeground(Color.WHITE);
      label.setFont(new Font("MinimalPixel", Font.PLAIN, 60));

      gamePanel.add(input);
      gamePanel.add(label);
      gamePanel.add(button);

      int labelWidth = (int) (TILES_SIZE * 15);
      int labelHeight = (int) (TILES_SIZE * 2);

      input.setBounds((GAME_WIDTH / 2) - (TILES_SIZE * 4), GAME_HEIGHT - (TILES_SIZE * 7), TILES_SIZE * 8, 30);
      label.setBounds((GAME_WIDTH / 2) - (labelWidth / 2), GAME_HEIGHT - labelHeight * 5, labelWidth, labelHeight);
      button.setBounds();

      gamePanel.revalidate();
      flag = false;
    }

    if (showMessage) {
      JLabel message = new JLabel("Debes ingresar un nombre de usuario");
      message.setForeground(Color.WHITE);
      message.setFont(getFont());
      gamePanel.add(message);

      message.setBounds((GAME_WIDTH / 2) - (TILES_SIZE * 9), (TILES_SIZE * 2), (TILES_SIZE * 20), GAME_HEIGHT - TILES_SIZE * 4);

      gamePanel.revalidate();
      showMessage = false;
    }
  }

  /**
   * Inicializa los rectángulos de selección de personajes
   */
  private void initRectangles() {
    for (int i = 0; i < characters.length; i++) {
      charRects[i] = new Rectangle((GAME_WIDTH / 2) - (TILES_SIZE * i * 6), (GAME_HEIGHT / 7), TILES_SIZE * 6,
          TILES_SIZE * 6);
    }
  }

  /**
   * Inicializa los componentes de la pantalla
   */
  private void initComponents() {
    input = new JTextField();
    input.setFont(new Font("MinimalPixel", Font.PLAIN, 60));
    button = new Button((GAME_WIDTH / 2) - (int) (TILES_SIZE * 3.5), GAME_HEIGHT - TILES_SIZE * 4, "Empezar");

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (input.getText().equals("")) {
          showMessage = true;
        } else {
          game.setPlayer(selectedHero, input.getText());
          flag = true;
        }
      }
    });
  }

  private void loadImages() {
    for (int i = 0; i < characters.length; i++) {
      characters[i] = getAnimationsX(PATH_CHARACTERS_LIST[i][4]);
    }
  }

  
  /** 
   * @param e
   */
  public void mouseClick(MouseEvent e) {
    int i = 0;
    for (Rectangle rect : charRects) {
      if (rect.contains(e.getX(), e.getY())) {
        selectedHero = Heroes.values()[i];
      }

      i++;
    }
  }

}
