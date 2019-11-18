package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.jestgame.api.ICard;

public class Player {
	
	private List<ICard> hand = new ArrayList<ICard>();
	private List<ICard> capturedCards = new ArrayList<ICard>();
	private String name;
	private int score;
	private int rank;
	private boolean isFacedUp;
	private boolean isCatchedUp;
	private int facedUpRank;
	private IObserver interfac;
	
	
	public Player(String name, int rank, IObserver interfac) {
		this.name=name;
		this.rank=rank;
		this.interfac=interfac;
		
	}
	
	
	
}
