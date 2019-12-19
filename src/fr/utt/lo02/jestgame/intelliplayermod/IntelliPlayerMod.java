package fr.utt.lo02.jestgame.intelliplayermod;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

public class IntelliPlayerMod implements IMod {

	@Override
	public String getName() {
		return "IntelliPlayer";
	}

	@Override
	public String getDescription() {
		return "IA de joueur un petit peu plus intelligente qui essai de choisir ses coups au mieux";
	}

	@Override
	public ModType getType() {
		return ModType.STRATEGY;
	}

	@Override
	public Object[] getInstance() {
		Object[] back = { new IntelliPlayer(null, null) };
		return back;
	}

}
