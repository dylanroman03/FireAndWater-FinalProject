package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
	GamePanel gamePanel;

	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (gamePanel.getGame().getState()) {
			case CHARACTERS:
				gamePanel.getGame().getCharacters().mouseClick(e);
				break;
		
			default:
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Auto-generated method stub
	}

}
