package fr.utt.lo02.jestgame.moneymod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

/**
 * cette classe implemente ICard et permet de jouer avec 4 cartes supplementaire de 'MoneyPile'
 * @author akramsyukri corrected by Edouard Berge
 *
 */

public class MoneyPile implements ICard {

	private ImageIcon texture;
	private ITrophyChooser chooser;
	private int baseValue;
	private String name;

	/**
	 * 
	 * @param texture Image de la carte
	 * @param chooser Choisir les jouers pour donner la carte trophee
	 * @param baseValue Valeur faciale de la carte
	 * @param name Nom de la carte
	 */
	public MoneyPile(ImageIcon texture, ITrophyChooser chooser, int baseValue, String name) {
		this.texture = texture;
		this.chooser = chooser;
		this.baseValue = baseValue;
		this.name = name;
	}

	/**
	 * @param players Liste de toutes les instances de Player de la partie et qui va etre choisi en tant que joueur qui a la trophee.
	 */
	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		return chooser.delegateTrophyChoose(players, this, null);

	}

	/**
	 *@param players Liste de toutes les instances de Player de la partie
	 */
	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		return baseValue;
	}

	/**
	 * @param players Liste de toutes les instances de Player de la partie
	 * @param myPlayer Le joueur qui possede cette instance de carte.
	 */
	@Override
	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}

	/**
	 * @param players Liste de toutes les instances de Player de la partie
	 * @param myPlayer myPlayer Le joueur qui possede cette instance de carte.
	 */
	@Override
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		int found = 0;
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		while (it.hasNext()) {
			ICard current = it.next();
			if (current.getColor() == "MoneyPile" && current != this) {
				found++;
			}
		}
		return found;
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
		return "MoneyPile";
	}

	/**
	 * getteur de valeur de couleur de la carte
	 */
	@Override
	public int getColorValue() {
		return 26;
	}

	/**
	 * getteur de l'image de la carte
	 */
	@Override
	public ImageIcon getTexture() {
		return texture;
	}

}
