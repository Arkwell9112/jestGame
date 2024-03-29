package fr.utt.lo02.jestgame.baserulemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IPartyRules;
import fr.utt.lo02.jestgame.core.Player;

/**
 * Classe effectuant l'implementation de IPartyRules. Elle permet d'implementer les regles de base du Jest.
 * @author Edouard
 * 
 */
public class NormalPartyRules implements IPartyRules {

	/**
	 * Choisit le joueur avec la meilleure carte pour commencer (meilleure valeur faciale et meilleure couleur si egalite).
	 */
	@Override
	public Player chooseFirstCatch(List<Player> players) {
		Player best = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			if (best == null) {
				best = current;
			} else if (current.getFacedUpCard().getUpdatedGameFaceValue(players) == best.getFacedUpCard()
					.getUpdatedGameFaceValue(players)) {
				if (best.getFacedUpCard().getColorValue() < current.getFacedUpCard().getColorValue()) {
					best = current;
				}
			} else if (best.getFacedUpCard().getUpdatedGameFaceValue(players) < current.getFacedUpCard()
					.getUpdatedGameFaceValue(players)) {
				best = current;
			}
		}
		return best;
	}

	/**
	 * Choisit le vainqueur de la partie, avec ces regles il s'agit du joueur avec le meilleur Jest.
	 */
	@Override
	public Player chooseWinner(List<Player> players) {
		Player best = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			if (best == null) {
				best = current;
			} else if (best.calculateScore(players) < current.calculateScore(players)) {
				best = current;
			} else if (best.calculateScore(players) == current.calculateScore(players)) {
				Iterator<ICard> it2 = best.getCapturedCards().iterator();
				int bestValue = 0;
				int bestColorValue = 0;
				while (it2.hasNext()) {
					ICard currentCard = it2.next();
					if (currentCard.getUpdatedGameFaceValue(players) > bestValue) {
						bestValue = Math.abs(currentCard.endFaceValue(players, best));
						bestColorValue = currentCard.getColorValue();
					}
				}

				it2 = current.getCapturedCards().iterator();
				int currentValue = 0;
				int currentColorValue = 0;
				while (it2.hasNext()) {
					ICard currentCard = it2.next();
					if (currentCard.getUpdatedGameFaceValue(players) > bestValue) {
						currentValue = Math.abs(currentCard.endFaceValue(players, current));
						currentColorValue = currentCard.getColorValue();
					}
				}

				if (bestValue < currentValue) {
					best = current;
				} else if (bestValue == currentValue) {
					if (bestColorValue < currentColorValue) {
						best = current;
					}
				}
			}
		}
		return best;
	}

	@Override
	public int getPlayerCardNb() {
		return 2;
	}

	@Override
	public int getTrophyCardNb(int nbPlayer) {
		if (nbPlayer == 3) {
			return 2;
		} else {
			return 1;
		}
	}

}
