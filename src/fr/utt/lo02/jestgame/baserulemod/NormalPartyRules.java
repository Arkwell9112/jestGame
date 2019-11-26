package fr.utt.lo02.jestgame.baserulemod;

import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.IPartyRules;
import fr.utt.lo02.jestgame.core.Player;

public class NormalPartyRules implements IPartyRules {

	@Override
	public Player chooseFirstCatch(List<Player> players) {
		Player best = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			if (best == null) {
				best = current;
			} else if (best.getFacedUpCard().getUpdatedGameFaceValue(players) < current.getFacedUpCard()
					.getUpdatedGameFaceValue(players)) {
				best = current;
			}
		}
		return best;
	}

	@Override
	public Player chooseWinner(List<Player> players) {
		Player best = null;
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			if (best == null) {
				best = current;
			} else if (best.calculateScore(players) < current.calculateScore(players)) {
				best = current;
			}
		}
		return best;
	}

	@Override
	public int getPlayerCardNb() {
		return 2;
	}

	@Override
	public int getTrophyCardNb(int nbPlayer) {
		if (nbPlayer == 3) {
			return 2;
		} else {
			return 1;
		}
	}

}
