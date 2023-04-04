package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInputs implements KeyListener {
	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (gamePanel.getGame().getState()) {
			case PLAYING:
				switch (e.getKeyCode()) {
					case KeyEvent.VK_W:
						gamePanel.getGame().getPlaying().getPlayer().setJump(false);
						break;
					case KeyEvent.VK_A:
						gamePanel.getGame().getPlaying().getPlayer().setLeft(false);
						break;
					case KeyEvent.VK_S:
						gamePanel.getGame().getPlaying().getPlayer().setFall(false);
						break;
					case KeyEvent.VK_D:
						gamePanel.getGame().getPlaying().getPlayer().setRight(false);
						break;
					default:
						break;
				}
				break;

			default:
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (gamePanel.getGame().getState()) {
			case PLAYING:
				switch (e.getKeyCode()) {
					case KeyEvent.VK_W:
						gamePanel.getGame().getPlaying().getPlayer().setJump(true);
						break;
					case KeyEvent.VK_A:
						gamePanel.getGame().getPlaying().getPlayer().setLeft(true);
						break;
					case KeyEvent.VK_S:
						gamePanel.getGame().getPlaying().getPlayer().setFall(true);
						break;
					case KeyEvent.VK_D:
						gamePanel.getGame().getPlaying().getPlayer().setRight(true);
						break;
					default:
						break;
				}
				break;

			default:
				break;
		}
	}
}
