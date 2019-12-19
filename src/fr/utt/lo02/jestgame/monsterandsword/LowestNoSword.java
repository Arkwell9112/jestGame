package fr.utt.lo02.jestgame.monsterandsword;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

public class LowestNoSword implements ITrophyChooser {

	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg) {
		Player lowest = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			boolean found = false;
			while (it2.hasNext()) {
				ICard cardCurrent = it2.next();
				if (cardCurrent.getName() == "Sword") {
					found = true;
				}
			}
			if (!found) {
				if (lowest == null) {
					lowest = current;
				} else if (current.calculateScore(players) < lowest.calculateScore(players)) {
					lowest = current;
				} else if (current.calculateScore(players) == lowest.calculateScore(players)) {
					Iterator<ICard> it3 = lowest.getCapturedCards().iterator();
					ICard lowestCard = null;
					while(it3.hasNext()) {
						ICard cardCurrent = it3.next();
						if(lowestCard == null) {
							lowestCard = cardCurrent;
						} else if(cardCurrent.getUpdatedGameFaceValue(players) < lowestCard.getUpdatedGameFaceValue(players)) {
							lowestCard = cardCurrent;
						} else if(cardCurrent.getUpdatedGameFaceValue(players) == lowestCard.getUpdatedGameFaceValue(players)) {
							if(cardCurrent.getColorValue() < lowestCard.getColorValue()) {
								lowestCard = cardCurrent;
							}
						}
					}
					Iterator<ICard> it4 = current.getCapturedCards().iterator();
					ICard currentCard = null;
					while(it4.hasNext()) {
						ICard cardCurrent = it4.next();
						if(currentCard == null) {
							currentCard = cardCurrent;
						} else if(cardCurrent.getUpdatedGameFaceValue(players) < currentCard.getUpdatedGameFaceValue(players)) {
							currentCard = cardCurrent;
						} else if(cardCurrent.getUpdatedGameFaceValue(players) == currentCard.getUpdatedGameFaceValue(players)) {
							if(cardCurrent.getColorValue() < currentCard.getColorValue()) {
								currentCard = cardCurrent;
							}
						}
					}
					if(currentCard.getUpdatedGameFaceValue(players) < lowestCard.getUpdatedGameFaceValue(players)) {
						lowest = current;
					} else if(currentCard.getUpdatedGameFaceValue(players) == lowestCard.getUpdatedGameFaceValue(players)) {
						if(currentCard.getColorValue() < lowestCard.getColorValue()) {
							lowest = current;
						}
					}
				}
			}
		}
		return lowest;
	}

}
