package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

public abstract class Player extends Observable {

	private List<ICard> hand = new ArrayList<ICard>();
	private List<ICard> capturedCards = new ArrayList<ICard>();
	private String name;
	private int score;
	private boolean isFacedUp;
	private boolean isCatchedUp;
	private boolean isHanded;
	private int facedUpRank;
	private Party currentParty;
	
	public List<ICard> getCapturedCards() {
		return capturedCards;
	}

	public String getName() {
		return name;
	}

	public List<ICard> getHand() {
		return hand;
	}

	public Player(String name, IObserver interfac) {
		this.name = name;
		if (interfac != null) {
			addObserver(interfac);
		}

		isFacedUp = false;
		isCatchedUp = false;
		isHanded = false;
		facedUpRank = 0;
	}

	public Party getCurrentParty() {
		return currentParty;
	}

	public void setCurrentParty(Party currentParty) {
		this.currentParty = currentParty;
	}

	public void setName(String name) {
		if (this.name == null) {
			this.name = name;
		}
	}

	public void setInterface(IObserver interfac) {
		addObserver(interfac);
	}

	public void setHand(List<ICard> cards) {
		if (isHanded == false) {
			hand = cards;
			isHanded = true;
		}
	}

	public void addHand(ICard card) {
		hand.add(card);
	}

	public void addCapturedCard(ICard card) {
		capturedCards.add(card);
	}

	public abstract void yourTurnFaceUp(List<Player> players);

	public abstract void yourTurnCatchUp(List<Player> players);

	public ICard getFacedUpCard() {
		if(!isCatchedUp) {
			return hand.get(facedUpRank);
		} else {
			return null;
		}
	}

	public boolean isFacedUp() {
		return isFacedUp;
	}

	public void setFacedUp(int facedUp) {
		isFacedUp = true;
		facedUpRank = facedUp;
	}

	public boolean isCatchedUp() {
		return isCatchedUp;
	}

	public void setCatchedUp() {
		isCatchedUp = true;
	}

	public ICard catchUp(boolean isCatchingFacedUpCard) {
		if (isCatchedUp == false) {
			if (isCatchingFacedUpCard) {
				ICard card = hand.get(facedUpRank);
				hand.remove(facedUpRank);
				return card;
			} else {
				int rand = (int) Math.random()*hand.size();
				ICard card = hand.get(rand);
				hand.remove(rand);
				return card;
			}
		}
		else {
			return null;
		}
	}
	
	public int calculateScore(List<Player> players) {
		int sum = 0;
		Iterator<ICard> it = capturedCards.iterator();
		while(it.hasNext()) {
			ICard next = it.next();
			sum = sum + next.endFaceValue(players, this) + next.endSpecialFaceValue(players, this);
		}
		
		score = sum;
		return score;
	}

}
