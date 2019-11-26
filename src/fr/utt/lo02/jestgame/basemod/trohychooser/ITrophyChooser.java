package fr.utt.lo02.jestgame.basemod.trohychooser;

import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public interface ITrophyChooser {
	public Player delegateTrophyChoose(List<Player> players, ICard card);
}
