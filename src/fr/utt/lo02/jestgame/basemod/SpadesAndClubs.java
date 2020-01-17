package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

/**
 * Classe de base pour tous les piques et trefles.
 * @author Edouard
 * 
 */
public class SpadesAndClubs extends CouldBeAnAce implements ICard {

	/**
	 * Definit le type de trophee dont il s'agit en utilisant l'enumeration associe
	 * TrophyType.
	 */
	private TrophyType myType;
	/**
	 * L'argument e envoyer au ITrophyChooser pour parametrer le choix en tant que trophee.
	 */
	private Object trophyArg;

	/**
	 * @param name Nom de la carte.
	 * @param color Nom de la couleur de la carte.
	 * @param colorValue Valeur de la couleur de la carte.
	 * @param baseValue Valeur de base de la carte.
	 * @param type Type de trophee pour cette carte.
	 * @param trophyArg Argument pour le choix en tant que trophee.
	 * @param texture Texture de la carte.
	 */
	public SpadesAndClubs(String name, String color, int colorValue, int baseValue, TrophyType type, Object trophyArg, ImageIcon texture) {
		super(name, color, colorValue, baseValue, texture);
		this.myType = type;
		this.trophyArg = trophyArg;
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		boolean found = false;
		while (it.hasNext()) {
			ICard current = it.next();
			if (this.getColor() == "Spade") {
				if (current.getColor() == "Club") {
					if (current.endFaceValue(players, myPlayer) == this.endFaceValue(players, myPlayer)) {
						found = true;
					}
				}
			} else if (this.getColor() == "Club") {
				if (current.getColor() == "Spade") {
					if (current.endFaceValue(players, myPlayer) == this.endFaceValue(players, myPlayer)) {
						found = true;
					}
				}
			}
		}

		if (found) {
			return 1;
		} else {
			return 0;
		}
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return getBaseValue();
	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		return getAceValue(players, myPlayer);
	}

}
