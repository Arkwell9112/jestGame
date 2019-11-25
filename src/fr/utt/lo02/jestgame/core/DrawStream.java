package fr.utt.lo02.jestgame.core;

import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

public class DrawStream {
	private List<ICard> draft;
	
	public DrawStream(List<ICard> cards) {
		while(cards.size() != 0) {
			byte rand = (byte) (Math.random()*cards.size());
			draft.add(cards.get(rand));
			cards.remove(rand);
		}
	}
	
	public int getRemainingCards() {
		return draft.size();
	}
	
	public ICard getDraft() {
		ICard card = draft.get(0);
		draft.remove(0);
		return card;
	}
}
