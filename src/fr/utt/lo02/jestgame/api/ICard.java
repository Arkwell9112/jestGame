package fr.utt.lo02.jestgame.api;

import fr.utt.lo02.jestgame.core.Player;

public interface ICard {
	
	public Player chooseTrophyOwner(Player[] players);
	public int getUpdatedGameFaceValue(Player[] players);
	public int endFaceValue(Player[] players);
	public int endSpecialFaceValue(Player[] players);
	public String getName();
	public String getColor();
	public int getColorValue();

}
