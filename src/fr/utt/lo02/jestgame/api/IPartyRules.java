package fr.utt.lo02.jestgame.api;

import java.util.List;

import fr.utt.lo02.jestgame.core.Player;

/**
 * @author Edouard
 * Ceci est l'interface permettant la manipulation des r�gles de jeu par le coeur de l'application.
 */
public interface IPartyRules {
	
	/**
	 * @param players Tous les joueurs.
	 * @return Renvoie le premier joueur � jouer.
	 */
	public Player chooseFirstCatch(List<Player> players);
	/**
	 * @param players Tous les joueurs.
	 * @return Renvoie le gagnant de la partie en cour.
	 */
	public Player chooseWinner(List<Player> players);
	/**
	 * @return Renvoie de nombre de cartes � distribuer aux joueurs.
	 */
	public int getPlayerCardNb();
	/**
	 * @param nbPlayers Nombre de joueurs qui jouent.
	 * @return Renvoie le nombre de troph�es � mettre de c�t�.
	 */
	public int getTrophyCardNb(int nbPlayers);
	
	
		
		
	

}
