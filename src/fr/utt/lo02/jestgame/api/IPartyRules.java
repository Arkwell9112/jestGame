package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

public interface IPartyRules {
	
	public Player chooseFirstCatch(List<Player> players);
	public Player chooseWinner(List<Player> players);
	public int getPlayerCardNb();
	public int getTrophyCardNb(int nbPlayers);
	
	
		
		
	

}
