package fr.utt.lo02.jestgame.basemod;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;

public class NormalCards implements IMod {

	@Override
	public String getName() {
		return "Permet l'ajout de toutes les cartes de bases";
	}

	@Override
	public String getDescription() {
		return "Ajoute toutes les cartes classiques du Jest";
	}

	@Override
	public ModType getType() {
		return ModType.CARDS;
	}

	@Override
	public Object[] getInstance() {
		Object [] arg = new Object[17];
		arg[0] = new Joker(TrophyType.BEST, null);
		arg[1] = new Hearts("Ace", 1, TrophyType.WITHJ, null);
		arg[2] = new Hearts("Two", 2, TrophyType.WITHJ, null);
		arg[3] = new Hearts("Three", 3, TrophyType.WITHJ, null);
		arg[4] = new Hearts("Four", 4, TrophyType.WITHJ, null);
		arg[5] = new Diamonds("Ace", 1, TrophyType.MAJORITY, 4);
		arg[6] = new Diamonds("Two", 2, TrophyType.HIGHEST, "Diamond");
		arg[7] = new Diamonds("Three", 3, TrophyType.LOWEST, "Diamond");
		arg[8] = new Diamonds("Four", 4, TrophyType.BESTNJ, null);
		arg[9] = new SpadesAndClubs("Ace", "Club", 30, 1, TrophyType.HIGHEST, "Spade");
		arg[10] = new SpadesAndClubs("Two", "Club", 30, 2, TrophyType.LOWEST, "Heart");
		arg[11] = new SpadesAndClubs("Three", "Club", 30, 3, TrophyType.HIGHEST, "Heart");
		arg[12] = new SpadesAndClubs("Four", "Club", 30, 4, TrophyType.LOWEST, "Spade");
		arg[13] = new SpadesAndClubs("Ace", "Spade", 40, 1, TrophyType.HIGHEST, "Club");
		arg[14] = new SpadesAndClubs("Two", "Spade", 40, 2, TrophyType.MAJORITY, 3);
		arg[15] = new SpadesAndClubs("Three", "Spade", 40, 3, TrophyType.MAJORITY, 2);
		arg[16] = new SpadesAndClubs("Four", "Spade", 40, 4, TrophyType.LOWEST, "Club");
		return arg;
	}

}
