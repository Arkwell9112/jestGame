package fr.utt.lo02.jestgame.moneymod;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;
import fr.utt.lo02.jestgame.core.Player;

/**
 * 
 * @author akramsyukri
 *
 */

public class MoneyPile implements ICard{
	
	private TrophyType myType;
	private Object trophyArg;
	private ImageIcon texture;
	private ITrophyChooser chooser;
	
	
	public MoneyPile(ImageIcon texture, ITrophyChooser chooser) {
		this.texture=texture;
		this.chooser=chooser;
	}
	
	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		return myType.getChooser().delegateTrophyChoose(players, this, trophyArg);
		
	}

	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		return 0;
	}

	@Override
	public int endFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}

	@Override
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		boolean found = false;
		Iterator<ICard> it = myPlayer.getCapturedCards().iterator();
		while(it.hasNext()) {
			ICard current = it.next();
			if(current.getName() == "MoneyPile") {
				found = true;
			}
		}
		if(found) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String getName() {
		
		return null;
	}

	@Override
	public String getColor() {
		return null;
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
