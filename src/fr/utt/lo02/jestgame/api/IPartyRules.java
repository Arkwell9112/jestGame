package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Ceci est l'interface permettant la manipulation des régles de jeu par le coeur de l'application.
 */
public interface IPartyRules {
	
	/**
	 * @param players Tous les joueurs.
	 * @return Renvoie le premier joueur à jouer.
	 */
	public Player chooseFirstCatch(List<Player> players);
	/**
	 * @param players Tous les joueurs.
	 * @return Renvoie le gagnant de la partie en cour.
	 */
	public Player chooseWinner(List<Player> players);
	/**
	 * @return Renvoie de nombre de cartes à distribuer aux joueurs.
	 */
	public int getPlayerCardNb();
	/**
	 * @param nbPlayers Nombre de joueurs qui jouent.
	 * @return Renvoie le nombre de trophées à mettre de côté.
	 */
	public int getTrophyCardNb(int nbPlayers);
	
	
		
		
	

}
