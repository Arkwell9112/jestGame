package fr.utt.lo02.jestgame.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fr.utt.lo02.jestgame.api.IMod;
import fr.utt.lo02.jestgame.api.ModType;

public class CommandInterface implements IObserver {
	public static void main(String[] args) {
		CommandInterface test = new CommandInterface();
		test.setCreatePartyMenu(null, null);
	}

	@Override
	public void update(Observable observed, NotEvent callEvent, Object[] args) {
		if (callEvent == NotEvent.MAIN_MENU) {
			setCreatePartyMenu((IMod[]) args, observed);
		}
	}

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
					System.out.println("Le nombre de joueurs est bien pris en compte");
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
					System.out.println("Le nombre de joueurs robots est bien pris en compte");
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
								choosedPlayers.add(players.get(counter));
								choosed = true;
								tfStrategy = false;
							} else {
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
							choosedRule = rules.get(counter);
							choosed = true;
							tfRules = false;
						} else {
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
							choosedCards.add(current);
							tfCard = false;
						} else {
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

		for (int i = 0; i <= 0; i++) {
			if(input.hasNext()) {
				playersName.add(input.next());
			}
		}
		
		input.close();
		
		List<Object> returner = new ArrayList<Object>();
		
		Iterator<IMod> it2 = choosedPlayers.iterator();
		
		while(it2.hasNext()) {
			returner.add(it2.hasNext());
		}
		
		returner.add(choosedRule);
		it2 = choosedCards.iterator();
			
		while(it2.hasNext()) {
			returner.add(it2.next());
		}
		
		Iterator<String> it3 = playersName.iterator();
		
		while(it3.hasNext()) {
			returner.add(it3.next());
		}
		
		returner.add(nbPlayers);
		
		observed.notifyBack(NotEvent.MAIN_MENU, returner.toArray(new Object[returner.size()]));
	}
}
