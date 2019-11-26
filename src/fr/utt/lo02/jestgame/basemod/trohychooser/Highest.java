package fr.utt.lo02.jestgame.basemod.trohychooser;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class Highest implements ITrophyChooser {

	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card) {
		Iterator<Player> it = players.iterator();
		Player higher = null;
		int higherValue = 0;
		while (it.hasNext()) {
			Player current = it.next();
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			int currentValue = 0;
			while (it2.hasNext()) {
				ICard currentCard = it2.next();
				if (Math.abs(currentCard.endFaceValue(players, current)) > currentValue
						&& currentCard.getColor() == card.getColor()) {
					currentValue = Math.abs(currentCard.endFaceValue(players, current));
				}
			}
			if(currentValue > higherValue) {
				higherValue = currentValue;
				higher = current;
			}
		}
		
		return higher;
	}

}
