package fr.utt.lo02.jestgame.randomplayermod;

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
	//Ajouter dernier recour
	protected Object[] chooseCatchUp(List<Player> players) {
		int choosed = (int) (Math.random() * players.size());
		while (true) {
			if (!players.get(choosed).isCatchedUp()) {
				boolean tf = false;
				if ((int) (Math.random() * 2) == 1) {
					tf = true;
				}
				Object[] arg = { this, players.get(choosed), tf};
				return arg;
			} else {
				choosed++;
				if (choosed > players.size()) {
					choosed = 0;
				}
			}
		}
	}

}
