package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.GamePanel;
import utilities.Constants.States;

public class MouseInputs implements MouseListener {
	GamePanel gamePanel;

	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	
	/** 
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Auto-generated method stub
	}

	
	/** 
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (gamePanel.getGame().getState() == States.CHARACTERS) {
				gamePanel.getGame().getCharacters().mouseClick(e);
		}
	}

	
	/** 
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// Auto-generated method stub

	}

	
	/** 
	 * @param e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// Auto-generated method stub

	}

	
	/** 
	 * @param e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// Auto-generated method stub
	}

}
