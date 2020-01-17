package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Classe de base pour tous les coeurs.
 */
public class Hearts extends CouldBeAnAce {

	/**
	 * D�finit le type de troph�e dont il s'agit en utilisant l'�num�ration associ�
	 * TrophyType.
	 */
	private TrophyType myType;
	/**
	 * L'argument � envoyer au ITrophyChooser pour param�trer le choix en tant que troph�e.
	 */
	private Object trophyArg;

	/**
	 * @param name Nom de la carte.
	 * @param baseValue Valeur de base de la carte.
	 * @param type Type de troph�e de la carte.
	 * @param trophyArg Argument pour le choix en tant que troph�e.
	 * @param texture Texture de la carte.
	 * Constructeur de la classe.
	 */
	public Hearts(String name, int baseValue, TrophyType type, Object trophyArg, ImageIcon texture) {
		super(name, "Heart", 10, baseValue, texture);
		this.myType = type;
		this.trophyArg = trophyArg;
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		boolean jok = false;
		while (it.hasNext()) {
			if (it.next().getName() == "Joker") {
				jok = true;
			}
		}

		if (jok) {
			it = myPlayer.getCapturedCards().iterator();
			int counter = 0;
			while (it.hasNext()) {
				if (it.next().getColor() == "Heart") {
					counter++;
				}
			}
			if (counter <= 3) {
				return -getAceValue(players, myPlayer);
			} else {
				return getAceValue(players, myPlayer);
			}
		} else {
			return 0;
		}
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return getBaseValue();
	}

}
