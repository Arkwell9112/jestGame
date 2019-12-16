package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public static void main(String[] args) {
		Window window = new Window();
		JPanel panel = new PlayPanel((byte) 4, (byte) 3, (byte) 2, window);
		window.setContentPane(panel);
		window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		window.setVisible(true);

	}

	public void callBack(CallBackEvent event, Object arg) {

	}
}
