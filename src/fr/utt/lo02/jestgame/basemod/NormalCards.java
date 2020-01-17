package fr.utt.lo02.jestgame.basemod;

import javax.swing.ImageIcon;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;
import fr.utt.lo02.jestgame.basemod.trohychooser.TrophyType;

/**
 * @author Edouard
 * Classe de chargement de l'extension ajoutant les cartes de base du Jest.
 */
public class NormalCards implements IMod {
	/**
	 * Chemin vers le dossier contenant les assets de l'extension.
	 */
	private String path;

	/**
	 * Constructeur de la classe.
	 */
	public NormalCards() {
		path = "img\\normalcards";
	}

	@Override
	public String getName() {
		return "Permet l'ajout de toutes les cartes de bases";
	}

	@Override
	public String getDescription() {
		return "Ajoute toutes les cartes classiques du Jest";
	}

	@Override
	public ModType getType() {
		return ModType.CARDS;
	}

	/**
	 * @return Renvoie les 17 cartes de base du Jest. Avec chacune leur texture, valeur de couleur, valeur de base, nom de couleur et nom de carte.
	 */
	@Override
	public Object[] getInstance() {
		Object[] arg = new Object[17];
		arg[0] = new Joker(TrophyType.BEST, null, new ImageIcon(path + "\\joker.jpg"));
		arg[1] = new Hearts("Ace", 1, TrophyType.WITHJ, null, new ImageIcon(path + "\\aceheart.jpg"));
		arg[2] = new Hearts("Two", 2, TrophyType.WITHJ, null, new ImageIcon(path + "\\twoheart.jpg"));
		arg[3] = new Hearts("Three", 3, TrophyType.WITHJ, null, new ImageIcon(path + "\\threeheart.jpg"));
		arg[4] = new Hearts("Four", 4, TrophyType.WITHJ, null, new ImageIcon(path + "\\fourheart.jpg"));
		arg[5] = new Diamonds("Ace", 1, TrophyType.MAJORITY, 4, new ImageIcon(path + "\\acediamond.jpg"));
		arg[6] = new Diamonds("Two", 2, TrophyType.HIGHEST, "Diamond", new ImageIcon(path + "\\twodiamond.jpg"));
		arg[7] = new Diamonds("Three", 3, TrophyType.LOWEST, "Diamond", new ImageIcon(path + "\\threediamond.jpg"));
		arg[8] = new Diamonds("Four", 4, TrophyType.BESTNJ, null, new ImageIcon(path + "\\fourdiamond.jpg"));
		arg[9] = new SpadesAndClubs("Ace", "Club", 30, 1, TrophyType.HIGHEST, "Spade", new ImageIcon(path + "\\aceclub.jpg"));
		arg[10] = new SpadesAndClubs("Two", "Club", 30, 2, TrophyType.LOWEST, "Heart", new ImageIcon(path + "\\twoclub.jpg"));
		arg[11] = new SpadesAndClubs("Three", "Club", 30, 3, TrophyType.HIGHEST, "Heart", new ImageIcon(path + "\\threeclub.jpg"));
		arg[12] = new SpadesAndClubs("Four", "Club", 30, 4, TrophyType.LOWEST, "Spade", new ImageIcon(path + "\\fourclub.jpg"));
		arg[13] = new SpadesAndClubs("Ace", "Spade", 40, 1, TrophyType.HIGHEST, "Club", new ImageIcon(path + "\\acespade.jpg"));
		arg[14] = new SpadesAndClubs("Two", "Spade", 40, 2, TrophyType.MAJORITY, 3, new ImageIcon(path + "\\twospade.jpg"));
		arg[15] = new SpadesAndClubs("Three", "Spade", 40, 3, TrophyType.MAJORITY, 2, new ImageIcon(path + "\\threespade.jpg"));
		arg[16] = new SpadesAndClubs("Four", "Spade", 40, 4, TrophyType.LOWEST, "Club", new ImageIcon(path + "\\fourspade.jpg"));
		return arg;
	}

}
