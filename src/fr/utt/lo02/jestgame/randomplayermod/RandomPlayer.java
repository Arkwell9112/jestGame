package fr.utt.lo02.jestgame.randomplayermod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.core.BotPlayer;
import fr.utt.lo02.jestgame.core.IObserver;
import fr.utt.lo02.jestgame.core.Player;

/**
 * Cette classe herite le BotPlayer et permet de choisir le jouer de maniere aleatoire
 * @author akramsyukri
 *
 */
public class RandomPlayer extends BotPlayer {
	/**
	 * 
	 * @param name nom du joueur
	 * @param interfac controlleur liee a ce joueur
	 */
	public RandomPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}

	/**
	 * @param players Liste de toutes les instances de Player de la partie qui va choisir la carte a mettre visible
	 */
	@Override
	protected int chooseFaceUp(List<Player> players) {
		return (int) (Math.random() * getHand().size());
	}

	/**
	 * @param players Liste de toutes les instances de Player de la partie et qui va choisir l'autre joueur pour prendre la carte
	 */
	@Override
	protected Object[] chooseCatchUp(List<Player> players) {
		int choosed = (int) (Math.random() * players.size());
		boolean lastOne = false;
		int counter = 0;
		Iterator<Player> it = players.iterator();
		while(it.hasNext()) {
			Player current = it.next();
			if(current.isCatchedUp()) {
				counter++;
			}
		}
		if(counter == players.size() - 1 && !this.isCatchedUp()) {
			lastOne = true;
		}
		while (true) {
			if (!players.get(choosed).isCatchedUp() && players.get(choosed) != this) {
				boolean tf = false;
				if ((int) (Math.random() * 2) == 1) {
					tf = true;
				}
				Object[] arg = { this, players.get(choosed), tf};
				return arg;
			} else {
				if(lastOne) {
					int rand = (int) Math.random() * 2;
					boolean tf = true;
					if(rand == 0) {
						tf = false;
					}
					Object[] arg = {this, this, tf};
					return arg;
				}
				choosed++;
				if (choosed == players.size()) {
					choosed = 0;
				}
			}
		}
	}

}
