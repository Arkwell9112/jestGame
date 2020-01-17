package fr.utt.lo02.jestgame.basemod;

import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard Classe de base pour tous les carreaux.
 */
public class Diamonds extends CouldBeAnAce {

	/**
	 * Définit le type de trophée dont il s'agit en utilisant l'énumération associé
	 * TrophyType.
	 */
	private TrophyType myType;
	/**
	 * L'argument à envoyer au ITrophyChooser pour paramétrer le choix en tant que
	 * trophée.
	 */
	private Object trophyArg;

	/**
	 * @param name      Nom de la carte.
	 * @param baseValue Valeur faciale de base de la carte.
	 * @param type      Type de trophée de la carte.
	 * @param trophyArg Argument permettant le choix du joueur recevant le trophée.
	 * @param texture   Texture de la carte. 
	 * Constructeur de la classe.
	 */
	public Diamonds(String name, int baseValue, TrophyType type, Object trophyArg, ImageIcon texture) {
		super(name, "Diamond", 20, baseValue, texture);
		this.myType = type;
		this.trophyArg = trophyArg;
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return getBaseValue();
	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		return -getAceValue(players, myPlayer);
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		return 0;

	}

}
