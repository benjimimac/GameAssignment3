package ie.ben;

import java.awt.Color;

public class Settings {

	//setting fields for game and general settings
	private int numPlayers;
	private Color[][] themes;
	private Color colour;
	private int playerNumber;
	
	public Settings() {
		
		initSettings();
	}
	
	private void initSettings() {
		
		themes = new Color[1][6];	//Colour themes can be selected
		playerNumber = 0;	//Black is the default first move
	}
	
	public int getPlayerNumber() {
		
		return playerNumber;
	}
}
