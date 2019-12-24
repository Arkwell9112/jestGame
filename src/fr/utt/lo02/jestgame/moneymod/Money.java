package fr.utt.lo02.jestgame.moneymod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;
/**
 * 
 * @author akramsyukri
 *
 */
public class Money implements ITrophyChooser {

	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg) {
		Player money = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			boolean found = false;
			while (it2.hasNext()) {
				ICard cardCurrent = it2.next();
				if (cardCurrent.getName() == "MoneyPile") {
					found = true;
				}
			}
			if (!found) {
				if (money == null) {
					money = current;
				} else if (current.calculateScore(players) < money.calculateScore(players)) {
					money = current;
				} else if (current.calculateScore(players) == money.calculateScore(players)) {
					Iterator<ICard> it3 = money.getCapturedCards().iterator();
					ICard moneyCard = null;
					while(it3.hasNext()) {
						ICard cardCurrent = it3.next();
						if(moneyCard == null) {
							moneyCard = cardCurrent;
						} else if(cardCurrent.getUpdatedGameFaceValue(players) < moneyCard.getUpdatedGameFaceValue(players)) {
							moneyCard = cardCurrent;
						} else if(cardCurrent.getUpdatedGameFaceValue(players) == moneyCard.getUpdatedGameFaceValue(players)) {
							if(cardCurrent.getColorValue() < moneyCard.getColorValue()) {
								moneyCard = cardCurrent;
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
					if(currentCard.getUpdatedGameFaceValue(players) < moneyCard.getUpdatedGameFaceValue(players)) {
						money = current;
					} else if(currentCard.getUpdatedGameFaceValue(players) == moneyCard.getUpdatedGameFaceValue(players)) {
						if(currentCard.getColorValue() < moneyCard.getColorValue()) {
							money = current;
						}
					}
				}
			}
		}
		return money;
	}
	

}
