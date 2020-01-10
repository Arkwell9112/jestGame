package fr.utt.lo02.jestgame.moneymod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

/**
 * 
 * @author akramsyukri corrected by Edouard Bergé
 *
 */

public class MoneyPile implements ICard {

	private ImageIcon texture;
	private ITrophyChooser chooser;
	private int baseValue;
	private String name;

	public MoneyPile(ImageIcon texture, ITrophyChooser chooser, int baseValue, String name) {
		this.texture = texture;
		this.chooser = chooser;
		this.baseValue = baseValue;
		this.name = name;
	}

	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		return chooser.delegateTrophyChoose(players, this, null);

	}

	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		return baseValue;
	}

	@Override
	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}

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

	@Override
	public String getName() {

		return name;
	}

	@Override
	public String getColor() {
		return "MoneyPile";
	}

	@Override
	public int getColorValue() {
		return 26;
	}

	@Override
	public ImageIcon getTexture() {
		return texture;
	}

}
