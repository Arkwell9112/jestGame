package fr.utt.lo02.jestgame.speedparty;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

/**
 * cette classe implemente IMod et elle va instancier SpeedParty
 * @author akramsyukri
 *
 */
public class SpeedPartyMod implements IMod {

	/**
	 * recuperer le nom de mode
	 */
	@Override
	public String getName() {
		return "Mode de jeu rapide";
	}
	
	/**
	 * recuperer la description de mode
	 */
	@Override
	public String getDescription() {
		return "Mod permettant de joueur au Jest en partie rapide, 3 cartes par joueur et toujours 2 troph�es";
	}
	/**
	 * recuperer les regles de Mod choisis par l'utilisateur
	 */
	@Override
	public ModType getType() {
		return ModType.RULES;
	}

	/**
	 * instancier SpeedParty
	 */
	@Override
	public Object[] getInstance() {
		Object[] back = { new SpeedParty() };
		return back;
	}

}
