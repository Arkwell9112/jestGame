package fr.utt.lo02.jestgame.monsterandsword;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

public class MonsterAndSwordMod implements IMod {

	@Override
	public String getName() {
		return "Monster & Sword";
	}

	@Override
	public String getDescription() {
		return "Extensions de carte contenant la carte Monstre qui vaut 5 pendant le jeu sauf si l'épée est face visible, l'épée vaut toujours 2, le monstre vaut 3 en fin de jeu sauf si l'épée est aussi desle Jest de ce joueur auquel cas le monstre vaut 1";
	}

	@Override
	public ModType getType() {
		return ModType.CARDS;
	}

	@Override
	public Object[] getInstance() {
		Object[] back = new Object[2];
		back[0] = new Monster(new ImageIcon("img\\monsterandsword\\monster.jpg"), new LowestNoSword());
		back[1] = new Sword(new ImageIcon("img\\monsterandsword\\sword.jpg"), new WithMonster());
		return back;
	}

}
