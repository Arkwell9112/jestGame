package fr.utt.lo02.jestgame.moneymod;



import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;
/**
 * 
 * @author akramsyukri
 *
 */
public class MoneyPileMod implements IMod {

	@Override
	public String getName() {
		return "MoneyPile";
	}

	@Override
	public String getDescription() {
		
		return "Extension de carte contenant la carte MoneyPile";
	}

	@Override
	public ModType getType() {
		return ModType.CARDS;
	}

	@Override
	public Object[] getInstance() {
		Object[] money = new Object[3];
		//money[0]= new MoneyPile(new ImageIcon("img/monsterandsword/monster.jpg")); 
		return money;
	}

}
