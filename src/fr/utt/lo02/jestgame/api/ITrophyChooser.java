package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

public interface ITrophyChooser {
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg);
}