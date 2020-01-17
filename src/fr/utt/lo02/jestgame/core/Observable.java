package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe Observable du patron observeur/observable.
 * @author Edouard
 *
 */
public abstract class Observable {
	
	/**
	 * Liste des observeurs de cette observable.
	 */
	private List<IObserver> observers;
	
	public Observable() {
		observers = new ArrayList<IObserver>();
	}
	
	/**
	 * Méthode permettant l'ajout d'un observeur sur cette observable.
	 * @param observer L'observeur à ajouter.
	 */
	public void addObserver(IObserver observer) {
		observers.add(observer);
	}
	
	/**
	 * Méthode notifiant tous les observeurs sur cet objet.
	 * @param callEvent Event de notification.
	 * @param args Arguments à passer aux observeurs.
	 */
	public void notifyAll(NotEvent callEvent, Object[] args) {
		Iterator<IObserver> it = observers.iterator();
		
		while(it.hasNext()) {
			it.next().update(this, callEvent, args);
		}
	}
	
	/**
	 * Méthode permettant à l'observable de recevoir les notifications retours de l'observer.
	 * @param backCallEvent L'événement ayant causé la notification donc par extension cette notification de retour aussi.
	 * @param backArgs Arguments à repasser à l'observable.
	 */
	public abstract void notifyBack(NotEvent backCallEvent, Object[] backArgs);
}
