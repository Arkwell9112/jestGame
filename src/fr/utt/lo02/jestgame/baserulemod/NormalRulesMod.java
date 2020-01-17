package fr.utt.lo02.jestgame.baserulemod;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

/**
 * @author Edouard
 * Classe efectuant l'implémentation de IMod.
 */
public class NormalRulesMod implements IMod{

	@Override
	public String getName() {
		return "Mode de jeu normal";
	}

	@Override
	public String getDescription() {
		return "Mod permettant de jouer avec les régles classiques du Jest";
	}

	@Override
	public ModType getType() {
		return ModType.RULES;
	}

	@Override
	public Object[] getInstance() {
		Object[] arg = {new NormalPartyRules()};
		return arg;
	}

}
