package jestGame.core;

public interface IObserver {
	public void update(Observable observed, NotEvent callEvent, Object[] args);
}
