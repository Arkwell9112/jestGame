package fr.utt.lo02.jestgame.monsterandsword;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

/**
 * cette classe implemente l'interface ITrophyChooser et va choisir le joueur qui a carte 'Monster'
 * @author akramsyukri
 *
 */
public class WithMonster implements ITrophyChooser {
	
	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @param card La carte qui doit choisir un joueur pour la recevoir en tant que trophee.
	 * @param trophyArg Un argument permettant de choisir certains parametres lors du choix du joueur.
	 */
	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg) {
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			while (it2.hasNext()) {
				ICard currentCard = it2.next();
				if (currentCard.getName() == "Monster") {
					return current;
				}
			}
		}
		return null;
	}
}
