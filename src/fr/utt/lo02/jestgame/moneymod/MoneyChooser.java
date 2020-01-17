package fr.utt.lo02.jestgame.moneymod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;
/**
 * cette classe implemente l'interface de ITrophyChooser et permet de choisir les joueurs qui ont la carte trophee
 * @author akramsyukri
 *
 */
public class MoneyChooser implements ITrophyChooser {

	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @param card La carte qui doit choisir un joueur pour la recevoir en tant que trophee.
	 * @param trophyArg Un argument permettant de choisir certains parametres lors du choix du joueur.
	 */
	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg) {
		Player best = null;
		ICard playerBestCard = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			ICard bestCard = null;
			Player current = it.next();
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			while (it2.hasNext()) {
				ICard currentCard = it2.next();
				if (currentCard.getColor() == "MoneyPile") {
					if (bestCard == null) {
						bestCard = currentCard;
					} else if (currentCard.getUpdatedGameFaceValue(players) > bestCard
							.getUpdatedGameFaceValue(players)) {
						bestCard = currentCard;
					}
				}
			}
			if (playerBestCard == null && bestCard != null) {
				playerBestCard = bestCard;
				best = current;
			} else if (bestCard != null) {
				if (bestCard.getUpdatedGameFaceValue(players) > playerBestCard.getUpdatedGameFaceValue(players)) {
					playerBestCard = bestCard;
					best = current;
				}
			}
		}
		return best;
	}

}
