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
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				gamePanel.getGame().getPlayer().setJump(false);
				break;
			case KeyEvent.VK_A:
				gamePanel.getGame().getPlayer().setLeft(false);
				break;
			case KeyEvent.VK_S:
				gamePanel.getGame().getPlayer().setFall(false);
				break;
			case KeyEvent.VK_D:
				gamePanel.getGame().getPlayer().setRight(false);
				break;
			default:
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				gamePanel.getGame().getPlayer().setJump(true);
				break;
			case KeyEvent.VK_A:
				gamePanel.getGame().getPlayer().setLeft(true);
				break;
			case KeyEvent.VK_S:
				gamePanel.getGame().getPlayer().setFall(true);
				break;
			case KeyEvent.VK_D:
				gamePanel.getGame().getPlayer().setRight(true);
				break;
			default:
				break;
		}
	}
}
