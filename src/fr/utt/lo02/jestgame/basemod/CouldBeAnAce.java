package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Carte mère de toutes les cartes qui peuvent être un as. Pour les cartes de base toutes les cartes sauf le joker
 */
public abstract class CouldBeAnAce extends Card implements ICard {

	/**
	 * @param name 
	 * @param color
	 * @param colorValue
	 * @param baseValue
	 * Constructeur permettant l'appel du constructeur de la classe Mère
	 */
	public CouldBeAnAce(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
	}

	/**
	 * @param players Liste contenant tous les joueurs de la partie
	 * @param myPlayer Le joueur qui posséde actuellement la carte au moment de l'appel de la fonction
	 * @return Renvoie si la carte est un as ou non et en l'occurence renvoie la valaur faciale correcte,
	 * c'est à dire: 5 si l'as est seul et 1 si l'as n'est pas seul
	 */
	public int getAceValue(List<Player> players, Player myPlayer) {
		if (getName() == "Ace") {
			boolean alone = true;
			Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
			while (it.hasNext()) {
				ICard next = it.next();
				if (next.getColor() == this.getColor() && next != this) {
					alone = false;
				}
			}
			if (alone == true) {
				return 5;
			} else {
				return 1;
			}
		} else {
			return getBaseValue();
		}
	}
}
