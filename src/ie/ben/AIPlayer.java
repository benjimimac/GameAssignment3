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

	public void selectMove() {

		int selectIndex = 0;
		int currentPlayer = GameEngine.currentPlayer;
		int score1 = ReversiGame.players.get(0).getPieceCount();
		int score2 = ReversiGame.players.get(1).getPieceCount();

		for (int index = 0; index < GameEngine.legalMoves.size(); index++) {
			DummyTile[][] dummyTiles = new DummyTile[8][8];
			ArrayList<DummyTile> dummyLegalMoves = new ArrayList<DummyTile>();
			ArrayList<DummyTile> dummyPotentialMoves = new ArrayList<DummyTile>();

			for (int row = 0; row < dummyTiles.length; row++) {

				for (int col = 0; col < dummyTiles.length; col++) {

					DummyTile dummyTile = new DummyTile(new Point(row, col), ReversiGame.tiles[row][col].isOccupied(),
							ReversiGame.tiles[row][col].getOccupiedBy(),
							ReversiGame.tiles[row][col].getOccupiedNeighbours());

					dummyTiles[row][col] = dummyTile;

				}
			}

			// dummyPotentialMoves = new ArrayList<DummyTile>();
			for (Tile potentialMove : GameEngine.potentialMoves) {

				dummyPotentialMoves.add(dummyTiles[potentialMove.getLocation().x][potentialMove.getLocation().y]);
			}

			// dummyLegalMoves = new ArrayList<DummyTile>();
			for (Tile legalMove : GameEngine.legalMoves) {

				dummyLegalMoves.add(dummyTiles[legalMove.getLocation().x][legalMove.getLocation().y]);
			}

			// System.out.println(dummyLegalMoves.size());

			int weight = getMoveWeight(dummyTiles, dummyPotentialMoves, dummyLegalMoves, currentPlayer, score1, score2);
			// System.out.println(weight);
		}

	}

	public int getMoveWeight(DummyTile[][] dummyTiles, ArrayList<DummyTile> dummyPotentialMoves,
			ArrayList<DummyTile> dummyLegalMoves, int currentPlayer, int score1, int score2) {

		int sweight = 0;
		
		if (dummyLegalMoves.isEmpty()) {

			return score2 - score1;
		} else {

			for (int index = 0; index < 1 /* dummyLegalMoves.size() */; index++) {

				DummyTile[][] tempDummyTiles = new DummyTile[8][8];
				ArrayList<DummyTile> tempDummyLegalMoves = new ArrayList<DummyTile>();
				ArrayList<DummyTile> tempDummyPotentialMoves = new ArrayList<DummyTile>();

				for (int row = 0; row < dummyTiles.length; row++) {

					for (int col = 0; col < dummyTiles.length; col++) {

						DummyTile dummyTile = new DummyTile(new Point(row, col), dummyTiles[row][col].isOccupied(),
								dummyTiles[row][col].getOccupiedBy(), dummyTiles[row][col].getOccupiedNeighbours());

						tempDummyTiles[row][col] = dummyTile;

					}
				}

				for (DummyTile dummyPotentialMove : dummyPotentialMoves) {
					if (!tempDummyPotentialMoves.contains(
							dummyTiles[dummyPotentialMove.getLocation().x][dummyPotentialMove.getLocation().y])) {
						tempDummyPotentialMoves.add(
								dummyTiles[dummyPotentialMove.getLocation().x][dummyPotentialMove.getLocation().y]);
					}
				}

				for (DummyTile dummyLegalMove : dummyLegalMoves) {
					if (!tempDummyLegalMoves
							.contains(dummyTiles[dummyLegalMove.getLocation().x][dummyLegalMove.getLocation().y])) {
						tempDummyLegalMoves
								.add(dummyTiles[dummyLegalMove.getLocation().x][dummyLegalMove.getLocation().y]);
					}
					System.out.println(dummyLegalMove.getLocation());
				}
				System.out.println();

			}
		}
		return -1;
	}

}