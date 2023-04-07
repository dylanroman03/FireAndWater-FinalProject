package screens;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Game;
import main.GamePanel;

public class Instructions {

  public Instructions(Game game) {
  }

  public void render(Graphics2D g, GamePanel gamePanel) {
    g.setColor(Color.WHITE);
    g.drawRect(10, 10, 10, 10);
  }
  
}
