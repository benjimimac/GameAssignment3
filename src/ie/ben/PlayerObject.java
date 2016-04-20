package ie.ben;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class PlayerObject {

	//
	protected int player;
	protected Color colour;
	protected ArrayList<Point> legalMoves = new ArrayList<Point>();
	protected int pieceCount;
	
	//Contructors
	PlayerObject(int player) {

		initPlayer(player);
	}

	PlayerObject(int player, int pieceCount) {

		initPlayer(player, pieceCount);
	}

	// Getters and Setters for all fields
	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public ArrayList<Point> getLegalMoves() {
		return legalMoves;
	}

	public void setLegalMoves(ArrayList<Point> legalMoves) {
		this.legalMoves = legalMoves;
	}

	public int getPieceCount() {
		return pieceCount;
	}

	public void setPieceCount(int pieceCount) {
		this.pieceCount += pieceCount;
	}

	//First initialisation method for a new game
	private void initPlayer(int player) {
		
		setPlayerColour(player);

		// In a new game both players start with 2 pieces each
		setPieceCount(2);
	}

	//The second initialisation method for a saved game
	private void initPlayer(int player, int pieceCount) {

		setPlayerColour(player);

		setPieceCount(pieceCount);
	}

	protected void setPlayerColour(int player) {

		if (player == 0) {
			setColour(Color.black);
		} else {
			setColour(Color.white);
		}
	}
}
