package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Ceci est l'interface permettant la manipulation des règles de jeu par le coeur de l'application.
 */
public interface IPartyRules {
	
	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie le premier joueur à jouer pour le tour de capture des cartes.
	 */
	public Player chooseFirstCatch(List<Player> players);
	/**
	 * @param players Liste de toutes les instances de Player de la partie.
	 * @return Renvoie le gagnant de la partie en cours.
	 */
	public Player chooseWinner(List<Player> players);
	/**
	 * @return Renvoie le nombre de cartes à distribuer aux joueurs.
	 */
	public int getPlayerCardNb();
	/**
	 * @param nbPlayers Nombre de joueurs qui jouent.
	 * @return Renvoie le nombre de trophées à utiliser pour cette partie.
	 */
	public int getTrophyCardNb(int nbPlayers);
	
	
		
		
	

}
