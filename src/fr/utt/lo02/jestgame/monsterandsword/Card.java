package fr.utt.lo02.jestgame.monsterandsword;

import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

/**
 * cette classe abstract implemente ICar et qui permet d'obtenir tous les attributs d'une carte
 * @author akramsyukri
 *
 */
public abstract class Card implements ICard {
	private String name;
	private String color;
	private int colorValue;
	private ImageIcon texture;
	private ITrophyChooser chooser;

	/**
	 * 
	 * @param name Nom de la carte
	 * @param color Couleur de la carte
	 * @param colorValue Valeur de couleur de la carte
	 * @param texture Image de la carte
	 * @param chooser Carte trophe choisi parmi les jouers
	 */
	public Card(String name, String color, int colorValue, ImageIcon texture, ITrophyChooser chooser) {
		this.name = name;
		this.color = color;
		this.colorValue = colorValue;
		this.texture = texture;
		this.chooser = chooser;
	}
	
	/**
	 * @param players Liste de toutes les instances de Player de la partie et qui va etre choisi en tant que joueur qui a la trophee
	 */
	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		return chooser.delegateTrophyChoose(players, this, null);
	}

	/**
	 * @param players Liste de toutes les instances de Player
	 * @param myPlayer Le joueur qui possede cette instance de carte.
	 */
	@Override
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}
	/**
	 * getteur de nom de la carte
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * getteur de couleur de la carte
	 */
	@Override
	public String getColor() {
		return color;
	}
	
	/**
	 * getteur de valeur de la couleur de la carte
	 */
	@Override
	public int getColorValue() {
		return colorValue;
	}
	/**
	 * getteur de l'image de la carte
	 */
	@Override
	public ImageIcon getTexture() {
		return texture;
	}
}
