package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class SpadesAndClubs extends CouldBeAnAce implements ICard{

	public SpadesAndClubs(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
		
	}
	
	public Player chooseTrophyOwner(List<Player> players) {
		Iterator<Player> it=players.iterator();
		Player highest=null;
		Player lowest=null;
		while(it.hasNext()) {
			Player current=it.next();
			if()
		}
		
	}
	
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		int bonus=0;
		Iterator<ICard> it= myPlayer.getCapturedCards().iterator();
		while(it.hasNext()) {
			ICard currentCard=it.next();
			ICard afterCard=it.next();
			if(currentCard.getColor()=="Spades" && afterCard.getColor()=="Clubs") {
				if(currentCard.getColorValue()==afterCard.getColorValue()) {
					bonus=currentCard.getColorValue()+afterCard.getColorValue()+2;
				}
			}
		}
		
		return bonus;
	}
	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;
	}
	public int endFaceValue(List<Player> players) {
		return 0;
	}
	
	
	

}
