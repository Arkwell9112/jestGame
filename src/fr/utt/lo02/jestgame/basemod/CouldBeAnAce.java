package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class CouldBeAnAce extends Card implements ICard {

	public CouldBeAnAce(String name, String color, int colorValue, int baseValue) {
		super(name, color, colorValue, baseValue);
		
	}
	
	protected int getAceValue(List<Player> players,Player myPlayer) {
		
		
		
		Iterator<ICard> it=myPlayer.getCapturedCards().iterator();
		while(it.hasNext()) {
			ICard current=it.next();
			if(current.getColorValue()==5) {
				return current.getColorValue();
			}
		}
	
		
		
		
	}

	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		Player best=null;
		Iterator<Player> it=players.iterator();
		while(it.hasNext()) {
			Player current=it.next();
			if(best == null) {
				best=current;
			}
			
		}
	}
	
	@Override
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		int counter=0;
		Iterator<ICard> it=myPlayer.getCapturedCards().iterator();
		while(it.hasNext()) {
			ICard currentCard=it.next();
			if(currentCard.getColor()=="Ace") {
				counter ++;
				
			}
		}
		
		if(counter==1) {
			return 5;
		}
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
