package fr.utt.lo02.jestgame.randomplayermod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.core.BotPlayer;
import fr.utt.lo02.jestgame.core.IObserver;
import fr.utt.lo02.jestgame.core.Player;

public class RandomPlayer extends BotPlayer {

	public RandomPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}

	@Override
	protected int chooseFaceUp(List<Player> players) {
		return (int) (Math.random() * getHand().size());
	}

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
