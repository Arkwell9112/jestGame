package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

public interface ICard {
	
	public Player chooseTrophyOwner(List<Player> players);
	public int getUpdatedGameFaceValue(List<Player> players);
	public int endFaceValue(List<Player> players, Player myPlayer);
	public int endSpecialFaceValue(List<Player> players, Player myPlayer);
	public String getName();
	public String getColor();
	public int getColorValue();

}
