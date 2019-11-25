package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fr.utt.lo02.jestgame.api.ICard;
import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

public class CommandInterface implements IObserver {
	/**
	 *
	 */
	private Object[] players;
	private Observable lastObserved;

	@Override
	public void update(Observable observed, NotEvent callEvent, Object[] args) {
		if (callEvent == NotEvent.CREATE_PARTY_MENU) {
			setCreatePartyMenu((IMod[]) args, observed);
		} else if (callEvent == NotEvent.CATCH_UP_MENU || callEvent == NotEvent.CATCH_UP_MENU_BOT
				|| callEvent == NotEvent.CATCH_UP_MENU_ERROR || callEvent == NotEvent.CATCH_UP_MENU_SUCCESS) {
			setCatchUpMenu(callEvent, args, observed);
		} else if(callEvent == NotEvent.FACE_UP_MENU || callEvent == NotEvent.FACE_UP_MENU_BOT) {
			setFaceUpMenu(callEvent, args, observed);
		}
	}

	/**
	 * @param mods
	 * @param observed
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

		Scanner input = new Scanner(System.in);
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
					System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
				}
			} else {
				System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
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
					System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
				}
			} else {
				System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
			}
		} while (tfBot);

		byte count = 1;

		while (count == nbBots && nbBots != 0) {
			System.out.println("Veuillez choisir une IA pour le robot " + count + " , répondre par o/n");

			boolean choosed = false;
			byte counter = 0;
			while (choosed == false) {
				System.out.println(players.get(counter).getName());
				System.out.println(players.get(counter).getDescription());

				boolean tfStrategy = true;

				do {
					if (input.hasNext()) {
						String buffer = input.next();
						if (buffer == "o" || buffer == "n") {
							if (buffer == "o") {
								System.out.println("Les informations sont bien prises en compte");
								choosedPlayers.add(players.get(counter));
								choosed = true;
								tfStrategy = false;
							} else {
								System.out.println("Les informations sont bien prises en compte");
								tfStrategy = false;
							}
						} else {
							System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
						}
					} else {
						System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
					}
				} while (tfStrategy);

				counter++;

				if (counter >= players.size() - 1) {
					counter = 0;
				}
			}

			count++;
		}

		boolean choosed = false;
		byte counter = 0;

		while (choosed == false) {
			System.out.println(rules.get(counter).getName());
			System.out.println(rules.get(counter).getDescription());

			boolean tfRules = true;

			do {
				if (input.hasNext()) {
					String buffer = input.next();
					if (buffer == "o" || buffer == "n") {
						if (buffer == "o") {
							System.out.println("Les informations sont bien prises en compte");
							choosedRule = rules.get(counter);
							choosed = true;
							tfRules = false;
						} else {
							System.out.println("Les informations sont bien prises en compte");
							tfRules = false;
						}
					} else {
						System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
					}
				} else {
					System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
				}
			} while (tfRules);

			counter++;

			if (counter >= players.size() - 1) {
				counter = 0;
			}
		}

		Iterator<IMod> it = cards.iterator();

		while (it.hasNext()) {
			boolean tfCard = true;
			IMod current = it.next();

			do {
				System.out.println("Veuillez entrer o/n pour le choix des mods de carte");
				System.out.println(current.getName());
				System.out.println(current.getDescription());
				if (input.hasNext()) {
					String buffer = input.next();
					if (buffer == "o" || buffer == "n") {
						if (buffer == "o") {
							System.out.println("Les informations sont bien prises en compte");
							choosedCards.add(current);
							tfCard = false;
						} else {
							System.out.println("Les informations sont bien prises en compte");
							tfCard = false;
						}
					} else {
						System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
					}
				} else {
					System.out.println("Erreur d'entrée veuillez entrer les informations à nouveau");
				}
			} while (tfCard);
		}

		for (int i = 0; i <= nbPlayers; i++) {
			if (input.hasNext()) {
				System.out.println("Veuillez entrer le nom du joueur : " + i);
				System.out.println("Le nom des joueurs humains en premier svp");
				playersName.add(input.next());
			}
		}

		input.close();

		List<Object> returner = new ArrayList<Object>();

		Iterator<IMod> it2 = choosedPlayers.iterator();

		// On retourne d'abord les joueurs, puis le nom des joueurs, puis les régles,
		// puis le nombre de joueurs, les cartes et enfin le nombre de joueurs robots
		while (it2.hasNext()) {
			returner.add(it2.hasNext());
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

	private void setCatchUpMenu(NotEvent event, Object[] args, Observable observed) {
		Scanner input = new Scanner(System.in);
		players = args;
		lastObserved = observed;

		if (event == NotEvent.CATCH_UP_MENU) {
			boolean choosed = false;
			while (!choosed) {
				System.out.println("Veuillez choisir un joueur à capturer, il est possible de vous capturer vous même");
				System.out.println("Entrez le chiffre correspondant au joueur que vous souhaitez capturer");
				int counter = 0;
				for (Object obj : args) {
					Player current = (Player) obj;
					ICard currentCard = current.getFacedUpCard();
					System.out.println(counter + ". " + current.getName() + " : carte face visible : "
							+ currentCard.getName() + " " + currentCard.getColor());
				}
				if (input.hasNextByte()) {
					byte next = input.nextByte();
					if (next >= 0 && next <= args.length - 1) {
						System.out.println("Votre choix est bien pris en compte");
						System.out.println(
								"Voulez vous capturer la carte face visible ou une autre carte, répondre par v/a");
						if (input.hasNext()) {
							String nexte = input.next();
							if (nexte == "v") {
								System.out.println("Votre chois est bien pris en compte");
								choosed = true;
								Object[] back = { next, true };
								observed.notifyBack(NotEvent.CATCH_UP_MENU, back);
							} else if (nexte == "a") {
								System.out.println("Votre choix est bien pris en compte");
								choosed = true;
								Object[] back = { next, false };
								observed.notifyBack(NotEvent.CATCH_UP_MENU, back);
							} else {
								System.out.println("L'entrée n'est pas correcte");
							}
						}
					}
					System.out.println("L'entrée n'est pas correcte");
				}
				System.out.println("L'entrée n'est pas correcte");
			}
		} else if (event == NotEvent.CATCH_UP_MENU_BOT) {
			Player theplayer = (Player) args[0];
			Player thecaptured = (Player) args[1];
			boolean face = (boolean) args[2];
			System.out.println("Le joueur : " + theplayer.getName() + " a décidé de capturer le joueur : "
					+ thecaptured.getName() + " la carte capturé sera de face visible : " + face);
		} else if (event == NotEvent.CATCH_UP_MENU_ERROR) {
			System.out.println("Le choix du joueur a capturé n'est pas correct veuillez choisir à nouveau");
			setCatchUpMenu(NotEvent.CATCH_UP_MENU, players, lastObserved);
		} else if(event == NotEvent.CATCH_UP_MENU_SUCCESS) {
			ICard card = (ICard) args[0];
			System.out.println("La carte : " + card.getName() + " " + card.getColorValue() + " a correctement été capturé");
		}
		input.close();
	}
	
	private void setFaceUpMenu(NotEvent event, Object[] args, Observable observed) {
		
	}
}