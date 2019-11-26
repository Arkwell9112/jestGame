package fr.utt.lo02.jestgame.basemod;

import fr.utt.lo02.jestgame.api.ICard;

/**
 * 
 * @author akramsyukri
 *
 */
public abstract class Card implements ICard {

	private String name;
	private int colorValue;
	private String color;
	private int baseValue;

	public int getBaseValue() {
		return baseValue;
	}

	// Ce sont des methodes getteur
	public String getName() {
		return name;
	}

	public int getColorValue() {
		return colorValue;
	}

	public String getColor() {
		return color;
	}

	/**
	 * c'est le constructeur pour la classe Card
	 * 
	 * @param name
	 * @param color
	 * @param colorValue
	 * @param baseValue
	 */
	public Card(String name, String color, int colorValue, int baseValue) {
		this.name = name;
		this.color = color;
		this.colorValue = colorValue;
		this.baseValue = baseValue;
	}
}
