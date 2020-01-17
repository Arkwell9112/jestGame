package fr.utt.lo02.jestgame.jest11;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IPartyRules;
import fr.utt.lo02.jestgame.core.Player;

/**
 * cette classe implementer IPartyRules et permet de jouer de maniere l'inverse(celui avec moins de points va gagner)
 * @author akramsyukri
 *
 */
public class Jest11 implements IPartyRules {

	/**
	 * @param players Liste de toutes les instances de Player de la partie qui va choisir en premier.
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
	 * @param players Liste de toutes les instances de Player de la partie et qui va etre choisi en tant que gagnant.
	 */
	@Override
	public Player chooseWinner(List<Player> players) {
		Player winner = null;
		int winnerDelta = 0;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			int currentDelta = current.calculateScore(players) - 11;
			if (winner == null) {
				winner = current;
				winnerDelta = winner.calculateScore(players) - 11;
			} else if (Math.abs(currentDelta) < Math.abs(winnerDelta)) {
				winner = current;
				winnerDelta = currentDelta;
			} else if (Math.abs(currentDelta) == Math.abs(winnerDelta)) {
				if (currentDelta < winnerDelta) {
					winner = current;
					winnerDelta = currentDelta;
				} else if (currentDelta == winnerDelta) {
					Iterator<ICard> it2 = current.getCapturedCards().iterator();
					ICard lowest = null;
					while (it2.hasNext()) {
						ICard cardCurrent = it2.next();
						if (lowest == null) {
							lowest = cardCurrent;
						} else if (cardCurrent.endFaceValue(players, current) < lowest.endFaceValue(players, current)) {
							lowest = cardCurrent;
						} else if (cardCurrent.endFaceValue(players, current) == lowest.endFaceValue(players,
								current)) {
							if (cardCurrent.getColorValue() < lowest.getColorValue()) {
								lowest = cardCurrent;
							}
						}
					}
					Iterator<ICard> it3 = winner.getCapturedCards().iterator();
					ICard winnerLowest = null;
					while (it3.hasNext()) {
						ICard cardCurrent = it3.next();
						if (winnerLowest == null) {
							winnerLowest = cardCurrent;
						} else if (cardCurrent.endFaceValue(players, winner) < winnerLowest.endFaceValue(players,
								winner)) {
							winnerLowest = cardCurrent;
						} else if (cardCurrent.endFaceValue(players, winner) == winnerLowest.endFaceValue(players,
								winner)) {
							if (cardCurrent.getColorValue() < winnerLowest.getColorValue()) {
								winnerLowest = cardCurrent;
							}
						}
					}
					if (lowest.endFaceValue(players, current) < winnerLowest.endFaceValue(players, winner)) {
						winner = current;
						winnerDelta = currentDelta;
					} else if (lowest.endFaceValue(players, current) == winnerLowest.endFaceValue(players, winner)) {
						if (lowest.getColorValue() < winnerLowest.getColorValue()) {
							winner = current;
							winnerDelta = currentDelta;
						}
					}
				}
			}
		}
		return winner;
	}

	/**
	 * recuper le nombre de cartes distribuee au joueur.
	 */
	@Override
	public int getPlayerCardNb() {
		return 2;
	}

	/**
	 * @param nbPlayers recuperer le nombre de carte trophee de jouer.
	 */
	@Override
	public int getTrophyCardNb(int nbPlayers) {
		if (nbPlayers == 3) {
			return 2;
		} else {
			return 1;
		}
	}

}
