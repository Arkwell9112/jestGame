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

@SuppressWarnings("serial")
public class Window extends JFrame {
	private GraphicController controller;
	private JPanel container;
	private NbBotPanel bots;
	private NbPlayerPanel players;
	private ModPanel mods;
	private NamePanel names;
	private PlayPanel plays;
	private WinPanel wins;
	private CardLayout layout;

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

	public void callBack(CallBackEvent event, Object arg) {
		controller.callBack(event, arg);
	}
	
	public void setWinPanel(String state, String who, List<ICard> cards) {
		if(wins == null) {
			wins = new WinPanel(this);
			container.add(wins);
			layout.addLayoutComponent(wins, "wins");
		}
		wins.setWin(state, who, cards);
		layout.show(container, "wins");
	}

	public void setNbPlayerPanel() {
		if (players == null) {
			players = new NbPlayerPanel(this);
			container.add(players);
			layout.addLayoutComponent(players, "players");
		}
		layout.show(container, "players");
	}

	public void setNbBotPanel(byte nbPlayer) {
		if (bots == null) {
			bots = new NbBotPanel(this, nbPlayer);
			container.add(bots);
			layout.addLayoutComponent(bots, "bots");
		}
		layout.show(container, "bots");
	}

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

	public void setNamePanel(byte nb) {
		if (names == null) {
			names = new NamePanel(this);
			container.add(names);
			layout.addLayoutComponent(names, "names");
		}
		names.askName(nb);
		layout.show(container, "names");
	}

	public void setPlayPanel(String action, List<Player> players, Player activePlayer) {
		plays.setActionLabel(action);
		plays.setPlay(players, activePlayer);
		layout.show(container, "plays");
	}

	public void setTrophies(byte nbPlayer, byte nbCard, List<ICard> trophies) {
		if (plays == null) {
			plays = new PlayPanel(nbPlayer, nbCard, (byte) trophies.size(), this);
			container.add(plays);
			layout.addLayoutComponent(plays, "plays");
		}
		plays.setTrophies(trophies);
	}
}
