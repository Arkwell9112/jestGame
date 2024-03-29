package fr.utt.lo02.jestgame.core;

import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

/**
 * Concrétisation de Player pour les joueurs humains.
 * @author Edouard
 *
 */
public class HumanPlayer extends Player {

	/**
	 * Constructeur de la classe.
	 * @param name Nom du joueur.
	 * @param interfac Controller lié à ce joueur.
	 */
	public HumanPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}

	public void notifyBack(NotEvent event, Object[] args) {
		if (event == NotEvent.FACE_UP_MENU) {
			setFacedUp((byte) args[0]);
			getCurrentParty().endFaceUpTurn(this);
		} else if (event == NotEvent.CATCH_UP_MENU) {
			Player current = (Player) args[0];
			boolean face = (boolean) args[1];
			ICard catched = current.catchUp(face);
			if (catched != null) {
				addCapturedCard(catched);
				Object[] arg = {catched};
				notifyAll(NotEvent.CATCH_UP_MENU_SUCCESS, arg);
				getCurrentParty().endCatchUpTurn(this, current);
			} else {
				notifyAll(NotEvent.CATCH_UP_MENU_ERROR, null);
			}
		}
	}

	@Override
	public void yourTurnFaceUp(List<Player> players) {
		Object[] arg = {(Player) this, players};
		super.notifyAll(NotEvent.FACE_UP_MENU, arg);

	}

	@Override
	public void yourTurnCatchUp(List<Player> players) {
		setHasCatchedUp();
		Object[] arg = {this};
		super.notifyAll(NotEvent.CURRENT_PLAYER, arg);
		super.notifyAll(NotEvent.CATCH_UP_MENU, players.toArray(new Object[players.size()]));
	}
}
