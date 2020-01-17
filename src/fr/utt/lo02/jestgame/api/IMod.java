package fr.utt.lo02.jestgame.api;

/**
 * Cette interface permet de manipuler les Mods qui sont charges par le GameLoader au lancement du jeu.
 * @author Edouard
 * 
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
	 * @return Renvoie une des trois valeurs de l'enum, permet de determiner le type de mod dont il s'agit.
	 */
	public ModType getType();
	/**
	 * @return Renvoie toutes les instances que le mod est cense generer. Toutes les cartes pour un mod de carte, le manager de regles pour un mod de regles ou un joueur robot pour un mod de strategie.
	 */
	public Object[] getInstance();
}
