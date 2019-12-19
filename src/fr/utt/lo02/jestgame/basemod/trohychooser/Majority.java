package fr.utt.lo02.jestgame.basemod.trohychooser;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.basemod.CouldBeAnAce;
import fr.utt.lo02.jestgame.core.Player;

public class Majority implements ITrophyChooser {

	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg) {
		int arg = (int) trophyArg;
		Player major = null;
		int majority = 0;
		int color = 0;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			int currentMajority = 0;
			int currentColor = 0;
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			while (it2.hasNext()) {
				ICard currentCard = it2.next();
				if (CouldBeAnAce.class.isAssignableFrom(currentCard.getClass())) {
					CouldBeAnAce currentCould = (CouldBeAnAce) currentCard;
					if (Math.abs(currentCould.getAceValue(players, current)) == arg) {
						currentMajority++;
						if (currentCard.getColorValue() > currentColor) {
							currentColor = currentCard.getColorValue();
						}
					}
				}
			}

			if (currentMajority > majority) {
				majority = currentMajority;
				major = current;
				color = currentColor;
			} else if (currentMajority == majority) {
				if (currentColor > color) {
					majority = currentMajority;
					major = current;
					color = currentColor;
				}
			}
		}
		return major;
	}

}
