package fr.utt.lo02.jestgame.intelliplayermod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.BotPlayer;
import fr.utt.lo02.jestgame.core.IObserver;
import fr.utt.lo02.jestgame.core.Player;

/**
 * cette classe herite la classe BotPlayer et qui va essayer de choisir la meilleur carte en tant que joueur virtuel
 * @author akramsyukri
 *
 */
public class IntelliPlayer extends BotPlayer {

	/**
	 * c'est le constructeur de la classe
	 * @param name Nom de joueur virtuel
	 * @param interfac Controlleur liee a ce jouer
	 */
	public IntelliPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}

	/**
	 * cette methode est pour choisir parmi les jouers qui va choisir la carte en premier
	 */
	@Override
	protected int chooseFaceUp(List<Player> players) {
		ICard best = null;
		Iterator<ICard> it = this.getHand().iterator();
		while (it.hasNext()) {
			ICard current = it.next();
			if (best == null) {
				best = current;
			} else if (current.getUpdatedGameFaceValue(players) > best.getUpdatedGameFaceValue(players)) {
				best = current;
			} else if (current.getUpdatedGameFaceValue(players) == best.getUpdatedGameFaceValue(players)) {
				if (current.getColorValue() > best.getColorValue()) {
					best = current;
				}
			}
		}
		return this.getHand().indexOf(best);
	}

	/**
	 * cette methode est pour un joueur de choisir une carte de l'autre joueur
	 */
	@Override
	protected Object[] chooseCatchUp(List<Player> players) {
		Player best = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			if (current.getFacedUpCard() != null && !current.isCatchedUp() && current != this) {
				if (best == null) {
					best = current;
				} else if (current.getFacedUpCard().getUpdatedGameFaceValue(players) > best.getFacedUpCard()
						.getUpdatedGameFaceValue(players)) {
					best = current;
				} else if (current.getFacedUpCard().getUpdatedGameFaceValue(players) == best.getFacedUpCard()
						.getUpdatedGameFaceValue(players)) {
					if (current.getFacedUpCard().getColorValue() > best.getFacedUpCard().getColorValue()) {
						best = current;
					}
				}
			}
		}
		if (best == null) {
			best = this;
		}
		Object[] back = { this, best, true };
		return back;
	}
}
