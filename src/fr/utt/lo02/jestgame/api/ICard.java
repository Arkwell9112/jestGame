package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Cette classe est l'interface permettant de manipuler les cartes du jeu de Jest.
 */
public interface ICard {
	
	/**
	 * @param players Tous les joueurs de la partie.
	 * @return Renvoie le joueur qui est choisit par la carte pour la recevoir comme trophé.
	 */
	public Player chooseTrophyOwner(List<Player> players);
	/**
	 * @param players Tous les joueurs de la partie.
	 * @return Renvoie la valeur faciale courante (mise à jour en fonction de la partie).
	 */
	public int getUpdatedGameFaceValue(List<Player> players);
	/**
	 * @param players Tous les joueurs de la partie.
	 * @param myPlayer Le joueur qui posséde 
	 * @return Renvoie la valeur faciale de fin de jeu (mise à jour en fonction de l'état de fin de la partie).
	 */
	public int endFaceValue(List<Player> players, Player myPlayer);
	/**
	 * @param players Tous les joueurs de la partie.
	 * @param myPlayer Le joueur qui posséde actuellement la carte.
	 * @return Renvoie la valeur de bonus de fin de la partie, par exemple la valeur bonus pour les paires noir ou pour le Joker sans coeurs.
	 */
	public int endSpecialFaceValue(List<Player> players, Player myPlayer);
	/**
	 * @return Renvoie le nom de la carte.
	 */
	public String getName();
	/**
	 * @return Renvoie le nom de la couleur de la carte.
	 */
	public String getColor();
	/**
	 * @return Renvoie la valeur de la couleur de la carte.
	 */
	public int getColorValue();

}
