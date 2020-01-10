package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import fr.utt.lo02.jestgame.api.ICard;

@SuppressWarnings("serial")
public class ShowPanel extends JPanel implements ActionListener {
	private JLabel state;
	private Window displayer;
	private JLabel who;
	private List<JLabel> cards;
	private SpringLayout layout;
	private JButton next;

	public ShowPanel(Window displayer) {
		state = new JLabel();
		who = new JLabel();
		cards = new ArrayList<JLabel>();
		layout = new SpringLayout();
		next = new JButton("Revenir à la partie");
		next.addActionListener(this);
		this.displayer = displayer;
		this.setLayout(layout);
		cards.add(new JLabel());
		this.add(state);
		this.add(who);
		this.add(cards.get(0));
		this.add(next);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cards.get(0), 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, cards.get(0), 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, who, 0, SpringLayout.HORIZONTAL_CENTER, cards.get(0));
		layout.putConstraint(SpringLayout.SOUTH, who, -10, SpringLayout.NORTH, cards.get(0));
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, state, 0, SpringLayout.HORIZONTAL_CENTER, cards.get(0));
		layout.putConstraint(SpringLayout.SOUTH, state, -10, SpringLayout.NORTH, who);
		layout.putConstraint(SpringLayout.EAST, next, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, next, 0, SpringLayout.SOUTH, this);
	}

	public void setWin(String state, String who, List<ICard> cards) {
		this.state.setText(state);
		this.who.setText(who);
		if (this.cards.size() < cards.size()) {
			int delta = cards.size() - this.cards.size();
			for (int i = 1; i <= delta; i++) {
				JLabel neo = new JLabel();
				this.add(neo);
				int index = this.cards.size();
				int relative = 0;
				boolean odd = false;
				if (index > 2) {
					relative = index - 2;
				} else {
					relative = 0;
				}
				if ((index & 1) == 0) {
					odd = true;
				}
				if (odd) {
					layout.putConstraint(SpringLayout.WEST, neo, 10, SpringLayout.EAST, this.cards.get(relative));
				} else {
					layout.putConstraint(SpringLayout.EAST, neo, -10, SpringLayout.WEST, this.cards.get(relative));
				}
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, neo, 0, SpringLayout.VERTICAL_CENTER,
						this.cards.get(0));
				this.cards.add(neo);
			}
		}
		Iterator<ICard> it = cards.iterator();
		Iterator<JLabel> it2 = this.cards.iterator();
		while (it.hasNext() && it2.hasNext()) {
			it2.next().setIcon(it.next().getTexture());
		}
		if (this.cards.size() > cards.size()) {
			for (int i = cards.size(); i <= this.cards.size() - 1; i++) {
				this.cards.get(i).setIcon(null);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		displayer.showPlayPanel();
	}
}
