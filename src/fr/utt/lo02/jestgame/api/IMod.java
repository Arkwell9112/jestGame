package fr.utt.lo02.jestgame.api;

public interface IMod {
	public String getName();
	public String getDescription();
	public ModType getType();
	public Object[] getInstance();
}
