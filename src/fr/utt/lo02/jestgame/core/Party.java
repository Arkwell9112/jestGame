package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.IPartyRules;

public class Party extends Observable{

	private List<Player> players;
	private IPartyRules rules;
	private int faceUpBeginnerRank;
	private int currentRank;
	private Pot pot;
	private DrawStream draw;

	public Party(List<IMod> newPlayers, List<IMod> cardMods, IMod ruleMode, IObserver interfac,
			List<String> playersName) {
		addObserver(interfac);
		currentRank = 0;
		faceUpBeginnerRank = 0;

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
			for (ICard card : (ICard[]) next.getInstance()) {
				cards.add(card);
			}
		}

		draw = new DrawStream(cards);

		List<ICard> trophies = new ArrayList<ICard>();

		for (int i = 0; i <= rules.getTrophyCardNb(); i++) {
			trophies.add(draw.getDraft());
		}

		pot = new Pot(trophies);

	}

	private void endParty() {
		Iterator<ICard> it = pot.getTrophies().iterator();
		while(it.hasNext()) {
			ICard card = it.next();
			card.chooseTrophyOwner(players).addCapturedCard(card);
		}
		notifyAll(NotEvent.END_PARTY_MENU, players.toArray(new Object[players.size()]));
	}

	private void beginCatchUp() {
		Player beginner = rules.chooseFirstCatch(players);
		beginner.yourTurnCatchUp(players);

	}

	public void endFaceUpTurn(Player player) {
		currentRank++;
		if (currentRank > players.size() - 1) {
			currentRank = 0;
		}
		if (currentRank == faceUpBeginnerRank) {
			beginCatchUp();
		} else {
			players.get(currentRank).yourTurnFaceUp(players);
		}
	}

	
	public void endCatchUpTurn(Player player, Player nextPlayer) {
		currentRank++;
		if(currentRank < players.size()) {
			nextPlayer.yourTurnCatchUp(players);
		} else if(draw.getRemainingCards() >= players.size()) {
			beginFaceUp();
		} else {
			endParty();
		}
	}

	public void beginParty() {
		if (draw.getRemainingCards() >= rules.getPlayerCardNb()) {
			List<ICard> cards = new ArrayList<ICard>();
			Iterator<Player> it = players.iterator();
			while (it.hasNext()) {
				for (int i = 0; i <= rules.getPlayerCardNb(); i++) {
					cards.add(draw.getDraft());
				}
				it.next().setHand(cards);
				cards.clear();
			}
			currentRank = faceUpBeginnerRank;
			beginFaceUp();
		}
		else {
			System.out.println("Impossible de continuer, pas suffisemment de cartes pour jouer");
		}
	}
	
	private void beginFaceUp() {
		players.get(currentRank).yourTurnFaceUp(players);
		faceUpBeginnerRank++;
		if(faceUpBeginnerRank > players.size() -1) {
			faceUpBeginnerRank = 0;
		}
	}

	@Override
	public void notifyBack(NotEvent backCallEvent, Object[] backArgs) {
		// TODO Auto-generated method stub
		
	}

}
