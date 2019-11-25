package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class Hearts extends CouldBeAnAce {

	public Hearts(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
		
	}
	
	public Player chooseTrophyOwner(List<Player> players) {
		Iterator <Player> it= players.iterator();
		Player withJoker=null;
		while(it.hasNext()) {
			Player current= it.next();
			if(withJoker==null) {
				withJoker=current;
			}
		}
		return withJoker;
	}
	
	public int endFaceValue(List<Player> players) {
		return 0;
	}
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		int counter=0;
		Iterator<ICard> it= myPlayer.getCapturedCards().iterator();
		while(it.hasNext()) {
			ICard currentCard = it.next();
			if(currentCard.getColor()=="Heart") {
				counter++;
			}
			
			if(counter == 0 && currentCard.getColor()=="Joker") {
				return 4;
			}
		}
		
		return 0;
	}
	
	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;
	}
	

}
