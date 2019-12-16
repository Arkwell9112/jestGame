package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class NbBotPanel extends JPanel implements ActionListener {
	private JLabel text;
	private JTextField textField;
	private JButton button;
	private SpringLayout layout;
	private Window displayer;
	private byte nbPlayer;

	public NbBotPanel(Window displayer, byte nbPlayer) {
		this.nbPlayer = nbPlayer;
		this.displayer = displayer;
		text = new JLabel("Entrez un nombre de joueurs robots laissant au minimum un joueur humain");
		textField = new JTextField();
		button = new JButton("Entrer");
		layout = new SpringLayout();
		textField.setColumns(10);
		button.addActionListener(this);
		this.setLayout(layout);
		this.add(textField);
		this.add(text);
		this.add(button);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, textField, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, text, -10, SpringLayout.NORTH, textField);
		layout.putConstraint(SpringLayout.NORTH, button, 10, SpringLayout.SOUTH, textField);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, textField, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			byte nbBot = Byte.parseByte(textField.getText());

			if (nbPlayer - nbBot > 0) {
				displayer.callBack(null, nbBot);
			} else {
				text.setForeground(Color.RED);
			}
		} catch (NumberFormatException e1) {
			text.setForeground(Color.RED);
		}
	}

}
