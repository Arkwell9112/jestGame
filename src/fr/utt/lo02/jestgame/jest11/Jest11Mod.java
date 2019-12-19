package fr.utt.lo02.jestgame.jest11;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

public class Jest11Mod implements IMod {

	@Override
	public String getName() {
		return "Jest11";
	}

	@Override
	public String getDescription() {
		return "Ce n'est plus le meilleur qui gagne mais celui qui est le plus prêt de 11, en cas d'égalité c'est le joueur avec le moins de point puis la moins bonne valeur faciale et enfin la moins bonne couleur qui gagne";
	}

	@Override
	public ModType getType() {
		return ModType.RULES;
	}

	@Override
	public Object[] getInstance() {
		Object[] back = { new Jest11() };
		return back;
	}

}
