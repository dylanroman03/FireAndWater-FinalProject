package screens;

import static main.Game.GAME_WIDTH;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Button;
import gui.Dialog;
import main.Game;
import main.GamePanel;
import utilities.Constants.States;

public class MainMenu extends Dialog {
  private Button playButton;
  private Button instructionButton;
  private Button creditsButton;
  private Button quitButton;
  private boolean flag = true;
  private Game game;
  private GamePanel gamePanel;

  public MainMenu(Game game) {
    super(game, (int) (GAME_WIDTH / 1.9), (int) (GAME_WIDTH / 1.9));
    this.game = game;
    initClasses();
  }

  private void initClasses() {
    playButton = new Button((int) this.x + (this.width / 4), this.y + (int) (this.height / 9), "Jugar");
    instructionButton = new Button((int) this.x + (this.width / 4), this.y + (int) (this.height / 3.2),
        "Instrucciones");
    creditsButton = new Button((int) this.x + (this.width / 4), this.y + (int) (this.height / 1.95), "Creditos");
    quitButton = new Button((int) this.x + (this.width / 4), this.y + (int) (this.height / 1.4), "Salir");

    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Jugar");
        game.setState(States.CHARACTERS);
        flag = true;
      }
    });

    instructionButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Instrucciones");
        game.setState(States.INSTRUCTIONS);
        flag = true;
      }

    });

    creditsButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Creditos");
        game.setState(States.CREDITS);
        flag = true;
      }
    });

    quitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Saliendo del juego");
        System.exit(0);
      }
    });
  }

  @Override
  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);
    this.gamePanel = gamePanel;

    if (flag) {
      gamePanel.add(playButton);
      gamePanel.add(instructionButton);
      gamePanel.add(creditsButton);
      gamePanel.add(quitButton);

      playButton.setBounds();
      instructionButton.setBounds();
      creditsButton.setBounds();
      quitButton.setBounds();

      gamePanel.revalidate();
      flag = false;
    }
  }

}
