package fr.utt.lo02.jestgame.basemod;

import fr.utt.lo02.jestgame.api.ICard;

/**
 * 
 * @author akramsyukri
 * Classe mère de toutes les classes qui sont des cartes dans le mod
 */
public abstract class Card implements ICard {

	/**
	 * Nom de la carte, sous forme de String
	 */
	private String name;
	/**
	 * Valeur de la couleur de la carte, 10 coeur, 20 carreau, 30 trèfle, 40 pic
	 */
	private int colorValue;
	/**
	 * Nom de la couleur de la carte, sous forme de String
	 */
	private String color;
	/**
	 * Valeur faciale de base de la carte, toujours positive dans ce cas
	 */
	private int baseValue;

	/**
	 * @return Renvoie la valeur faciale de base de la carte, valeur strictement positive
	 */
	public int getBaseValue() {
		return baseValue;
	}

	/**
	 * @return Renvoie le nom de la carte
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return renvoie la valeur de la couleur de la carte, pour les cartes de base
	 * cette valeur est figé, 10 coeur, 20 carreau, 30 trèfle et 40 pic
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
	 * c'est le constructeur pour la classe Card
	 * 
	 * @param name Nom de la carte
	 * @param color Nom de la couleur de la carte
	 * @param colorValue Valeur de la couleur de la carte
	 * @param baseValue Valeur faciale de base de la carte
	 */
	public Card(String name, String color, int colorValue, int baseValue) {
		this.name = name;
		this.color = color;
		this.colorValue = colorValue;
		this.baseValue = baseValue;
	}
}
