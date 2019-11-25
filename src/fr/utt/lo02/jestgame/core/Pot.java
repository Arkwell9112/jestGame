package fr.utt.lo02.jestgame.core;

import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

public class Pot {
	
	private List<ICard> trophies;
	
	public Pot(List<ICard> newTrophies) {
		trophies = newTrophies;
	}
	
	public List<ICard> getTrophies(){
		return trophies;
	}
}
