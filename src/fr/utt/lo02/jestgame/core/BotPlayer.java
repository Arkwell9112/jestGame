package fr.utt.lo02.jestgame.core;

import java.util.List;

public abstract class BotPlayer extends Player{
	
	public BotPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}
	
	protected abstract int chooseFaceUp(List<Player> players);
	
	protected abstract void chooseCatchUp(List<Player> players);

	@Override
	public void yourTurnFaceUp(List<Player> players) {
		int choosed = chooseFaceUp(players);
		Object[] arg = {this, choosed};
		notifyAll(NotEvent.FACE_UP_MENU_BOT, arg);
		setFacedUp(choosed);
	}

	@Override
	public void yourTurnCatchUp(List<Player> players) {
		
		
	}

	@Override
	public void notifyBack(NotEvent backCallEvent, Object[] backArgs) {
		// TODO Auto-generated method stub
		
	}
}
