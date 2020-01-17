package fr.utt.lo02.jestgame.monsterandsword;

import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

/**
 * cette classe herite de la classe Card et va determiner qui a la carte Sword
 * @author akramsyukri
 *
 */
public class Sword extends Card {

	/**
	 * C'est le constructeur pour la classe Sword
	 * @param texture Image de la carte
	 * @param chooser Choisir la trophee
	 * 
	 */
	public Sword(ImageIcon texture, ITrophyChooser chooser) {
		super("Sword", "MonsterAndSword", 25, texture, chooser);
	}

	
	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		return 2;
	}
	
	
	@Override
	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 2;
	}

}
