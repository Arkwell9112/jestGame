package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

/**
 * Pile de carte du Jest.
 * @author Edouard
 *
 */
public class DrawStream {
	/**
	 * Cartes présentes dans la pile de cartes.
	 */
	private List<ICard> draft;
	
	/**
	 * Constructeur de la classe, disposant les cartes de manière aléatoire dans la pile.
	 * @param cards Liste des cartes à mettre dans la pile.
	 */
	public DrawStream(List<ICard> cards) {
		draft = new ArrayList<ICard>(100);
		
		while(cards.size() != 0) {
			byte rand = (byte) (Math.random()*cards.size());
			draft.add(cards.get(rand));
			cards.remove(rand);
		}
	}
	
	/**
	 * Getteur du nombre de cartes restant dans la pile.
	 * @return Renvoie le nombre de cartes restantes dans la pile.
	 */
	public int getRemainingCards() {
		return draft.size();
	}
	
	/**
	 * Méthode renvoyant la carte du dessus de la pile puis retire cette carte de la pile.
	 * @return Renvoie la carte du dessus de la pile.
	 */
	public ICard getDraft() {
		ICard card = draft.get(0);
		draft.remove(0);
		return card;
	}
}
