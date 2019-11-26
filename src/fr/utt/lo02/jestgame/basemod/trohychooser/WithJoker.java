package fr.utt.lo02.jestgame.basemod.trohychooser;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class WithJoker implements ITrophyChooser{

	@Override
	public Player delegateTrophyChoose(List<Player> players, ICard card) {
		Iterator<Player> it = players.iterator();
		while(it.hasNext()) {
			Player current = it.next();
			Iterator<ICard> it2 = current.getCapturedCards().iterator();
			while(it2.hasNext()) {
				ICard currentCard = it2.next();
				if(currentCard.getName() == "Joker") {
					return current;
				}
			}
		}
		return null;
	}

}
