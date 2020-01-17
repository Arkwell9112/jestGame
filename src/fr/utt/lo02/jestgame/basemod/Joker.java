package fr.utt.lo02.jestgame.basemod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Classe du Joker.
 */
public class Joker extends Card {

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
	 * @param type Type de troph�e qu'est le Joker.
	 * @param trophyArg Argument pour le choix en tant que troph�e.
	 * @param texture Texture de la carte.
	 * Constructeur de la classe.
	 */
	public Joker(TrophyType type, Object trophyArg, ImageIcon texture) {
		super("Joker", "Joker", 0, 0, texture);
		this.myType = type;
		this.trophyArg = trophyArg;
	}

	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
	}

	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		int counter = 0;
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		while (it.hasNext()) {
			ICard currentCard = it.next();
			if (currentCard.getColor() == "Heart") {
				counter++;
			}
		}

		if (counter == 0) {
			return 4;
		}

		return 0;
	}

	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;

	}

	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 0;

	}
}
