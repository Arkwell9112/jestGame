package fr.utt.lo02.jestgame.basemod;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;

/**
 * Classe mere de toutes les classes qui sont des cartes (dans l'extension des cartes classiques du Jest).
 * Cette classe implemente ICard.
 * @author akramsyukri 
 * 
 * 
 */
public abstract class Card implements ICard {

	/**
	 * Nom de la carte, sous forme de String.
	 */
	private String name;
	/**
	 * Valeur de la couleur de la carte, 10 coeur, 20 carreau, 30 trefle, 40 pique.
	 */
	private int colorValue;
	/**
	 * Nom de la couleur de la carte, sous forme de String.
	 */
	private String color;
	/**
	 * Valeur faciale de base de la carte, toujours positive dans cette extension.
	 */
	private int baseValue;

	/**
	 *  Texture de la carte, sous forme d'une image.
	 */
	private ImageIcon texture;

	/**
	 * @return Renvoie la valeur faciale de base de la carte.
	 */
	public int getBaseValue() {
		return baseValue;
	}

	/**
	 * @return Renvoie le nom de la carte.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Renvoie la valeur de la couleur de la carte.
	 */
	public int getColorValue() {
		return colorValue;
	}

	/**
	 * @return Renvoie le nom de la couleur de la carte
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return Renvoie la texture de la carte.
	 */
	public ImageIcon getTexture() {
		return texture;
	}

	/**
	 * c'est le constructeur pour la classe Card
	 * 
	 * @param name       Nom de la carte.
	 * @param color      Nom de la couleur de la carte.
	 * @param colorValue Valeur de la couleur de la carte.
	 * @param baseValue  Valeur faciale de base de la carte.
	 * @param texture    Texture de la carte.
	 */
	public Card(String name, String color, int colorValue, int baseValue, ImageIcon texture) {
		this.texture = texture;
		this.name = name;
		this.color = color;
		this.colorValue = colorValue;
		this.baseValue = baseValue;
	}
}
