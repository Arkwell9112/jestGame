package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * Panel pour le choix des noms.
 * @author Edouard
 *
 */
@SuppressWarnings("serial")
public class NamePanel extends JPanel implements ActionListener {
	private JLabel text;
	private JTextField textField;
	private JButton button;
	private SpringLayout layout;
	private Window displayer;

	/**
	 * @param displayer La vue associé à ce panel.
	 */
	public NamePanel(Window displayer) {
		this.displayer = displayer;
		text = new JLabel();
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

	/**
	 * Méthode mettant à jour le titre pourle choix des noms.
	 * @param nb Index du joueur dont on demande le nom.
	 */
	public void askName(byte nb) {
		text.setText("Veuillez entrer le nom du joueur: " + nb
				+ ", le nom des bots doit �tre donn� dans le m�me ordre que le choix des IAs et les noms des joueurs humains doivent �tre donn� en dernier svp");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		displayer.callBack(CallBackEvent.NAME_PANEL, textField.getText());
	}

}
