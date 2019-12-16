package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

public class Joker extends Card {

	private TrophyType myType;
	private Object trophyArg;

	public Joker(TrophyType type, Object trophyArg, ImageIcon texture) {
		super("Joker", "Joker", 0, 0, texture);
		this.myType = type;
		this.trophyArg = trophyArg;
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		int counter = 0;
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		while (it.hasNext()) {
			ICard currentCard = it.next();
			if (currentCard.getColor() == "Heart") {
				counter++;
			}
		}

		if (counter == 0) {
			return 4;
		}

		return 0;
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;

	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 0;

	}
}
