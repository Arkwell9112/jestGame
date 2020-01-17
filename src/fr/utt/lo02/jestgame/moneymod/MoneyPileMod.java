package fr.utt.lo02.jestgame.moneymod;



import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ITrophyChooser;
import fr.utt.lo02.jestgame.api.ModType;
/**
 * cette classe implemente IMod et permet d'instancier MoneyPile
 * @author akramsyukri
 *
 */
public class MoneyPileMod implements IMod {

	/**
	 * recuperer le nom de mod
	 */
	@Override
	public String getName() {
		return "MoneyPile Mod";
	}

	/**
	 * recuperer la description de mod
	 */
	@Override
	public String getDescription() {
		
		return "Extension de carte contenant la carte MoneyPile, il y a 4 cartes MoneyPile, d'une valeur de 1 � 4, elles valent toutes 0 en fin de partie mais donne 1 point bonus pour chaque autre carte moneypile dans le Jest du joueur";
	}
	
	/**
	 * recuperer type de carte choisi par l'utilsateur
	 */
	@Override
	public ModType getType() {
		return ModType.CARDS;
	}

	/**
	 * instancier l'objet MoneyPile
	 */
	@Override
	public Object[] getInstance() {
		String path = "img\\moneypile";
		ITrophyChooser chooser = new MoneyChooser();
		Object[] money = new Object[4];
		money[0] = new MoneyPile(new ImageIcon(path + "\\moneypile1.jpg"), chooser, 1, "MoneyPileOne");
		money[1] = new MoneyPile(new ImageIcon(path + "\\moneypile2.jpg"), chooser, 2, "MoneyPileTwo");
		money[2] = new MoneyPile(new ImageIcon(path + "\\moneypile3.jpg"), chooser, 3, "MoneyPileThree");
		money[3] = new MoneyPile(new ImageIcon(path + "\\moneypile4.jpg"), chooser, 4, "MoneyPileFour");
		return money;
	}

}
