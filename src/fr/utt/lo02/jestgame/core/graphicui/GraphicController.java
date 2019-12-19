package fr.utt.lo02.jestgame.core.graphicui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;
import fr.utt.lo02.jestgame.core.IObserver;
import fr.utt.lo02.jestgame.core.NotEvent;
import fr.utt.lo02.jestgame.core.Observable;
import fr.utt.lo02.jestgame.core.Player;
import fr.utt.lo02.jestgame.core.Pot;

public class GraphicController implements IObserver {
	private Window window;
	private byte nbPlayer;
	private byte nbBot;
	private boolean choosed;
	private byte nbCard;
	private String name;
	private Pot trophyPot;

	private Observable lastObserved;
	private Object[] lastPlayers;
	private Player currentPlayer;

	public GraphicController() {
		window = new Window(this);
	}

	@Override
	public void update(Observable observed, NotEvent callEvent, Object[] args) {
		if (callEvent == NotEvent.CREATE_PARTY_MENU) {
			setCreatePartyMenu((IMod[]) args, observed);
		} else if (callEvent == NotEvent.FACE_UP_MENU || callEvent == NotEvent.FACE_UP_MENU_BOT) {
			if (callEvent == NotEvent.FACE_UP_MENU) {
				setFaceUpMenu(observed, args);
			} else {
				observed.notifyBack(callEvent, null);
			}
		} else if (callEvent == NotEvent.SHOW_TROPHY) {
			trophyPot = (Pot) args[0];
			byte nbCard = (byte) args[1];
			this.nbCard = nbCard;
			window.setTrophies(nbPlayer, nbCard, trophyPot.getTrophies());
		} else if (callEvent == NotEvent.CURRENT_PLAYER) {
			currentPlayer = (Player) args[0];
		} else if (callEvent == NotEvent.CATCH_UP_MENU) {
			setCatchUpMenu(observed, args);
		} else if (callEvent == NotEvent.CATCH_UP_MENU_ERROR) {
			setCatchUpMenu(lastObserved, lastPlayers);
		} else if (callEvent == NotEvent.WIN_MENU) {
			setWinMenu(args);
		}
	}

	public synchronized void callBack(CallBackEvent event, Object arg) {
		if (event == CallBackEvent.PLAYER_PANEL) {
			nbPlayer = (byte) arg;
		} else if (event == CallBackEvent.BOT_PANEL) {
			nbBot = (byte) arg;
		} else if (event == CallBackEvent.MOD_PANEL) {
			choosed = (boolean) arg;
		} else if (event == CallBackEvent.NAME_PANEL) {
			name = (String) arg;
		} else if (event == CallBackEvent.PLAY_PANEL) {
			name = (String) arg;
		}
		this.notifyAll();
	}

	private synchronized void setWinMenu(Object[] args) {
		List<Player> players = new ArrayList<Player>();
		for (Object obj : args) {
			players.add((Player) obj);
		}
		List<Player> refinedPlayers = new ArrayList<Player>(players);
		refinedPlayers.remove(currentPlayer);
		window.setWinPanel("GAGNANT", "Joueur: " + currentPlayer.getName() + " vous avez obtenu: "
				+ currentPlayer.calculateScore(players) + " points.", currentPlayer.getCapturedCards());
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Iterator<Player> it = refinedPlayers.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			window.setWinPanel("PERDANT", "Joueur: " + current.getName() + " vous avez obtenu: "
					+ current.calculateScore(players) + " points.", current.getCapturedCards());
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private synchronized void setCatchUpMenu(Observable observed, Object[] args) {
		lastObserved = observed;
		lastPlayers = args;
		List<Player> players = new ArrayList<Player>();
		for (Object obj : args) {
			players.add((Player) obj);
		}
		window.setPlayPanel("Veuillez choisir un joueur à capturer et une carte à capturer en cliquant dessus", players,
				currentPlayer);
		choosed = false;
		while (!choosed) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (name.contains("passive")) {
				String current = name.replaceAll("passive", "");
				boolean tf = false;
				int rank = Integer.parseInt(current);
				int refined = rank % nbCard;
				if (refined == 0) {
					tf = true;
				}
				rank = (rank - refined) / nbCard;
				Iterator<Player> it = players.iterator();
				int counter = 0;
				boolean found = false;
				Player founded = null;
				while (it.hasNext() && !found) {
					Player localCurrentPlayer = it.next();
					if (localCurrentPlayer != currentPlayer) {
						if (counter == rank) {
							founded = localCurrentPlayer;
							found = true;
						}
						counter++;
					}
				}
				Object[] back = { founded, tf };
				choosed = true;
				observed.notifyBack(NotEvent.CATCH_UP_MENU, back);
			} else {
				String current = name.replaceAll("active", "");
				boolean tf = false;
				int rank = Integer.parseInt(current);
				if (rank == 0) {
					tf = true;
				}
				Iterator<Player> it = players.iterator();
				int counter = 0;
				while (it.hasNext()) {
					Player localCurrentPlayer = it.next();
					if (!localCurrentPlayer.isCatchedUp()) {
						counter++;
					}
				}
				if (counter == 1) {
					Object[] back = { currentPlayer, tf };
					choosed = true;
					observed.notifyBack(NotEvent.CATCH_UP_MENU, back);
				}
			}
		}
	}

	private synchronized void setFaceUpMenu(Observable observed, Object[] args) {
		@SuppressWarnings("unchecked")
		List<Player> players = (List<Player>) args[1];
		Player activePlayer = (Player) args[0];
		window.setPlayPanel("Veuillez choisir une de vos cartes à mettre face visible", players, activePlayer);
		choosed = false;
		while (!choosed) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (name.contains("active")) {
				String current = name.replaceAll("active", "");
				int rank = Integer.parseInt(current);
				Object[] back = { (byte) rank };
				observed.notifyBack(NotEvent.FACE_UP_MENU, back);
			}
		}
	}

	private synchronized void setCreatePartyMenu(IMod[] mods, Observable observed) {
		window.setNbPlayerPanel();
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.setNbBotPanel(nbPlayer);
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<IMod> players = new ArrayList<IMod>();
		List<IMod> cards = new ArrayList<IMod>();
		List<IMod> rules = new ArrayList<IMod>();

		for (IMod mod : mods) {
			if (mod.getType() == ModType.STRATEGY) {
				players.add(mod);
			} else if (mod.getType() == ModType.CARDS) {
				cards.add(mod);
			} else {
				rules.add(mod);
			}
		}

		List<IMod> choosedPlayers = new ArrayList<IMod>();
		List<IMod> choosedCards = new ArrayList<IMod>();
		IMod choosedRules = null;

		for (int i = 0; i < nbBot; i++) {
			choosed = false;
			Iterator<IMod> it = players.iterator();
			while (!choosed) {
				if (it.hasNext()) {
					IMod current = it.next();
					window.setModPanel(current, "Veuillez choisir une IA pour le robot: " + i);
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (choosed) {
						choosedPlayers.add(current);
					}
				} else {
					it = players.iterator();
				}
			}
		}

		Iterator<IMod> it2 = cards.iterator();
		while (it2.hasNext()) {
			choosed = false;
			IMod current = it2.next();
			window.setModPanel(current, "Veuillez choisir vos mods de cartes");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (choosed) {
				choosedCards.add(current);
			}
		}

		it2 = rules.iterator();
		choosed = false;
		while (!choosed) {
			if (it2.hasNext()) {
				IMod current = it2.next();
				window.setModPanel(current, "Veuillez choisir un mod de régles");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (choosed) {
					choosedRules = current;
				}
			} else {
				it2 = rules.iterator();
			}
		}

		List<String> names = new ArrayList<String>();

		for (int i = 0; i < nbPlayer; i++) {
			window.setNamePanel((byte) i);
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			names.add(name);
		}

		List<Object> returner = new ArrayList<Object>();

		it2 = choosedPlayers.iterator();
		while (it2.hasNext()) {
			returner.add(it2.next());
		}

		Iterator<String> it3 = names.iterator();
		while (it3.hasNext()) {
			returner.add(it3.next());
		}

		returner.add(choosedRules);
		returner.add(nbPlayer);

		it2 = choosedCards.iterator();
		while (it2.hasNext()) {
			returner.add(it2.next());
		}

		returner.add(nbBot);

		Object[] back = returner.toArray(new Object[returner.size()]);

		observed.notifyBack(NotEvent.CREATE_PARTY_MENU, back);
	}
}
