package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class CouldBeAnAce extends Card implements ICard {

	public CouldBeAnAce(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
		
	}
	
	protected int getAceValue(List<Player> players) {
		
		return 1;
		
		
		
	}

	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}


	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int endFaceValue(List<Player> players) {
		// TODO Auto-generated method stub
		return 0;
	}

	



}
