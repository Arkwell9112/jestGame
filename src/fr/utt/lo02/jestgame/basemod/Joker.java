package fr.utt.lo02.jestgame.basemod;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

public class Joker extends Card {

	public Joker(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
		
	}
	
	public Player chooseTrophyOwner(List<Player> players) {
		
		return null;
	}
	
	public int endSpecialFaceValue(List<Player> players) {
		return 0;
		
	}
	
	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;
		
	}
	
	public int endFaceValue(List<Player> players) {
		return 0;
		
	}
	
	

}
