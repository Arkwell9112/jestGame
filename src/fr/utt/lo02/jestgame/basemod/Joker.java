package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class Joker extends Card implements ICard {

	public Joker(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);

	}

	public Player chooseTrophyOwner(List<Player> players) {
		Iterator<Player> it = players.iterator();
		Player best = null;
		while (it.hasNext()) {
			Player current = it.next();
			if (best == null) {
				best = current;
			} else if (best.calculateScore(players) < current.calculateScore(players)) {
				best = current;
			}
		}
		return best;
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

	public int endFaceValue(List<Player> players) {
		return 0;

	}
}
