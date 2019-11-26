package fr.utt.lo02.jestgame.basemod;

import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class Diamonds extends CouldBeAnAce {

	private TrophyType myType;
	private int powerTrophy;
	
	public Diamonds(String name, int baseValue, TrophyType type, int trophyPower) {
		super(name, "Diamond", 20, baseValue);
		this.myType = type;
		this.powerTrophy = trophyPower;
	}
	
	public Player chooseTrophyOwner(List<Player> players) {
		return null;
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

	


