package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class PlayPanel extends JPanel implements ActionListener {
	private List<JButton> activeCardButtons;
	private List<JButton> passiveCardButtons;
	private List<JLabel> trophyLabels;
	private List<JLabel> nameLabels;
	private JLabel centerCard;
	private JLabel actionLabel;
	private byte nbPlayer;
	private byte nbCard;
	private byte nbTrophy;
	private Window displayer;
	private SpringLayout layout;

	public PlayPanel(byte nbPlayer, byte nbCard, byte nbTrophy, Window displayer) {
		layout = new SpringLayout();
		this.setLayout(layout);
		this.nbTrophy = nbTrophy;
		this.displayer = displayer;
		this.nbPlayer = nbPlayer;
		this.nbCard = nbCard;
		activeCardButtons = new ArrayList<JButton>();
		passiveCardButtons = new ArrayList<JButton>();
		trophyLabels = new ArrayList<JLabel>();
		nameLabels = new ArrayList<JLabel>();
		ImageIcon center = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\img\\core\\centercard.png")
				.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		for (int i = 0; i < nbPlayer; i++) {
			JLabel current = new JLabel("Le nom");
			nameLabels.add(current);
			this.add(current);
		}
		for (int i = 0; i < nbCard; i++) {
			JButton current = new JButton();
			activeCardButtons.add(current);
			current.setActionCommand(Integer.toString(i) + "active");
		}
		for (int i = 0; i < (nbPlayer - 1) * nbCard; i++) {
			JButton current = new JButton();
			passiveCardButtons.add(current);
			current.setActionCommand(Integer.toString(i) + "passive");
		}
		for (int i = 0; i < nbTrophy; i++) {
			JLabel current = new JLabel();
			trophyLabels.add(current);
			this.add(current);
		}
		centerCard = new JLabel();
		centerCard.setIcon(center);
		actionLabel = new JLabel();
		this.add(centerCard);
		this.add(actionLabel);
		Iterator<JButton> it = activeCardButtons.iterator();
		while (it.hasNext()) {
			this.add(it.next());
		}
		it = passiveCardButtons.iterator();
		while (it.hasNext()) {
			this.add(it.next());
		}
		for (int i = 0; i < activeCardButtons.size(); i++) {
			int rank;
			if (i > 2) {
				rank = i - 2;
			} else {
				rank = 0;
			}
			if (i == 0) {
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, activeCardButtons.get(0), 0,
						SpringLayout.HORIZONTAL_CENTER, this);
			} else if ((i & 1) == 0) {
				layout.putConstraint(SpringLayout.WEST, activeCardButtons.get(i), 10, SpringLayout.EAST,
						activeCardButtons.get(rank));
			} else {
				layout.putConstraint(SpringLayout.EAST, activeCardButtons.get(i), -10, SpringLayout.WEST,
						activeCardButtons.get(rank));
			}
			layout.putConstraint(SpringLayout.SOUTH, activeCardButtons.get(i), 0, SpringLayout.SOUTH, this);
		}
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, centerCard, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, centerCard, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, actionLabel, -10, SpringLayout.NORTH, centerCard);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, actionLabel, 0, SpringLayout.HORIZONTAL_CENTER,
				centerCard);
		for (int i = 0; i < nbCard; i++) {
			int rank;
			if (i > 2) {
				rank = i - 2;
			} else {
				rank = 0;
			}
			if (i == 0) {
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, passiveCardButtons.get(0), 0,
						SpringLayout.VERTICAL_CENTER, this);
			} else if ((i & 1) == 0) {
				layout.putConstraint(SpringLayout.SOUTH, passiveCardButtons.get(i), -10, SpringLayout.NORTH,
						passiveCardButtons.get(rank));
			} else {
				layout.putConstraint(SpringLayout.NORTH, passiveCardButtons.get(i), 10, SpringLayout.SOUTH,
						passiveCardButtons.get(rank));
			}
			layout.putConstraint(SpringLayout.WEST, passiveCardButtons.get(i), 0, SpringLayout.WEST, this);
		}
		for (int i = nbCard; i < 2 * nbCard; i++) {
			int rank;
			if (i > 2 + 2 * nbCard) {
				rank = i - 2;
			} else {
				rank = nbCard;
			}
			if (i == nbCard) {
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, passiveCardButtons.get(nbCard), 0,
						SpringLayout.VERTICAL_CENTER, this);
			} else if ((i & 1) == 0) {
				layout.putConstraint(SpringLayout.SOUTH, passiveCardButtons.get(i), -10, SpringLayout.NORTH,
						passiveCardButtons.get(rank));
			} else {
				layout.putConstraint(SpringLayout.NORTH, passiveCardButtons.get(i), 10, SpringLayout.SOUTH,
						passiveCardButtons.get(rank));
			}
			layout.putConstraint(SpringLayout.EAST, passiveCardButtons.get(i), 0, SpringLayout.EAST, this);
		}
		if (nbPlayer == 4) {
			for (int i = 2 * nbCard; i < 3 * nbCard; i++) {
				int rank;
				if (i > 2 + 3 * nbCard) {
					rank = i - 2;
				} else {
					rank = 2 * nbCard;
				}
				if (i == 2 * nbCard) {
					layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passiveCardButtons.get(2 * nbCard), 0,
							SpringLayout.HORIZONTAL_CENTER, this);
				} else if ((i & 1) == 0) {
					layout.putConstraint(SpringLayout.WEST, passiveCardButtons.get(i), 10, SpringLayout.EAST,
							passiveCardButtons.get(rank));
				} else {
					layout.putConstraint(SpringLayout.EAST, passiveCardButtons.get(i), -10, SpringLayout.WEST,
							passiveCardButtons.get(rank));
				}
				layout.putConstraint(SpringLayout.NORTH, passiveCardButtons.get(i), 0, SpringLayout.NORTH, this);
			}
		}
		for (int i = 0; i < trophyLabels.size(); i++) {
			JLabel current;
			if (i > 1) {
				current = trophyLabels.get(i - 2);
			} else {
				current = centerCard;
			}
			if ((i & 1) == 0) {
				layout.putConstraint(SpringLayout.WEST, trophyLabels.get(i), 10, SpringLayout.EAST, current);
			} else {
				layout.putConstraint(SpringLayout.EAST, trophyLabels.get(i), -10, SpringLayout.WEST, current);
			}
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, trophyLabels.get(i), 0, SpringLayout.VERTICAL_CENTER,
					this);
		}
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLabels.get(0), 0, SpringLayout.HORIZONTAL_CENTER,
				activeCardButtons.get(0));
		layout.putConstraint(SpringLayout.SOUTH, nameLabels.get(0), -10, SpringLayout.NORTH, activeCardButtons.get(0));
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nameLabels.get(1), 0, SpringLayout.VERTICAL_CENTER,
				passiveCardButtons.get(0));
		layout.putConstraint(SpringLayout.WEST, nameLabels.get(1), 10, SpringLayout.EAST, passiveCardButtons.get(0));
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nameLabels.get(2), 0, SpringLayout.VERTICAL_CENTER,
				passiveCardButtons.get(nbCard));
		layout.putConstraint(SpringLayout.EAST, nameLabels.get(2), -10, SpringLayout.WEST,
				passiveCardButtons.get(nbCard));
		if (nbPlayer == 4) {
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLabels.get(3), 0, SpringLayout.HORIZONTAL_CENTER,
					passiveCardButtons.get(2 * nbCard));
			layout.putConstraint(SpringLayout.NORTH, nameLabels.get(3), 10, SpringLayout.SOUTH,
					passiveCardButtons.get(2 * nbCard));
		}
	}

	public void setTrophies(List<ICard> cards) {
		if (cards.size() == nbTrophy) {
			Iterator<JLabel> it = trophyLabels.iterator();
			Iterator<ICard> it2 = cards.iterator();
			while (it.hasNext()) {
				it.next().setIcon(it2.next().getTexture());
			}
		}
		this.revalidate();
	}

	public void setActionLabel(String label) {
		actionLabel.setText(label);
	}

	public void setPlay(List<Player> players, Player activePlayer) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
