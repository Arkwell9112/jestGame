package fr.utt.lo02.jestgame.core;

import java.util.List;

public abstract class BotPlayer extends Player{
	
	/**
	 * Constructeur de la classe.
	 * @param name Nom du joueur.
	 * @param interfac Controller lié au joueur.
	 */
	public BotPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}
	
	/**
	 * @param players players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie l'index de la carte choisie pour être placée en face visible.
	 */
	protected abstract int chooseFaceUp(List<Player> players);
	
	/**
	 * Méthode permettant d'envoyer le contexte au ConcreteBotPlayer.
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie en 1 le joueur qui capture et en 2 le joueur capturé. 
	 */
	protected abstract Object[] chooseCatchUp(List<Player> players);

	@Override
	public void yourTurnFaceUp(List<Player> players) {
		int choosed = chooseFaceUp(players);
		Object[] arg = {this, choosed};
		setFacedUp(choosed);
		notifyAll(NotEvent.FACE_UP_MENU_BOT, arg);
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
