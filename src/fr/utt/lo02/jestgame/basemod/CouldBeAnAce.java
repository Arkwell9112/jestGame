package fr.utt.lo02.jestgame.basemod;

import fr.utt.lo02.jestgame.core.Player;

public class CouldBeAnAce extends Card {

	public CouldBeAnAce(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
		
	}
	
	protected int getAceValue(Player[] players) {
		
		return 1;
	}

}
