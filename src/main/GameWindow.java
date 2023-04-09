package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	public GameWindow(GamePanel gamePanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gamePanel);
		setResizable(false);
		pack();
		setVisible(true);
		addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent arg0) {
			}

			@Override
			public void windowLostFocus(WindowEvent arg0) {
				gamePanel.getGame().windowsFocusLost();
			}

		});
	}

}
