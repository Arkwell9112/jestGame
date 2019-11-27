package fr.utt.lo02.jestgame.core;

import java.util.List;

public abstract class BotPlayer extends Player{
	
	public BotPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}
	
	protected abstract int chooseFaceUp(List<Player> players);
	
	//Doit renvoyer 1:Le joueur qui capture 2: Le joueur capturé
	protected abstract Object[] chooseCatchUp(List<Player> players);

	@Override
	public void yourTurnFaceUp(List<Player> players) {
		int choosed = chooseFaceUp(players);
		Object[] arg = {this, choosed};
		notifyAll(NotEvent.FACE_UP_MENU_BOT, arg);
		setFacedUp(choosed);
	}

	@Override
	public void yourTurnCatchUp(List<Player> players) {
		setHasCatchedUp();
		Object[] args = chooseCatchUp(players);
		Player toCatch = (Player) args[1];
		addCapturedCard(toCatch.catchUp((boolean) args[2]));
		notifyAll(NotEvent.CATCH_UP_MENU_BOT, args);
		getCurrentParty().endCatchUpTurn(this, (Player) args[1]);
	}

	@Override
	public void notifyBack(NotEvent backCallEvent, Object[] backArgs) {
		if(backCallEvent == NotEvent.FACE_UP_MENU_BOT) {
			getCurrentParty().endFaceUpTurn(this);
		}
		
	}
}
