package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.IPartyRules;

public class Party extends Observable {

	private List<Player> players;
	private IPartyRules rules;
	private int currentRank;
	private Pot pot;
	private DrawStream draw;

	public Party(List<IMod> newPlayers, List<IMod> cardMods, IMod ruleMode, IObserver interfac,
			List<String> playersName) {
		addObserver(interfac);
		currentRank = 0;

		players = new ArrayList<Player>(4);

		while (newPlayers.size() != 0) {
			byte rand = (byte) (Math.random() * newPlayers.size());
			if (newPlayers.get(rand) == null) {
				Player neo = new HumanPlayer(playersName.get(rand), interfac);
				neo.setCurrentParty(this);
				players.add(neo);
				newPlayers.remove(rand);
				playersName.remove(rand);
			} else {
				Player newer = (Player) newPlayers.get(rand).getInstance()[0];
				newer.setCurrentParty(this);
				players.add(newer);
				newer.setName(playersName.get(rand));
				newer.setInterface(interfac);
				newPlayers.remove(rand);
				playersName.remove(rand);
			}
		}

		rules = (IPartyRules) ruleMode.getInstance()[0];

		Iterator<IMod> it = cardMods.iterator();

		List<ICard> cards = new ArrayList<ICard>(100);

		while (it.hasNext()) {
			IMod next = it.next();
			Object[] instances = next.getInstance();
			for (Object obj : instances) {
				ICard current = (ICard) obj;
				cards.add(current);
			}
		}

		draw = new DrawStream(cards);

		List<ICard> trophies = new ArrayList<ICard>();

		for (int i = 1; i <= rules.getTrophyCardNb(players.size()); i++) {
			trophies.add(draw.getDraft());
		}

		pot = new Pot(trophies);

		Object[] arg = { pot };
		notifyAll(NotEvent.SHOW_TROPHY, arg);
	}

	private void endParty() {
		Iterator<ICard> it = pot.getTrophies().iterator();
		while (it.hasNext()) {
			ICard card = it.next();
			if (card.chooseTrophyOwner(players) != null) {
				card.chooseTrophyOwner(players).addCapturedCard(card);
			}
		}

		Player winner = rules.chooseWinner(players);
		Object[] arg = { winner };
		notifyAll(NotEvent.CURRENT_PLAYER, arg);
		notifyAll(NotEvent.END_PARTY_MENU, players.toArray(new Object[players.size()]));
	}

	private void beginCatchUp() {
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			it.next().setCatchedUp();
		}
		currentRank = 0;
		Player beginner = rules.chooseFirstCatch(players);
		beginner.yourTurnCatchUp(players);

	}

	public void endFaceUpTurn(Player player) {
		currentRank++;
		if (currentRank > players.size() - 1) {
			currentRank = 0;
		}
		if (currentRank == 0) {
			beginCatchUp();
		} else {
			players.get(currentRank).yourTurnFaceUp(players);
		}
	}

	public void endCatchUpTurn(Player player, Player nextPlayer) {
		currentRank++;
		if (currentRank < players.size()) {
			int pos = 0;
			for (int i = 0; i <= players.size() - 1; i++) {
				if(players.get(i) == nextPlayer) {
					pos = i;
				}
			}
			boolean founded = false;
			while(!founded) {
				pos++;
				if(!players.get(pos).isCatchedUp()) {
					nextPlayer = players.get(pos);
					founded = true;
				}
			}
			nextPlayer.yourTurnCatchUp(players);
		} else if (draw.getRemainingCards() >= players.size()) {
			beginFaceUp();
		} else {
			endParty();
		}
	}

	public void beginParty() {
		if (draw.getRemainingCards() >= rules.getPlayerCardNb() * players.size()) {
			List<ICard> cards = new ArrayList<ICard>();
			Iterator<Player> it = players.iterator();
			while (it.hasNext()) {
				for (int i = 1; i <= rules.getPlayerCardNb(); i++) {
					cards.add(draw.getDraft());
				}
				Player pl = it.next();
				List<ICard> handy = new ArrayList<ICard>();
				handy.addAll(cards);
				pl.setHand(handy);
				cards.clear();
			}
			beginFaceUp();
		} else {
			System.out.println("Impossible de continuer, pas suffisemment de cartes pour jouer");
		}
	}

	private void beginFaceUp() {
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			it.next().resetFacedUp();
		}

		if (players.get(0).isCatchedUp()) {
			Iterator<Player> it2 = players.iterator();
			while (it2.hasNext()) {
				it2.next().addHand(draw.getDraft());
			}
		}
		currentRank = 0;
		players.get(currentRank).yourTurnFaceUp(players);
	}

	@Override
	public void notifyBack(NotEvent backCallEvent, Object[] backArgs) {
		// TODO Auto-generated method stub

	}

}
