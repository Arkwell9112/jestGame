package fr.utt.lo02.jestgame.intelliplayermod;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

/**
 * cette classe implemente IMod et puis elle ca instancier IntelliPlayer
 * @author akramsyukri
 *
 */
public class IntelliPlayerMod implements IMod {

	/**
	 * getteur de nom du joueur virtuel
	 */
	@Override
	public String getName() {
		return "IntelliPlayer";
	}

	/**
	 * getteur de description de joueur virtuel
	 */
	@Override
	public String getDescription() {
		return "IA de joueur un petit peu plus intelligente qui essai de choisir ses coups au mieux";
	}

	/**
	 * getteur de type(strategie) choisi pour le joueur virtuel
	 */
	@Override
	public ModType getType() {
		return ModType.STRATEGY;
	}

	/**
	 * instancier le joueur virtuel 
	 */
	@Override
	public Object[] getInstance() {
		Object[] back = { new IntelliPlayer(null, null) };
		return back;
	}

}
