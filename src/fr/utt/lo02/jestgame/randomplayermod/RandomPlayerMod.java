package fr.utt.lo02.jestgame.randomplayermod;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

public class RandomPlayerMod implements IMod{

	@Override
	public String getName() {
		return "IA de jeu aléatoire";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Ce mod permet d'ajouter une IA de jeu aléatoire";
	}

	@Override
	public ModType getType() {
		return ModType.STRATEGY;
	}

	@Override
	public Object[] getInstance() {
		Object[] arg = {new RandomPlayer(null, null)};
		return arg;
	}

}
