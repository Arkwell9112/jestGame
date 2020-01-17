package fr.utt.lo02.jestgame.monsterandsword;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

/**
 * cette classe implemente IMod et va determiner qui a la carte Monster et la carte Sword
 * @author akramsyukri
 *
 */
public class MonsterAndSwordMod implements IMod {

	/**
	 * getteur de nom de la carte
	 */
	@Override
	public String getName() {
		return "Monster & Sword";
	}

	/**
	 * getteur de description de la carte
	 */
	@Override
	public String getDescription() {
		return "Extensions de carte contenant la carte Monstre qui vaut 5 pendant le jeu sauf si l'�p�e est face visible, l'�p�e vaut toujours 2, le monstre vaut 3 en fin de jeu sauf si l'�p�e est aussi desle Jest de ce joueur auquel cas le monstre vaut 1";
	}

	/**
	 * getteur de type de carte de la carte
	 */
	@Override
	public ModType getType() {
		return ModType.CARDS;
	}

	/**
	 * instancier les objets LowestNoSword et WithMonster
	 */
	@Override
	public Object[] getInstance() {
		Object[] back = new Object[2];
		back[0] = new Monster(new ImageIcon("img\\monsterandsword\\monster.jpg"), new LowestNoSword());
		back[1] = new Sword(new ImageIcon("img\\monsterandsword\\sword.jpg"), new WithMonster());
		return back;
	}

}
