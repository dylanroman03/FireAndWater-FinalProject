package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import utilities.Constants.Heroes;
import utilities.Constants.States;

public class KeyboardInputs implements KeyListener {
	private GamePanel gamePanel;
	private int[] pinkKeys = { KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D };
	private int[] dudeKeys = { KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT };

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (gamePanel.getGame().getState() == States.PLAYING) {
			int[] keys = gamePanel.getGame().getPlaying().getPlayer().getHero() == Heroes.PINK_MONSTER ? pinkKeys : dudeKeys;

			if (e.getKeyCode() == keys[0]) {
				gamePanel.getGame().getPlaying().getPlayer().setJump(false);
			} else if (e.getKeyCode() == keys[1]) {
				gamePanel.getGame().getPlaying().getPlayer().setLeft(false);
			} else if (e.getKeyCode() == keys[2]) {
				gamePanel.getGame().getPlaying().getPlayer().setFall(false);
			} else if (e.getKeyCode() == keys[3]) {
				gamePanel.getGame().getPlaying().getPlayer().setRight(false);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (gamePanel.getGame().getState() == States.PLAYING) {
			int[] keys = gamePanel.getGame().getPlaying().getPlayer().getHero() == Heroes.PINK_MONSTER ? pinkKeys : dudeKeys;

			if (e.getKeyCode() == keys[0]) {
				gamePanel.getGame().getPlaying().getPlayer().setJump(true);
			} else if (e.getKeyCode() == keys[1]) {
				gamePanel.getGame().getPlaying().getPlayer().setLeft(true);
			} else if (e.getKeyCode() == keys[2]) {
				gamePanel.getGame().getPlaying().getPlayer().setFall(true);
			} else if (e.getKeyCode() == keys[3]) {
				gamePanel.getGame().getPlaying().getPlayer().setRight(true);
			}
		}
	}
}
