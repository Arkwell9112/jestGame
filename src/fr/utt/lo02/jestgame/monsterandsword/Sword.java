package fr.utt.lo02.jestgame.monsterandsword;

import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

public class Sword extends Card {

	public Sword(ImageIcon texture, ITrophyChooser chooser) {
		super("Sword", "MonsterAndSword", 25, texture, chooser);
	}

	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		return 2;
	}

	@Override
	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 2;
	}

}
