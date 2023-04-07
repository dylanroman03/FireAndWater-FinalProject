package screens;

import static utilities.Helpers.getFont;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import gui.Button;
import gui.Dialog;
import main.Game;
import main.GamePanel;
import utilities.Constants.States;

public class Credits extends Dialog {
  private boolean flag = true;

  public Credits(Game game) {
    super(game);
  }

  @Override
  public void render(Graphics g, GamePanel gamePanel) {
    super.render(g, gamePanel);

    if (flag) {
      JLabel title = new JLabel("Creditos:");
      JLabel name = new JLabel("Dylan Buitrago");
      Button button = new Button((int) ((int) this.x + (this.width / 4.2)), (int) (this.y + (this.height / 1.5)),
          "Menu");

      // add click funtion for menuButton
      button.addActionListener(e -> {
        game.setState(States.MENU);
        flag = true;
      });

      title.setFont(getFont());
      name.setFont(getFont());

      title.setForeground(Color.WHITE);
      name.setForeground(Color.WHITE);

      gamePanel.add(title);
      gamePanel.add(name);
      gamePanel.add(button);

      title.setBounds((int) this.x + (this.width / 4), this.y + (int) (this.height / 9), 200, 50);
      name.setBounds((int) this.x + (this.width / 4), this.y + (int) (this.height / 3.2), this.width, 50);
      button.setBounds();

      gamePanel.revalidate();
      flag = false;
    }
  }

}
