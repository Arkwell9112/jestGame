package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IMod;

public class Party {

	private List<Player> players;
	private IPartyRules rules;
	private int faceUpBeginnerRank;
	private IObserver interfac;
	private int catchUpBeginnerRank;
	private Pot pot;
	private DrawStream draw;

	public Party(List<IMod> newPlayers, List<IMod> cardMods, IMod ruleMode, IObserver interfac,
			List<String> playersName) {
		players = new ArrayList<Player>(4);

		while (newPlayers.size() != 0) {
			byte rand = (byte) (Math.random() * newPlayers.size());
			if (newPlayers.get(rand) == null) {
				players.add(new HumanPlayer(playersName.get(rand), interfac));
				newPlayers.remove(rand);
				playersName.remove(rand);
			} else {
				Player newer = (Player) newPlayers.get(rand).getInstance()[0];
				players.add(newer);
				newer.setName(playersName.get(rand));
				newer.setInterface(interfac);
				newPlayers.remove(rand);
				playersName.remove(rand);
			}
		}

		rules = (IPartyRules) ruleMode.getInstance()[0];

		this.interfac = interfac;
		
		Iterator<IMod> it = cardMods.iterator();
		
		List<ICard> cards = new ArrayList<ICard>(100);
		
		while(it.hasNext()) {
			IMod next = it.next();
			for(ICard card : (ICard[]) next.getInstance()) {
				cards.add(card);
			}
		}
		
		draw = new DrawStream(cards);

	}

	private void endParty() {

	}

	private void beginCatchUp() {

	}

	public void endFaceUpTurn(Player player) {

	}

	public void endCatchUpTurn(Player player) {

	}

	public void beginParty() {

	}

}
