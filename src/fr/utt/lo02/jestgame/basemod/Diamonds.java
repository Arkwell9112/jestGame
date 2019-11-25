package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class Diamonds extends CouldBeAnAce implements ICard{

	public Diamonds(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
		
	}
	
	public Player chooseTrophyOwner(List<Player> players) {
		Iterator<Player> it= players.iterator();
		
	}
	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;
	}
	public int endFaceValue(List<Player> players) {
		return 0;
	}
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		

		
		Iterator<ICard> it= myPlayer.getCapturedCards().iterator();
		while(it.hasNext()) {
			ICard currentCard = it.next();
			if(currentCard.getColorValue()>=2) {
				
				
			}
		}
		
		
	}

}

	


