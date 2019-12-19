package fr.utt.lo02.jestgame.intelliplayermod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.BotPlayer;
import fr.utt.lo02.jestgame.core.IObserver;
import fr.utt.lo02.jestgame.core.Player;

public class IntelliPlayer extends BotPlayer {

	public IntelliPlayer(String name, IObserver interfac) {
		super(name, interfac);
	}

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
