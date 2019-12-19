package fr.utt.lo02.jestgame.basemod.trohychooser;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.basemod.CouldBeAnAce;
import fr.utt.lo02.jestgame.core.Player;

public class Highest implements ITrophyChooser {

	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg) {
		String arg = (String) trophyArg;
		Iterator<Player> it = players.iterator();
		Player higher = null;
		int higherValue = 0;
		while (it.hasNext()) {
			Player current = it.next();
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			int currentValue = 0;
			while (it2.hasNext()) {
				ICard currentCard = it2.next();
				if (CouldBeAnAce.class.isAssignableFrom(currentCard.getClass())) {
					CouldBeAnAce currentCould = (CouldBeAnAce) currentCard;
					if (Math.abs(currentCould.getAceValue(players, current)) > currentValue
							&& currentCard.getColor() == arg) {
						currentValue = Math.abs(currentCould.getAceValue(players, current));
					}
				}
			}
			if (currentValue > higherValue) {
				higherValue = currentValue;
				higher = current;
			}
		}

		return higher;
	}

}
