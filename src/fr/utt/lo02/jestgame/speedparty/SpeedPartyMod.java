package fr.utt.lo02.jestgame.speedparty;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

public class SpeedPartyMod implements IMod {

	@Override
	public String getName() {
		return "Mode de jeu rapide";
	}

	@Override
	public String getDescription() {
		return "Mod permettant de joueur au Jest en partie rapide, 3 cartes par joueur et toujours 2 trophées";
	}

	@Override
	public ModType getType() {
		return ModType.RULES;
	}

	@Override
	public Object[] getInstance() {
		Object[] back = { new SpeedParty() };
		return back;
	}

}
