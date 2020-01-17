package fr.utt.lo02.jestgame.core;

/**
 * Interface du patron observeur observable.
 * @author Edouard
 *
 */
public interface IObserver {
	/**
	 * Méthode permettant la notification d'un observeur par un observable.
	 * @param observed L'object notifiant cette observeur.
	 * @param callEvent L'event d'appel à l'observeur.
	 * @param args Les arguments à passer à l'observeur.
	 */
	public void update(Observable observed, NotEvent callEvent, Object[] args);
}
