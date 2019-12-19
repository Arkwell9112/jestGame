package fr.utt.lo02.jestgame.monsterandsword;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

public class Monster extends Card {

	public Monster(ImageIcon texture, ITrophyChooser chooser) {
		super("Monster", "MonsterAndSword", 25, texture, chooser);
	}

	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		boolean found = false;
		Iterator<Player> it = players.iterator();
		while(it.hasNext()) {
			Player current = it.next();
			if(current.getFacedUpCard() != null) {
				if(current.getFacedUpCard().getName() == "Sword") {
					found = true;
				}
			}
		}
		if(found) {
			return 1;
		} else {
			return 5;
		}
	}

	@Override
	public int endFaceValue(List<Player> players, Player myPlayer) {
		boolean found = false;
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		while(it.hasNext()) {
			ICard current = it.next();
			if(current.getName() == "Sword") {
				found = true;
			}
		}
		if(found) {
			return 1;
		} else {
			return 3;
		}
	}

}
