package fr.utt.lo02.jestgame.monsterandsword;

import java.util.List;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.core.Player;

public abstract class Card implements ICard {
	private String name;
	private String color;
	private int colorValue;
	private ImageIcon texture;
	private ITrophyChooser chooser;

	public Card(String name, String color, int colorValue, ImageIcon texture, ITrophyChooser chooser) {
		this.name = name;
		this.color = color;
		this.colorValue = colorValue;
		this.texture = texture;
		this.chooser = chooser;
	}
	
	@Override
	public Player chooseTrophyOwner(List<Player> players) {
		return chooser.delegateTrophyChoose(players, this, null);
	}

	@Override
	public int endSpecialFaceValue(List<Player> players, Player myPlayer) {
		return 0;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getColor() {
		return color;
	}
	
	@Override
	public int getColorValue() {
		return colorValue;
	}
	
	@Override
	public ImageIcon getTexture() {
		return texture;
	}
}
