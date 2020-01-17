package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

/**
 * Vue et contrôleur pour le jeu en mode ligne de commande.
 * @author Edouard
 *
 */
public class CommandInterface implements IObserver {
	/**
	 * Liste de toutes les instances de Player de la partie.
	 */
	private Object[] players;
	/**
	 * Dernier object a avoir été observé.
	 */
	private Observable lastObserved;
	/**
	 * Joueur en cours. Permet de stocker le joueur en cours utile dans les méthodes CatchUp.
	 */
	private Player currentPlayer;
	/**
	 * Scanner d'entrée de la console.
	 */
	private Scanner input;

	/**
	 * Constructeur de la classe.
	 */
	public CommandInterface() {
		input = new Scanner(System.in);
	}

	@Override
	public void update(Observable observed, NotEvent callEvent, Object[] args) {
		if (callEvent == NotEvent.CREATE_PARTY_MENU) {
			setCreatePartyMenu((IMod[]) args, observed);
		} else if (callEvent == NotEvent.CATCH_UP_MENU || callEvent == NotEvent.CATCH_UP_MENU_BOT
				|| callEvent == NotEvent.CATCH_UP_MENU_ERROR || callEvent == NotEvent.CATCH_UP_MENU_SUCCESS) {
			setCatchUpMenu(callEvent, args, observed);
		} else if (callEvent == NotEvent.FACE_UP_MENU || callEvent == NotEvent.FACE_UP_MENU_BOT) {
			setFaceUpMenu(callEvent, args, observed);
		} else if (callEvent == NotEvent.CURRENT_PLAYER) {
			this.currentPlayer = (Player) args[0];
		} else if (callEvent == NotEvent.WIN_MENU) {
			setWinPartyMenu(NotEvent.WIN_MENU, args);
		} else if (callEvent == NotEvent.SHOW_TROPHY) {
			Pot pot = (Pot) args[0];
			System.out.println("Les troph�es sont : ");
			List<ICard> cards = pot.getTrophies();
			Iterator<ICard> it = cards.iterator();
			while (it.hasNext()) {
				ICard card = it.next();
				System.out.println(card.getName() + " " + card.getColor());
			}
		}
	}

	/**
	 * Méthode mettant en place le menu de choix des paramètres de la partie.
	 * @param mods Tous les mods chargés par le GameLoader.
	 * @param observed L'observable qui a déclenché la méthode.
	 */
	private void setCreatePartyMenu(IMod[] mods, Observable observed) {
		List<IMod> players = new ArrayList<IMod>();
		List<IMod> rules = new ArrayList<IMod>();
		List<IMod> cards = new ArrayList<IMod>();
		List<IMod> choosedPlayers = new ArrayList<IMod>();
		IMod choosedRule = null;
		List<IMod> choosedCards = new ArrayList<IMod>();
		List<String> playersName = new ArrayList<String>(4);

		for (IMod mod : mods) {
			if (mod.getType() == ModType.STRATEGY) {
				players.add(mod);
			} else if (mod.getType() == ModType.RULES) {
				rules.add(mod);
			} else {
				cards.add(mod);
			}
		}
		
		Byte nbPlayers = 0;
		Byte nbBots = 0;
		boolean tfPlayer = true;
		boolean tfBot = true;

		do {
			System.out.println("Entrez un nombre de joueurs entre 3 et 4 svp");
			if (input.hasNextByte()) {
				byte buffer = input.nextByte();
				if (buffer > 2 && buffer < 5) {
					System.out.println("Les informations sont bien prises en compte");
					nbPlayers = buffer;
					tfPlayer = false;
				} else {
					System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
				}
			} else {
				if (input.hasNext()) {
					input.next();
				}
				System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
			}
		} while (tfPlayer);

		do {
			System.out.println("Entrez le nombre de joueurs robots, minimum 1 joueur humain svp");
			if (input.hasNextByte()) {
				byte buffer = input.nextByte();
				if (nbPlayers - buffer > 0 && buffer > -1) {
					System.out.println("Les informations sont bien prises en compte");
					nbBots = buffer;
					tfBot = false;
				} else {
					System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
				}
			} else {
				if (input.hasNext()) {
					input.next();
				}
				System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
			}
		} while (tfBot);

		byte count = 0;

		while (count < nbBots && nbBots != 0) {
			System.out.println("Veuillez choisir une IA pour le robot " + count + ", r�pondre par 1/0");

			boolean choosed = false;
			byte counter = 0;
			while (choosed == false) {
				System.out.println(players.get(counter).getName());
				System.out.println(players.get(counter).getDescription());

				boolean tfStrategy = true;

				do {
					if (input.hasNextByte()) {
						byte buffer = input.nextByte();
						if (buffer == 1 || buffer == 0) {
							if (buffer == 1) {
								System.out.println("Les informations sont bien prises en compte");
								choosedPlayers.add(players.get(counter));
								choosed = true;
								tfStrategy = false;
							} else if (buffer == 0) {
								System.out.println("Les informations sont bien prises en compte");
								tfStrategy = false;
							}
						} else {
							System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
						}
					} else {
						if (input.hasNext()) {
							input.next();
						}
						System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
					}
				} while (tfStrategy);

				counter++;

				if (counter >= players.size()) {
					counter = 0;
				}
			}

			count++;
		}

		boolean choosed = false;
		byte counter = 0;

		while (choosed == false) {
			System.out.println("Veuillez choisir un IA mode de r�gle, r�pondre par 1/0");
			System.out.println(rules.get(counter).getName());
			System.out.println(rules.get(counter).getDescription());

			boolean tfRules = true;

			do {
				if (input.hasNextByte()) {
					byte buffer = input.nextByte();
					if (buffer == 1 || buffer == 0) {
						if (buffer == 1) {
							System.out.println("Les informations sont bien prises en compte");
							choosedRule = rules.get(counter);
							choosed = true;
							tfRules = false;
						} else {
							System.out.println("Les informations sont bien prises en compte");
							tfRules = false;
						}
					} else {
						System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
					}
				} else {
					if (input.hasNext()) {
						input.next();
					}
					System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
				}
			} while (tfRules);

			counter++;

			if (counter >= rules.size()) {
				counter = 0;
			}
		}

		Iterator<IMod> it = cards.iterator();

		while (it.hasNext()) {
			boolean tfCard = true;
			IMod current = it.next();

			do {
				System.out.println("Veuillez entrer 1/0 pour le choix des mods de carte");
				System.out.println(current.getName());
				System.out.println(current.getDescription());
				if (input.hasNextByte()) {
					byte buffer = input.nextByte();
					if (buffer == 1 || buffer == 0) {
						if (buffer == 1) {
							System.out.println("Les informations sont bien prises en compte");
							choosedCards.add(current);
							tfCard = false;
						} else {
							System.out.println("Les informations sont bien prises en compte");
							tfCard = false;
						}
					} else {
						System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
					}
				} else {
					if (input.hasNext()) {
						input.next();
					}
					System.out.println("Erreur d'entr�e veuillez entrer les informations � nouveau");
				}
			} while (tfCard);
		}

		for (int i = 0; i <= nbPlayers - 1; i++) {
			System.out.println("Veuillez entrer le nom du joueur : " + i);
			System.out.println("Le nom des joueurs humains en dernier svp");
			if (input.hasNext()) {
				playersName.add(input.next());
			}
		}

		List<Object> returner = new ArrayList<Object>();

		Iterator<IMod> it2 = choosedPlayers.iterator();

		// On retourne d'abord les joueurs, puis le nom des joueurs, puis les r�gles,
		// puis le nombre de joueurs, les cartes et enfin le nombre de joueurs robots
		while (it2.hasNext()) {
			returner.add(it2.next());
		}

		Iterator<String> it3 = playersName.iterator();

		while (it3.hasNext()) {
			returner.add(it3.next());
		}

		returner.add(choosedRule);
		returner.add(nbPlayers);

		it2 = choosedCards.iterator();

		while (it2.hasNext()) {
			returner.add(it2.next());
		}

		returner.add(nbBots);

		observed.notifyBack(NotEvent.CREATE_PARTY_MENU, returner.toArray(new Object[returner.size()]));
	}

	/**
	 * Méthode mettant en place de menu de choix de capture et permet d'effectuer les actions nécessaires.
	 * @param event L'event ayant déclenché la méthode.
	 * @param args Les arguments passés par l'observable qui a déclenché la méthode.
	 * @param observed L'observable qui a déclenché la méthode.
	 */
	private void setCatchUpMenu(NotEvent event, Object[] args, Observable observed) {
		if (event == NotEvent.CATCH_UP_MENU) {
			players = args;
			lastObserved = observed;
			boolean choosed = false;
			List<Player> playersList = new ArrayList<Player>(4);
			for (Object obj : args) {
				playersList.add((Player) obj);
			}
			int pos = findYou(playersList, currentPlayer);
			boolean everybody = everyChoosed(playersList, currentPlayer);
			while (!choosed) {
				System.out.println("Joueur : " + currentPlayer.getName());
				System.out.println(
						"Veuillez choisir un joueur � capturer, il est possible de vous capturer vous m�me seulement en dernier recour");
				System.out.println("Entrez le chiffre correspondant au joueur que vous souhaitez capturer");
				int counter = 0;
				for (Object obj : args) {
					Player current = (Player) obj;
					ICard currentCard = current.getFacedUpCard();
					if (currentCard != null) {
						System.out.println(counter + ". " + current.getName() + " : carte face visible : "
								+ currentCard.getName() + " " + currentCard.getColor());
					}
					counter++;
				}
				if (input.hasNextByte()) {
					byte next = input.nextByte();
					if (((next >= 0 && next <= args.length - 1) && pos != next) || (pos == next && everybody)) {
						System.out.println("Votre choix est bien pris en compte");
						System.out.println(
								"Voulez vous capturer la carte face visible ou une autre carte, r�pondre par 1/0");
						if (input.hasNextByte()) {
							byte nexte = input.nextByte();
							if (nexte == 1) {
								System.out.println("Votre choix est bien pris en compte");
								choosed = true;
								Object[] back = { (Player) args[next], true };
								observed.notifyBack(NotEvent.CATCH_UP_MENU, back);
							} else if (nexte == 0) {
								System.out.println("Votre choix est bien pris en compte");
								choosed = true;
								Object[] back = { playersList.get(next), false };
								observed.notifyBack(NotEvent.CATCH_UP_MENU, back);
							} else {
								System.out.println("L'entr�e n'est pas correcte");
							}
						} else {
							if (input.hasNext()) {
								input.next();
							}
							System.out.println("L'entr�e n'est pas correcte");
						}
					} else {
						System.out.println("L'entr�e n'est pas correcte");
					}
				} else {
					if (input.hasNext()) {
						input.next();
					}
					System.out.println("L'entr�e n'est pas correcte");
				}
			}
		} else if (event == NotEvent.CATCH_UP_MENU_BOT) {
			Player theplayer = (Player) args[0];
			Player thecaptured = (Player) args[1];
			boolean face = (boolean) args[2];
			System.out.println("Le joueur : " + theplayer.getName() + " a d�cid� de capturer le joueur : "
					+ thecaptured.getName() + " la carte captur� sera de face visible : " + face);
		} else if (event == NotEvent.CATCH_UP_MENU_ERROR) {
			System.out.println("Le choix du joueur a captur� n'est pas correct veuillez choisir � nouveau");
			setCatchUpMenu(NotEvent.CATCH_UP_MENU, players, lastObserved);
		} else if (event == NotEvent.CATCH_UP_MENU_SUCCESS) {
			ICard card = (ICard) args[0];
			System.out.println("La carte : " + card.getName() + " " + card.getColor() + " a correctement �t� captur�");
		}
	}

	/**
	 * Méthode mettant en place le menu de choix de carte face visible et permet de faire les actions nécessaires.
	 * @param event L'event ayant déclenché la méthode.
	 * @param args Les arguments passés par l'observable qui a déclenché la méthode.
	 * @param observed L'observable qui a déclenché la méthode.
	 */
	private void setFaceUpMenu(NotEvent event, Object[] args, Observable observed) {
		if (event == NotEvent.FACE_UP_MENU) {
			Player current = (Player) args[0];
			System.out.println("Joueur : " + current.getName());
			System.out.println(
					"Veuillez choisir une de vos cartes � mettre face visible, r�pondre par le num�ro de la carte");
			int counter = 0;
			Iterator<ICard> it = current.getHand().iterator();
			while (it.hasNext()) {
				ICard card = it.next();
				System.out.println(counter + ". " + card.getName() + " " + card.getColor());
				counter++;
			}
			boolean tf = true;
			do {
				if (input.hasNextByte()) {
					byte next = input.nextByte();

					if (next >= 0 && next <= current.getHand().size() - 1) {
						System.out.println("L'information est bien prise en compte");
						Object[] back = { next };
						observed.notifyBack(NotEvent.FACE_UP_MENU, back);
						tf = false;
					} else {
						System.out.println("L'entr�e est incorrecte");
					}
				} else {
					if (input.hasNext()) {
						input.next();
					}
					System.out.println("Erreur d'entr�e");
				}
			} while (tf);
		} else if (event == NotEvent.FACE_UP_MENU_BOT) {
			Player current = (Player) args[0];
			int choosedCard = (int) args[1];
			ICard choosed = current.getHand().get(choosedCard);
			System.out.println("Le joueur : " + current.getName() + " a choisi la carte : " + choosed.getName() + " "
					+ choosed.getColor());
			current.notifyBack(NotEvent.FACE_UP_MENU_BOT, null);
		}
	}

	/**
	 * Méthode mettant en place le menu de fin de partie affichant les scores et les cartes de chaque joueur.
	 * @param event L'event ayant déclenché la méthode.
	 * @param args Les arguments passés par l'observable qui a déclenché la méthode.
	 */
	private void setWinPartyMenu(NotEvent event, Object[] args) {
		System.out.println("Le joueur gagnant est : " + currentPlayer.getName());
		System.out.println("Voici tous les scores");
		List<Player> players = new ArrayList<Player>(4);
		for (Object obj : args) {
			Player current = (Player) obj;
			players.add(current);
		}

		for (Player player : players) {
			System.out.println(
					"Le joueur : " + player.getName() + " a obtenu un score de : " + player.calculateScore(players));
			System.out.println("Avec les cartes captur�s finales suivantes : ");
			Iterator<ICard> it = player.getCapturedCards().iterator();
			while (it.hasNext()) {
				ICard current = it.next();
				System.out.println(current.getName() + " " + current.getColor());
				System.out.println(current.endFaceValue(players, player));
				System.out.println(current.endSpecialFaceValue(players, player));
			}
		}
	}

	/**
	 * Méthode permettant d'obtenir l'index d'un joueur dans une liste.
	 * @param findings Liste de toutes les instances de Player de la partie.
	 * @param player Le joueur dont on cherche la présence.
	 * @return Renvoie l'index du joueur dans la liste.
	 */
	private int findYou(List<Player> findings, Player player) {
		for (int i = 0; i <= findings.size(); i++) {
			if (findings.get(i) == player) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Méthode permettant de savoir si player est le dernier à jouer ou non parmis findings.
	 * @param findings Liste de toutes les instances de Player de la partie.
	 * @param player Le joueur dont on cherche si il est le dernier à jouer.
	 * @return Renvoie si le joueur est le dernier à jouer ou non.
	 */
	private boolean everyChoosed(List<Player> findings, Player player) {
		int counter = 0;
		for (Player current : findings) {
			if (current.isCatchedUp()) {
				counter++;
			}
		}
		if (counter == findings.size() - 1) {
			return true;
		} else {
			return false;
		}

	}
}