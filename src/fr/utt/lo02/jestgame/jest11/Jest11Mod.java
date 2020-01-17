package fr.utt.lo02.jestgame.jest11;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;
/**
 * cette classe implementer l'interface IMod et elle permet d'instancier Jest11
 * @author akramsyukri
 *
 */
public class Jest11Mod implements IMod {

	/**
	 * recuperer le nom de mod
	 */
	@Override
	public String getName() {
		return "Jest11";
	}

	/**
	 * recuperer la description de mod
	 */
	@Override
	public String getDescription() {
		return "Ce n'est plus le meilleur qui gagne mais celui qui est le plus pret de 11, en cas d'egalite c'est le joueur avec le moins de point puis la moins bonne valeur faciale et enfin la moins bonne couleur qui gagne";
	}

	/**
	 * recuperer les regles choisi par l'utilisateur
	 */
	@Override
	public ModType getType() {
		return ModType.RULES;
	}

	/**
	 * instancier l'objet J11
	 */
	@Override
	public Object[] getInstance() {
		Object[] back = { new Jest11() };
		return back;
	}

}
