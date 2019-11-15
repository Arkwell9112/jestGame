package fr.utt.lo02.jestgame.core;

public interface IObserver {
	public void update(Observable observed, NotEvent callEvent, Object[] args);
}
