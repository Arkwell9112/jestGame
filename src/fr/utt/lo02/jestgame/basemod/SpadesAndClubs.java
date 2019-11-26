package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

public class SpadesAndClubs extends CouldBeAnAce implements ICard {

	private TrophyType myType;
	private Object trophyArg;
	
	public SpadesAndClubs(String name, String color, int colorValue, int baseValue, TrophyType type, Object trophyArg) {
		super(name, color, colorValue, baseValue);
		this.myType = type;
		this.trophyArg = trophyArg;
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		boolean found = false;
		while (it.hasNext()) {
			ICard current = it.next();
			if ((current.getName() == "Spade" || current.getName() == "Club")
					&& (current.endFaceValue(players, myPlayer)) == endFaceValue(players, myPlayer)) {
				found = true;
			}
		}

		if (found) {
			return 2;
		} else {
			return 0;
		}
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return getBaseValue();
	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		return getAceValue(players, myPlayer);
	}

}
