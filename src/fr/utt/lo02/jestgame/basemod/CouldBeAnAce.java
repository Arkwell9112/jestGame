package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public abstract class CouldBeAnAce extends Card implements ICard {

	public CouldBeAnAce(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
	}

	protected int getAceValue(List<Player> players, Player myPlayer) {
		if (getName() == "Ace") {
			boolean alone = true;
			Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
			while (it.hasNext()) {
				ICard next = it.next();
				if (next.getColor() == this.getColor() && next != this) {
					alone = false;
				}
			}
			if (alone == true) {
				return 5;
			} else {
				return 1;
			}
		} else {
			return getBaseValue();
		}
	}
}
