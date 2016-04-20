package ie.ben;

import java.awt.Point;
import java.util.ArrayList;

public class AIPlayer extends PlayerObject {

	// private DummyTile[][] dummyTiles;
	// public ArrayList<DummyTile> legalMovesDummy;
	// public ArrayList<DummyTile> potentialMovesDummy;
	// private int currentPlayer;

	public AIPlayer(int player) {

		super(player);
	}

	public AIPlayer(int player, int pieceCount) {

		super(player, pieceCount);
	}

}