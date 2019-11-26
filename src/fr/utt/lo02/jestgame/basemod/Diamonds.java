package fr.utt.lo02.jestgame.basemod;

import java.util.List;

import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

public class Diamonds extends CouldBeAnAce {

	private TrophyType myType;
	private Object trophyArg;
	
	public Diamonds(String name, int baseValue, TrophyType type, Object trophyArg) {
		super(name, "Diamond", 20, baseValue);
		this.myType = type;
		this.trophyArg = trophyArg;
	}
	
	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
	}
	public int getUpdatedGameFaceValue(List<Player> players) {
		return getBaseValue();
	}
	public int endFaceValue(List<Player> players, Player myPlayer) {
		return -getAceValue(players, myPlayer);
	}
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		return 0;
			
	}

}

	


