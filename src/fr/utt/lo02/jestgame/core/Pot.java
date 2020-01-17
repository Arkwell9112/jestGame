package fr.utt.lo02.jestgame.core;

import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

/**
 * @author Edouard
 * Classe permettant le stockage des trophées pendant la partie.
 */
public class Pot {
	
	/**
	 * Liste contenant les trophées.
	 */
	private List<ICard> trophies;
	
	/**
	 * @param newTrophies Les trophées à mettre dans le Pot.
	 */
	public Pot(List<ICard> newTrophies) {
		trophies = newTrophies;
	}
	
	/**
	 * @return Renvoit les trophées stockés dans le Pot.
	 */
	public List<ICard> getTrophies(){
		return trophies;
	}
}
