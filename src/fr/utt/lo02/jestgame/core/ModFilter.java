package fr.utt.lo02.jestgame.core;

import java.io.File;
import java.io.FileFilter;

public class ModFilter implements FileFilter {
	@Override
	public boolean accept(File file) {
		return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
	}
	
}
