package fr.utt.lo02.jestgame.core;

public interface IPartyRules {
	
	public Player chooseFirstCatch(Player[] players);
	public Player chooseWinner(Player[] players);
	public int getPlayerCardNb();
	public int getTrophyCardNb();
	
	
		
		
	

}
