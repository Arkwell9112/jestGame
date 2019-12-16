package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import fr.utt.lo02.jestgame.api.IMod;

@SuppressWarnings("serial")
public class ModPanel extends JPanel implements ActionListener {
	private JLabel title;
	private JTextArea text;
	private JScrollPane scroll;
	private JButton button1;
	private JButton button2;
	private Window displayer;
	private SpringLayout layout;
	private JLabel beforeTitle;

	public ModPanel(Window displayer) {
		this.displayer = displayer;
		layout = new SpringLayout();
		this.setLayout(layout);
		beforeTitle = new JLabel();
		title = new JLabel();
		text = new JTextArea(5, 30);
		text.setEditable(false);
		text.setLineWrap(true);
		scroll = new JScrollPane(text);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		button1 = new JButton("Oui");
		button2 = new JButton("Non");
		this.button1.addActionListener(this);
		this.button2.addActionListener(this);
		this.add(beforeTitle);
		this.add(title);
		this.add(scroll);
		this.add(button1);
		this.add(button2);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scroll, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scroll, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, title, -20, SpringLayout.NORTH, scroll);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, beforeTitle, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, beforeTitle, -20, SpringLayout.NORTH, title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button1, 0, SpringLayout.WEST, scroll);
		layout.putConstraint(SpringLayout.NORTH, button1, 10, SpringLayout.SOUTH, scroll);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button2, 0, SpringLayout.EAST, scroll);
		layout.putConstraint(SpringLayout.NORTH, button2, 10, SpringLayout.SOUTH, scroll);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Oui")) {
			displayer.callBack(null, true);
		} else {
			displayer.callBack(null, false);
		}
	}

	public void displayMod(IMod mod) {
		title.setText(mod.getName());
		text.setText(mod.getDescription());
	}

	public void setBeforeTitle(String title) {
		beforeTitle.setText(title);
	}

}
