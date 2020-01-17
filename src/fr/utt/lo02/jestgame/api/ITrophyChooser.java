package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

/**
 * Interface permettant de manipuler plus facilement le choix des joueurs lors de la distribution des trophees.
 * @author Edouard
 * 
 */
public interface ITrophyChooser {
	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @param card La carte qui doit choisir un joueur pour la recevoir en tant que trophee.
	 * @param trophyArg Un argument permettant de choisir certains parametres lors du choix du joueur.
	 * @return Le joueur choisit pour recevoir la carte en tant que trophee.
	 */
	public Player delegateTrophyChoose(List<Player> players, ICard card, Object trophyArg);
}
