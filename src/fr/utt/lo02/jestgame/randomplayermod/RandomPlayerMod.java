package fr.utt.lo02.jestgame.randomplayermod;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

/**
 * cette classe implemente interface IMod et permet d'instancier RandomPlayerMod
 * @author akramsyukri
 *
 */
public class RandomPlayerMod implements IMod{

	/**
	 * recuperer la nom de Mod
	 */
	@Override
	public String getName() {
		return "RandomPlayer";
	}

	/**
	 * recuperer la description de Mod
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Ce mod permet d'ajouter une IA de jeu aleatoire";
	}

	/**
	 * recuperer la srategie choisie par l'utilisateur
	 */
	@Override
	public ModType getType() {
		return ModType.STRATEGY;
	}

	/**
	 * pour instancier RandomPlayer
	 */
	@Override
	public Object[] getInstance() {
		Object[] arg = {new RandomPlayer(null, null)};
		return arg;
	}

}
