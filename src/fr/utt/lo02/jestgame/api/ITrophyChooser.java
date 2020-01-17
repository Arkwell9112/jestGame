package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Interface permettant de manipuler plus facilement le choix des joueurs lors de la distribution des trophées.
 */
public interface ITrophyChooser {
	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @param card La carte qui doit choisir un joueur pour la recevoir en tant que trophée.
	 * @param trophyArg Un argument permettant de choisir certains paramètres lors du choix du joueur.
	 * @return Le joueur choisit pour recevoir la carte en tant que trophée.
	 */
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg);
}
