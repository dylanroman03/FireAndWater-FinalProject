package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

	private Game game;

	public GamePanel(Game game) {
		MouseInputs mouseInputs;
		mouseInputs = new MouseInputs(this);
		this.game = game;

		try {
      // create the font to use. Specify the size!
      Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/MinimalPixelFont.ttf")).deriveFont(12f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      // register the font
      ge.registerFont(customFont);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (FontFormatException e) {
      e.printStackTrace();
    }

		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
    setLayout(null);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		System.out.println("Dimesion:" + size.width + " " + size.height);
		System.out.println("Tile:" + Game.TILES_SIZE);
		setPreferredSize(size);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		game.render(g);
	}

	public Game getGame() {
		return game;
	}

}