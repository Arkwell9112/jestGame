package fr.utt.lo02.jestgame.core.graphicui;

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

/**
 * Panel permettant de jouer (affichage des cartes récupération des inputs joueur).
 * @author Edouard
 *
 */
@SuppressWarnings("serial")
public class PlayPanel extends JPanel implements ActionListener {
	private Player actualPlayer;
	private JButton jestButton;
	private List<JButton> activeCardButtons;
	private List<JButton> passiveCardButtons;
	private List<JLabel> trophyLabels;
	private List<JLabel> nameLabels;
	private JLabel centerCard;
	private JLabel actionLabel;
	private ImageIcon center;
	private ImageIcon verso;
	private ImageIcon crossed;
	private String corePath;
	@SuppressWarnings("unused")
	private byte nbPlayer;
	private byte nbCard;
	private byte nbTrophy;
	private Window displayer;
	private SpringLayout layout;

	/**
	 * @param nbPlayer Nombre de joueurs dans la partie.
	 * @param nbCard Nombre de cartes dans la main des joueurs.
	 * @param nbTrophy Nombre de trophées de la partie.
	 * @param displayer La vue associé à ce panel.
	 */
	public PlayPanel(byte nbPlayer, byte nbCard, byte nbTrophy, Window displayer) {
		jestButton = new JButton("Cliquez-ici pour voir votre Jest");
		corePath = "img\\core";
		center = new ImageIcon(corePath + "\\center.jpg");
		verso = new ImageIcon(corePath + "\\verso.jpg");
		crossed = new ImageIcon(corePath + "\\crossed.jpg");
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
		for (int i = 0; i < nbPlayer; i++) {
			JLabel current = new JLabel();
			nameLabels.add(current);
			this.add(current);
		}
		for (int i = 0; i < nbCard; i++) {
			JButton current = new JButton();
			activeCardButtons.add(current);
			current.setActionCommand(Integer.toString(i) + "active");
			current.addActionListener(this);
		}
		for (int i = 0; i < (nbPlayer - 1) * nbCard; i++) {
			JButton current = new JButton();
			passiveCardButtons.add(current);
			current.setActionCommand(Integer.toString(i) + "passive");
			current.addActionListener(this);
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
		this.add(jestButton);
		layout.putConstraint(SpringLayout.SOUTH, jestButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, jestButton, 0, SpringLayout.EAST, this);
		jestButton.addActionListener(this);
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

	/**
	 * Méthode mettant en place le graphisme des trophées.
	 * @param cards Les trophées.
	 */
	public void setTrophies(List<ICard> cards) {
		if (cards.size() == nbTrophy) {
			Iterator<JLabel> it = trophyLabels.iterator();
			Iterator<ICard> it2 = cards.iterator();
			while (it.hasNext()) {
				it.next().setIcon(it2.next().getTexture());
			}
		}
	}

	/**
	 * Méthode mettant en place le nom de l'action en cours.
	 * @param label Titre de l'action.
	 */
	public void setActionLabel(String label) {
		actionLabel.setText(label);
	}

	/**
	 * Méthode mettant à jour les graphismes du panel de jeu.
	 * @param players Liste des joueurs de la partie.
	 * @param activePlayer Le joueur actif.
	 */
	public void setPlay(List<Player> players, Player activePlayer) {  
		actualPlayer = activePlayer;
		int counter = 0;
		if (activePlayer.getFacedUpCard() != null) {
			activeCardButtons.get(counter).setIcon(activePlayer.getFacedUpCard().getTexture());
			counter++;
		}
		Iterator<ICard> it = activePlayer.getHand().iterator();
		while (it.hasNext()) {
			ICard current = it.next();
			if (current != activePlayer.getFacedUpCard()) {
				activeCardButtons.get(counter).setIcon(current.getTexture());
				counter++;
			}
		}
		if (counter != nbCard) {
			for (int i = counter; i < nbCard; i++) {
				activeCardButtons.get(i).setIcon(crossed);
			}
		}
		nameLabels.get(0).setText(activePlayer.getName());
		Iterator<Player> it2 = players.iterator();
		int playerCount = 0;
		while (it2.hasNext()) {
			Player currentPlayer = it2.next();
			if (currentPlayer != activePlayer) {
				nameLabels.get(playerCount + 1).setText(currentPlayer.getName());
				counter = 0;
				if (currentPlayer.getFacedUpCard() != null) {
					passiveCardButtons.get(playerCount * nbCard).setIcon(currentPlayer.getFacedUpCard().getTexture());
					counter++;
				}
				Iterator<ICard> it3 = currentPlayer.getHand().iterator();
				while (it3.hasNext()) {
					ICard currentCard = it3.next();
					if (currentPlayer.getFacedUpCard() != currentCard) {
						// � remettre vers currentCard.getTexture() pour debuggage
						passiveCardButtons.get(playerCount * nbCard + counter).setIcon(verso);
						counter++;
					}
				}
				if (counter != nbCard) {
					for (int i = counter; i < nbCard; i++) {
						passiveCardButtons.get(playerCount * nbCard + i).setIcon(crossed);
					}
				}
				playerCount++;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contains("passive") || e.getActionCommand().contains("active")) {
			displayer.callBack(CallBackEvent.PLAY_PANEL, e.getActionCommand());
		} else {
			displayer.setShowPanel(actualPlayer.getCapturedCards(), actualPlayer);
		}
	}

}
