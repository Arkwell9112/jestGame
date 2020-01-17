package fr.utt.lo02.jestgame.basemod.trohychooser;

import fr.utt.lo02.jestgame.api.ITrophyChooser;

/**
 * @author Edouard
 * Enum instanciant les TrophyChoosers et permettant leur utilisation en tant que TrophyType.
 */
public enum TrophyType {
	HIGHEST(new Highest()),
	LOWEST(new Lowest()),
	BEST(new BestJest()),
	BESTNJ(new BestJestNoJoke()),
	WITHJ(new WithJoker()),
	MAJORITY(new Majority());
	
	private ITrophyChooser load;
	
	TrophyType(ITrophyChooser load){
		this.load = load;
	}
	
	public ITrophyChooser getChooser() {
		return load;
	}
}
