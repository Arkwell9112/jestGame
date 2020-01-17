package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

/**
 * Carte mere de toutes les cartes qui peuvent etre un as. Il s'agit donc de toutes les cartes sauf le Joker.
 * Cette classe herite de Card.
 * @author Edouard
 * 
 * 
 */
public abstract class CouldBeAnAce extends Card {

	/**
	 * @param name Nom de la carte.
	 * @param color Nom de la couleur de la carte.
	 * @param colorValue Valeur de la couleur de la carte.
	 * @param baseValue Valeur de base de la carte.
	 * @param texture Texture de la carte.
	 * Constructeur de la classe. 
	 */
	public CouldBeAnAce(String name, String color, int colorValue, int baseValue, ImageIcon texture) {
		super(name, color, colorValue, baseValue, texture);
	}

	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @param myPlayer Le joueur qui possede cette instance de carte.
	 * @return Renvoie la valeur de base de la carte si celle-ci n'est pas un as. Sinon renvoie 1 si l'as n'est pas tout seul et 5 si l'as est tout seul.
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
