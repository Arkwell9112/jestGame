package fr.utt.lo02.jestgame.moneymod;

import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.core.Player;

public class MoneyPile implements ICard{

	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUpdatedGameFaceValue(List<Player> players) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int endFaceValue(List<Player> players, Player myPlayer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColorValue() {
		return 26;
	}

	@Override
	public ImageIcon getTexture() {
		
		return null;
	}

}
