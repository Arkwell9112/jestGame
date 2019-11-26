package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class Joker extends Card {

	public Joker() {
		super("Joker", "Joker", 0, 0);
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return null;
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		int counter = 0;
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		while (it.hasNext()) {
			ICard currentCard = it.next();
			if (currentCard.getColor() == "Heart") {
				counter++;
			}
		}

		if (counter == 0) {
			return 4;
		}

		return 0;
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;

	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 0;

	}
}
