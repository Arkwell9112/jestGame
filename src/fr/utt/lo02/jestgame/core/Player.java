package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

/**
 * @author Edouard
 * Classe de base pour tous les joueurs.
 */
public abstract class Player extends Observable {

	/**
	 * Liste de ICards faisant office de main du joueur.
	 */
	private List<ICard> hand;
	/**
	 * Liste de ICards faisant office de Jest du joueur.
	 */
	private List<ICard> capturedCards;
	/**
	 * Nomdu joueur.
	 */
	private String name;
	/**
	 * Valeur du Jest du joueur.
	 */
	private int score;
	/**
	 * La joueur a-t-il d�j� jouer pendant ce tour de mise en face visible ?
	 */
	private boolean isFacedUp;
	/**
	 * La joueur a-t-il d�j� jouer pendant ce tour de capture ?
	 */
	private boolean isCatchedUp;
	/**
	 * Ses cartes ont-elles �t� distribu�es au joueur ?
	 */
	private boolean isHanded;
	/**
	 * A quelle place ce joueur joue-t-il dans le tour de mise en face visible.
	 */
	private int facedUpRank;
	/**
	 * R�f�rence vers l'instance de la partie en cours.
	 */
	private Party currentParty;
	/**
	 * Ce joueur a-t-il d�j� captur� pendant ce tour de capture.
	 */
	private boolean hasCatchedUp;
	/**
	 * La carte actuellement face visible de ce joueur.
	 */
	private ICard facedUpCard;
	
	/**
	 * Met hasCatchedUp � false.
	 */
	public void resetHasCatchedUp() {
		hasCatchedUp = false;
	}
	
	/**
	 * Met hasCatchedUp � true.
	 */
	public void setHasCatchedUp() {
		hasCatchedUp = true;
	}
	
	/**
	 * @return Renvoie hasCatchedUp.
	 */
	public boolean isHasCatchedUp() {
		return hasCatchedUp;
	}
	
	/**
	 * @return Renvoie la liste de cartes du Jest du joueur.
	 */
	public List<ICard> getCapturedCards() {
		return capturedCards;
	}

	/**
	 * @return Renvoie le nom du joueur.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Renvoie la liste de cartes de la main du joueur.
	 */
	public List<ICard> getHand() {
		return hand;
	}

	/**
	 * @param name Nom du joueur.
	 * @param interfac Controller auquel le joueur est reli�.
	 * Constructeur de la classe Player.
	 */
	public Player(String name, IObserver interfac) {
		this.name = name;
		if (interfac != null) {
			addObserver(interfac);
		}

		isFacedUp = false;
		isCatchedUp = false;
		isHanded = false;
		facedUpRank = 0;
		hand = new ArrayList<ICard>();
		capturedCards = new ArrayList<ICard>();
		hasCatchedUp = false;
		facedUpCard = null;
	}

	/**
	 * @return Renvoie une r�f�rence vers la partie � laquelle appartient ce joueur.
	 */
	public Party getCurrentParty() {
		return currentParty;
	}

	/**
	 * @param currentParty La partie � laquelle appartient ce joueur.
	 * Setteur de currentParty.
	 */
	public void setCurrentParty(Party currentParty) {
		this.currentParty = currentParty;
	}

	/**
	 * @param name Nom du joueur.
	 * Setteur pour le nom du joueur.
	 */
	public void setName(String name) {
		if (this.name == null) {
			this.name = name;
		}
	}

	/**
	 * @param interfac Controller li� � ce joueur.
	 * Setteur du Controller de ce joueur.
	 */
	public void setInterface(IObserver interfac) {
		addObserver(interfac);
	}

	/**
	 * @param cards Liste de cartes composant la future main du joueur.
	 * Setteur pour la main du joueur.
	 */
	public void setHand(List<ICard> cards) {
		if (isHanded == false) {
			hand = cards;
			isHanded = true;
		}
	}

	/**
	 * @param card Carte � ajouter dans la main du joueur.
	 * Ajoute une carte dans la main du joueur.
	 */
	public void addHand(ICard card) {
		hand.add(card);
	}

	/**
	 * @param card Carte � ajouter dans le Jest du joueur.
	 * Ajoute une carte dans le Jest du joueur.
	 */
	public void addCapturedCard(ICard card) {
		capturedCards.add(card);
	}

	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * Permet de notifier au joueur que celui-ci doit jouer son tour de mise en face visible.
	 */
	public abstract void yourTurnFaceUp(List<Player> players);

	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * Permet de notifier au joueur que celui-ci doit jouer son tour de capture.
	 */
	public abstract void yourTurnCatchUp(List<Player> players);

	/**
	 * @return Renvoie une liste de cartes, il s'agit du Jest du joueur.
	 * Getteur du Jest du joueur.
	 */
	public ICard getFacedUpCard() {
		if(isFacedUp && hand.contains(facedUpCard)) {
			return facedUpCard;
		} else {
			return null;
		}
	}

	/**
	 * @return Revnoie si le joueur a d�j� mis une carte face visible durant ce tour.
	 * Getteur de is FacedUp.
	 */
	public boolean isFacedUp() {
		return isFacedUp;
	}

	/**
	 * @param facedUp L'index de la carte � mettre face visible.
	 * Setteur de la carte face visible du joueur.
	 */
	public void setFacedUp(int facedUp) {
		isFacedUp = true;
		facedUpRank = facedUp;
		facedUpCard = hand.get(facedUpRank);
	}
	
	/**
	 * Met isFacedUp � false.
	 */
	public void resetFacedUp() {
		isFacedUp = false;
	}

	/**
	 * @return Renvoie si le joueur a d�j� �t� capture pendant ce tour.
	 * Getteur de isCatchedUp.
	 */
	public boolean isCatchedUp() {
		return isCatchedUp;
	}

	/**
	 * Met isCatchedUp � false.
	 */
	public void setCatchedUp() {
		isCatchedUp = false;
	}

	/**
	 * @param isCatchingFacedUpCard Bool�en sp�cifiant si la carte captur� doit �tre celle face visible ou une autre.
	 * @return Renvoie la carte captur�.
	 * M�thode permettant de capturer une des cartes de ce joueur.
	 */
	public ICard catchUp(boolean isCatchingFacedUpCard) {
		if (isCatchedUp == false) {
			isCatchedUp = true;
			if (isCatchingFacedUpCard) {
				ICard card = hand.get(facedUpRank);
				hand.remove(facedUpRank);
				return card;
			} else {
				List<ICard> newHand = new ArrayList<ICard>(hand);
				newHand.remove(newHand.get(facedUpRank));
				int rand = (int) Math.random() * newHand.size();
				ICard choosen = newHand.get(rand);
				hand.remove(choosen);
				return choosen;
			}
		}
		else {
			return null;
		}
	}
	
	/**
	 * Méthode permettant de calculer le score du joueur.
	 * @param players Liste de toutes les instances des joueurs de la partie en cours.
	 * @return Renvoie le score de ce joueur.
	 */
	public int calculateScore(List<Player> players) {
		int sum = 0;
		Iterator<ICard> it = capturedCards.iterator();
		while(it.hasNext()) {
			ICard next = it.next();
			sum = sum + next.endFaceValue(players, this) + next.endSpecialFaceValue(players, this);
		}
		
		score = sum;
		return score;
	}

}
