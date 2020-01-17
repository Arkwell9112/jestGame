package fr.utt.lo02.jestgame.core.graphicui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.core.Player;

/**
 * Classe de la vue du patron MVC en mode graphique.
 * @author Edouard
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame {
	/**
	 * Contrôleur lié à cette vue.
	 */
	private GraphicController controller;
	private JPanel container;
	private NbBotPanel bots;
	private NbPlayerPanel players;
	private ModPanel mods;
	private NamePanel names;
	private PlayPanel plays;
	private WinPanel wins;
	/**
	 * Layout pour la fenêtre.
	 */
	private CardLayout layout;
	private ShowPanel shows;

	/**
	 * @param controller Controller lié à cette vue.
	 */
	public Window(GraphicController controller) {
		this.controller = controller;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screen.width / 2, screen.height / 2);
		container = new JPanel();
		layout = new CardLayout();
		container.setLayout(layout);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	/**
	 * Affiche la panel contenant le Jest du joueur.
	 */
	public void showPlayPanel() {
		layout.show(container, "plays");
	}

	/**
	 * Méthode renvoyant le callback vers le controller.
	 * @param event Event ayant causé le callBack.
	 * @param arg argument du callBack.
	 */
	public void callBack(CallBackEvent event, Object arg) {
		controller.callBack(event, arg);
	}

	/**
	 * Méthode paramétrant le panel d'affichage des Jests.
	 * @param cards Liste des cartes du Jest de ce joueur.
	 * @param player Joueur dont on veut afficher le Jest.
	 */
	public void setShowPanel(List<ICard> cards, Player player) {
		if (shows == null) {
			shows = new ShowPanel(this);
			container.add(shows);
			layout.addLayoutComponent(shows, "shows");
		}
		shows.setWin("Voici les cartes de votre Jest actuel", player.getName(), cards);
		layout.show(container, "shows");
	}

	/**
	 * Méthode affichant le panel de fin de jeu.
	 * @param state Une string décrivant l'état du joueur dans le jeu.
	 * @param who Nom du joueur.
	 * @param cards Liste des cartes du Jest du joueur.
	 */
	public void setWinPanel(String state, String who, List<ICard> cards) {
		if (wins == null) {
			wins = new WinPanel(this);
			container.add(wins);
			layout.addLayoutComponent(wins, "wins");
		}
		wins.setWin(state, who, cards);
		layout.show(container, "wins");
	}

	/**
	 * Méthode affichant le panel de choix du nombre de joueurs.
	 */
	public void setNbPlayerPanel() {
		if (players == null) {
			players = new NbPlayerPanel(this);
			container.add(players);
			layout.addLayoutComponent(players, "players");
		}
		layout.show(container, "players");
	}

	/**
	 * Méthode affichant le panel de choix du nombre de joueurs robots.
	 * @param nbPlayer Nombre de joueurs de la partie.
	 */
	public void setNbBotPanel(byte nbPlayer) {
		if (bots == null) {
			bots = new NbBotPanel(this, nbPlayer);
			container.add(bots);
			layout.addLayoutComponent(bots, "bots");
		}
		layout.show(container, "bots");
	}

	/**
	 * Méthode affichant un panel de choix pour le mod.
	 * @param mod Mod dont il faut afficher le choix.
	 * @param title Titre du panel.
	 */
	public void setModPanel(IMod mod, String title) {
		if (mods == null) {
			mods = new ModPanel(this);
			container.add(mods);
			layout.addLayoutComponent(mods, "mods");
		}
		mods.setBeforeTitle(title);
		mods.displayMod(mod);
		layout.show(container, "mods");
	}

	/**
	 * @param nb Index du joueur dont on choisit le nom.
	 */
	public void setNamePanel(byte nb) {
		if (names == null) {
			names = new NamePanel(this);
			container.add(names);
			layout.addLayoutComponent(names, "names");
		}
		names.askName(nb);
		layout.show(container, "names");
	}

	/**
	 * Méthode mettant en place le panel de jeu.
	 * @param action Action à effectuer, capture ou mise face visible.
	 * @param players Liste des joueurs de la partie.
	 * @param activePlayer Joueur actif.
	 */
	public void setPlayPanel(String action, List<Player> players, Player activePlayer) {
		plays.setActionLabel(action);
		plays.setPlay(players, activePlayer);
		layout.show(container, "plays");
	}

	/**
	 * @param nbPlayer Nombre de joueurs.
	 * @param nbCard Nombre de cartes dans les mains des joueurs.
	 * @param trophies Liste de cartes des trophées.
	 */
	public void setTrophies(byte nbPlayer, byte nbCard, List<ICard> trophies) {
		if (plays == null) {
			plays = new PlayPanel(nbPlayer, nbCard, (byte) trophies.size(), this);
			container.add(plays);
			layout.addLayoutComponent(plays, "plays");
		}
		plays.setTrophies(trophies);
	}
}
