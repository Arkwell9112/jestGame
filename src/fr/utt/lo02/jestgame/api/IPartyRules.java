package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

/**
 * Ceci est l'interface permettant la manipulation des regles de jeu par le coeur de l'application.
 * @author Edouard
 * 
 */
public interface IPartyRules {
	
	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie le premier joueur e jouer pour le tour de capture des cartes.
	 */
	public Player chooseFirstCatch(List<Player> players);
	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie le gagnant de la partie en cours.
	 */
	public Player chooseWinner(List<Player> players);
	/**
	 * @return Renvoie le nombre de cartes e distribuer aux joueurs.
	 */
	public int getPlayerCardNb();
	/**
	 * @param nbPlayers Nombre de joueurs qui jouent.
	 * @return Renvoie le nombre de trophees e utiliser pour cette partie.
	 */
	public int getTrophyCardNb(int nbPlayers);
	
	
		
		
	

}
