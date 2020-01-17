package fr.utt.lo02.jestgame.api;

import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.core.Player;

/**
 * Cette classe est l'interface permettant de manipuler les cartes du jeu de Jest.
 * @author Edouard
 * 
 *
 */
public interface ICard {

	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie le joueur qui est choisi par la carte pour la recevoir comme trophee.
	 * 
	 */
	public Player chooseTrophyOwner(List<Player> players);

	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie la valeur faciale courante (mise e jour en fonction de la
	 *         partie).
	 */
	public int getUpdatedGameFaceValue(List<Player> players);

	/**
	 * @param players  Liste de toutes les instances de Player de la partie.
	 * @param myPlayer Le joueur qui possede cette instance de carte.
	 * @return Renvoie la valeur faciale de fin de jeu (mise e jour en fonction de
	 *         l'etat de fin de la partie).
	 */
	public int endFaceValue(List<Player> players, Player myPlayer);

	/**
	 * @param players  Liste de toutes les instances de Player de la partie.
	 * @param myPlayer Le joueur qui possede cette instance de carte.
	 * @return Renvoie la valeur de bonus de fin de partie, par exemple la valeur
	 *         bonus pour les paires noires ou pour le Joker sans coeurs.
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

	
	/**
	 * @return Renvoie la texture de la carte (une image).
	 */
	public ImageIcon getTexture();
}
