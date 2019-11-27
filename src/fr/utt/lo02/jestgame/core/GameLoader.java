package fr.utt.lo02.jestgame.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarFile;

import fr.utt.lo02.jestgame.api.IMod;

public class GameLoader extends Observable {

	private Party currentParty;
	private IObserver currentInterface;
	private IMod[] loadedMods;

	public static void main(String[] args) {
		GameLoader gameLoader = new GameLoader(loadMods());
	}

	public static IMod[] loadMods() {
		List<String> names = new ArrayList<String>();
		List<URL> urls = new ArrayList<URL>();
		List<IMod> mods = new ArrayList<IMod>();

		// Récupération de tous les fichiers contenus dans mods en triant par leur
		// extension, uniquement les .jar sont conservés
		File[] files = new File("C:\\Users\\Edouard\\Documents\\Projet_LO02\\mods").listFiles(new ModFilter());

		// Récupération du nom de la classe principale dans chaques fichiers .jar
		for (File file : files) {
			JarFile jarFile = null;

			try {
				jarFile = new JarFile(file);

				// Ajout du nom de clase principale à la liste des noms par récupération dans le
				// manifest
				names.add(jarFile.getManifest().getMainAttributes().getValue("Mod-main-class"));

				// On ajoute ensuite l'url de tous les fichiers dont le nom de classe principale
				// a été correctement récupéré
				urls.add(file.toURI().toURL());

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					// On ferme le fichier jarFile en cour
					jarFile.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// On classload toutes les classes des fichiers de urls
		ClassLoader loader = new URLClassLoader(urls.toArray(new URL[urls.size()]), GameLoader.class.getClassLoader());

		// On récupére ensuite toutes les classes principales à charger
		Iterator<String> it = names.iterator();

		while (it.hasNext()) {
			// On récupére les classes principales
			try {
				Class<?> modClass = Class.forName(it.next(), true, loader);

				// On cast en IMod si possible et ensuite on instancie et on ajoute aux mods
				if (IMod.class.isAssignableFrom(modClass)) {
					@SuppressWarnings("unchecked")
					Class<IMod> castedClass = (Class<IMod>) modClass;

					try {
						mods.add(castedClass.getConstructor().newInstance());

					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		// On retourne enfin un tableau de tous les mods chargés
		return mods.toArray(new IMod[mods.size()]);
	}

	public GameLoader(IMod[] mods) {
		this.loadedMods = mods;
		currentInterface = new CommandInterface();
		addObserver(currentInterface);
		notifyAll(NotEvent.CREATE_PARTY_MENU, loadedMods);
	}

	@Override
	public void notifyBack(NotEvent backCallEvent, Object[] backArgs) {
		if (backCallEvent == NotEvent.CREATE_PARTY_MENU) {
			List<IMod> players = new ArrayList<IMod>();
			List<String> names = new ArrayList<String>();
			IMod rules;
			List<IMod> cards = new ArrayList<IMod>();

			int count = 0;
			while (IMod.class.isAssignableFrom(backArgs[count].getClass())) {
				players.add((IMod) backArgs[count]);
				count++;
			}

			while (String.class.isAssignableFrom(backArgs[count].getClass())) {
				names.add((String) backArgs[count]);
				count++;
			}

			rules = (IMod) backArgs[count];
			count++;
			count++;

			while (IMod.class.isAssignableFrom(backArgs[count].getClass())) {
				cards.add((IMod) backArgs[count]);
				count++;
			}

			while(players.size() != names.size()) {
				players.add(null);
			}
			
			currentParty = new Party(players, cards, rules, currentInterface, names);
			startGame();
		}

	}

	public void startGame() {
		currentParty.beginParty();
	}
}
