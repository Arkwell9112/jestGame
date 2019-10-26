package jestGame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Observable {
	
	private List<IObserver> observers;
	
	public Observable() {
		observers = new ArrayList<IObserver>();
	}
	
	public void addObserver(IObserver observer) {
		observers.add(observer);
	}
	
	public void notifyAll(NotEvent callEvent, Object[] args) {
		Iterator<IObserver> it = observers.iterator();
		
		while(it.hasNext()) {
			it.next().update(this, callEvent, args);
		}
	}
	
	public abstract void notifyBack(NotEvent backCallEvent, Object[] backArgs);
}
