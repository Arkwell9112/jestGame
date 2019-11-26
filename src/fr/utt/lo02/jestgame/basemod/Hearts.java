package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class Hearts extends CouldBeAnAce {

	private TrophyType type;
	private int trophyPower;

	public Hearts(String name, int baseValue, TrophyType type, int trophyPower) {
		super(name, "Heart", 10, baseValue);
		this.type = type;
		this.trophyPower = trophyPower;
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return null;
	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		boolean jok = false;
		while (it.hasNext()) {
			if (it.next().getName() == "Joker") {
				jok = true;
			}
		}

		if (jok) {
			it = myPlayer.getCapturedCards().iterator();
			int counter = 0;
			while (it.hasNext()) {
				if (it.next().getColor() == "Heart") {
					counter++;
				}
			}
			if (counter <= 3) {
				return -getAceValue(players, myPlayer);
			} else {
				return getAceValue(players, myPlayer);
			}
		} else {
			return 0;
		}
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return getBaseValue();
	}

}
