package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Window extends JFrame implements ActionListener {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 4874180209869186885L;
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel chooseModPanel = new JPanel();
	private JPanel nbPlayerPanel = new JPanel();

	private SpringLayout springLayout = new SpringLayout();

	private JTextField nbPlayerField = new JTextField();
	
	private byte nbPlayer;

	public static void main(String[] args) {
		Window window = new Window();
	}

	public Window() {
		super();
		this.setSize((int) screen.getWidth() / 2, (int) screen.getHeight() / 2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		nbPlayerPanel.setLayout(springLayout);
		JLabel title1 = new JLabel();
		title1.setText("Entrez un nombre de joueur compris entre 3 et 4");
		nbPlayerField.setColumns(10);
		springLayout.putConstraint(SpringLayout.WEST, nbPlayerField, 10, SpringLayout.EAST, title1);
		springLayout.putConstraint(SpringLayout.NORTH, title1, 25, SpringLayout.NORTH, nbPlayerPanel);
		springLayout.putConstraint(SpringLayout.NORTH, nbPlayerField, 0, SpringLayout.NORTH, title1);
		nbPlayerPanel.add(title1);
		nbPlayerPanel.add(nbPlayerField);

		this.setContentPane(nbPlayerPanel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("nbPlayer")) {
			nbPlayer = Byte.parseByte(nbPlayerField.getText());
			System.out.println(nbPlayer);
		}

	}

}
