package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Classe de base pour tous les piques et trèfles.
 */
public class SpadesAndClubs extends CouldBeAnAce implements ICard {

	/**
	 * Définit le type de trophée dont il s'agit en utilisant l'énumération associé
	 * TrophyType.
	 */
	private TrophyType myType;
	/**
	 * L'argument à envoyer au ITrophyChooser pour paramétrer le choix en tant que trophée.
	 */
	private Object trophyArg;

	/**
	 * @param name Nom de la carte.
	 * @param color Nom de la couleur de la carte.
	 * @param colorValue Valeur de la couleur de la carte.
	 * @param baseValue Valeur de base de la carte.
	 * @param type Type de trophée pour cette carte.
	 * @param trophyArg Argument pour le choix en tant que trophée.
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
