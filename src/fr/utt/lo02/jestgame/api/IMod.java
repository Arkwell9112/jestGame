package fr.utt.lo02.jestgame.api;

/**
 * @author Edouard
 * Cette interface permet de manipuler les Mods qui sont chargés par le GameLoader au lancement du jeu.
 */
public interface IMod {
	/**
	 * @return Renvoie le nom du mod.
	 */
	public String getName();
	/**
	 * @return Renvoie la description du mod.
	 */
	public String getDescription();
	/**
	 * @return Renvoie une des trois valeurs de l'enum, permet de déterminer le type de mod dont il s'agit.
	 */
	public ModType getType();
	/**
	 * @return Renvoie toutes les instances que le mod est censé générer. Toutes les cartes pour un mod de carte, le manager de règles pour un mod de règles ou un joueur robot pour un mod de stratégie.
	 */
	public Object[] getInstance();
}
