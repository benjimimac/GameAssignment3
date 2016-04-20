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
				if (!dummyPotentialMoves
						.contains(dummyTiles[potentialMove.getLocation().x][potentialMove.getLocation().y])) {

					dummyPotentialMoves.add(dummyTiles[potentialMove.getLocation().x][potentialMove.getLocation().y]);
				}
			}

			// dummyLegalMoves = new ArrayList<DummyTile>();
			for (Tile legalMove : GameEngine.legalMoves) {
				if (!dummyLegalMoves.contains(dummyTiles[legalMove.getLocation().x][legalMove.getLocation().y])) {

					dummyLegalMoves.add(dummyTiles[legalMove.getLocation().x][legalMove.getLocation().y]);
				}
			}

			// System.out.println(dummyLegalMoves.size());

			int weight = getMoveWeight(dummyTiles, dummyPotentialMoves, dummyLegalMoves, currentPlayer, score1, score2);
			System.out.println("weight is " + weight);
		}

	}

	public int getMoveWeight(DummyTile[][] dummyTiles, ArrayList<DummyTile> dummyPotentialMoves,
			ArrayList<DummyTile> dummyLegalMoves, int currentPlayer, int score1, int score2) {

		int weight = 0;
		int x = 0;
System.out.println("LEGAL MOVES IS " + dummyLegalMoves.size());
		if (dummyLegalMoves.isEmpty()) {//{dummyLegalMoves.isEmpty()) {

			return score2 - score1;
		} else {
			
			for (int index = 0; index < dummyLegalMoves.size(); index++) {

				DummyTile[][] tempDummyTiles = new DummyTile[8][8];
				ArrayList<DummyTile> tempDummyLegalMoves = new ArrayList<DummyTile>();
				ArrayList<DummyTile> tempDummyPotentialMoves = new ArrayList<DummyTile>();
				int tempScore1 = score1;
				int tempScore2 = score2;
				int temp = 0;
				for (int row = 0; row < dummyTiles.length; row++) {

					for (int col = 0; col < dummyTiles.length; col++) {

						DummyTile dummyTile = new DummyTile(new Point(row, col), dummyTiles[row][col].isOccupied(),
								dummyTiles[row][col].getOccupiedBy(), dummyTiles[row][col].getOccupiedNeighbours());

						tempDummyTiles[row][col] = dummyTile;

					}
				}

				for (DummyTile dummyPotentialMove : dummyPotentialMoves) {
					if (!tempDummyPotentialMoves.contains(
							tempDummyTiles[dummyPotentialMove.getLocation().x][dummyPotentialMove.getLocation().y])) {
						tempDummyPotentialMoves.add(
								tempDummyTiles[dummyPotentialMove.getLocation().x][dummyPotentialMove.getLocation().y]);
					}
				}

				for (DummyTile dummyLegalMove : dummyLegalMoves) {
					if (!tempDummyLegalMoves
							.contains(tempDummyTiles[dummyLegalMove.getLocation().x][dummyLegalMove.getLocation().y])) {
						tempDummyLegalMoves
								.add(tempDummyTiles[dummyLegalMove.getLocation().x][dummyLegalMove.getLocation().y]);
					}
				}

				tempDummyLegalMoves.get(index).setOccupied(true);
				tempDummyLegalMoves.get(index).setOccupiedBy(currentPlayer);
				tempDummyPotentialMoves.remove(tempDummyLegalMoves.get(index));

				setDummyNeighboursOccupiedNeighbours(tempDummyTiles, tempDummyPotentialMoves,
						dummyLegalMoves.get(index).getLocation());

				temp = takeDummyTiles(
						new Point(tempDummyLegalMoves.get(index).getLocation().x,
								tempDummyLegalMoves.get(index).getLocation().y),
						tempDummyTiles, tempDummyLegalMoves, currentPlayer);
System.out.println("temp is " + temp);
				if (currentPlayer == 0) {

					tempScore1 += temp;// + 1;
					tempScore2 -= temp;
				} else {

					tempScore1 -= temp;
					tempScore2 += temp;// + 1;
				}
				System.out.println("score2 " + score1 + ", score2 " + score2);
				tempDummyLegalMoves = setLegalMovesDummy(tempDummyPotentialMoves, tempDummyTiles, currentPlayer);
				System.out.println("legal size " + tempDummyLegalMoves.size());

				//dummyLegalMoves.get(index).setWeight(
						getMoveWeight(tempDummyTiles, tempDummyPotentialMoves,
						tempDummyLegalMoves, (currentPlayer + 1) % 2, tempScore1, tempScore2);//);
			}
//			x = 0;
//			for(DummyTile dlm : dummyLegalMoves) {
//				
//				if(dlm.getWeight() > x) {
//					
//					x = dlm.getWeight();
//				}
//			}
			return 10;
		}
		
	}

	public void setDummyNeighboursOccupiedNeighbours(DummyTile[][] dummyTiles, ArrayList<DummyTile> potentialMovesDummy,
			Point location) {

		int index = 7;

		for (int row = location.getLocation().x - 1; row <= location.getLocation().x + 1; row++) {

			for (int col = location.getLocation().y - 1; col <= location.getLocation().y + 1; col++) {

				if (row < 0 || row > 7) {

					index -= 3;
					break;
				}

				if (col < 0 || col > 7) {

					index--;
					continue;
				}

				if (location.getLocation().equals(new Point(row, col))) {

					continue;
				}

				dummyTiles[row][col].setOccupiedNeighbour(index);

				if (!dummyTiles[row][col].isOccupied() && !potentialMovesDummy.contains(dummyTiles[row][col])) {
					potentialMovesDummy.add(dummyTiles[row][col]);
				}

				index--;
			}
		}
	}

	public int takeDummyTiles(Point location, DummyTile[][] dummyTiles, ArrayList<DummyTile> dummyLegalMoves,
			int currentPlayer) {

		int index = 0;
		int tilesTaken = 0;

		// for(int row = getLocation().x - 1; row <= getLocation().x + 1; row++)
		// {
		for (int row = -1; row <= 1; row++) {

			// for(int col = getLocation().y - 1; col <= getLocation().y + 1;
			// col++) {
			for (int col = -1; col <= 1; col++) {

				if (location.getLocation().x + row < 0 || location.getLocation().x + row > 7) {
					// System.out.println("row break");
					index += 3;
					break;
				}

				if (location.getLocation().y + col < 0 || location.getLocation().y + col > 7) {
					// System.out.println("col continue");
					index++;
					continue;
				}

				if (location.equals(new Point(location.getLocation().x + row, location.getLocation().y + col))) {

					continue;
				}

				if (dummyTiles[location.getLocation().x][location.getLocation().y].getOccupiedNeighbour(index)) {
					// System.out.println("Tile.takeTiles method if statement -
					// index is " + index + " - row/col is " + row + ", " +
					// col);
					if (checkLegalMoveDummy(location, row, col, dummyTiles, dummyLegalMoves, currentPlayer)) {

						tilesTaken += takeTiles(row, col, location, dummyTiles, currentPlayer);
					}
				}

				index++;
			}
		}
System.out.println("tilesTaken 1 is " + tilesTaken);
		return tilesTaken;
	}

	public boolean checkLegalMoveDummy(Point location, int addX, int addY, DummyTile[][] dummyTiles,
			ArrayList<DummyTile> legalMovesDummy, int currentPlayer) {

		// Instantiate the leagalMoves ArrayList
		// legalMoves = new ArrayList<Tile>();

		// Create a temporary Point object set to the location of the "next"
		// tile
		Point point = new Point(location.x + addX, location.y + addY);

		// if()
		// Check initial tile isn't occupied by the current player
		// System.out.println("checkLegalMove point - " + point);
		if (dummyTiles[point.getLocation().x][point.getLocation().y].getOccupiedBy() != currentPlayer) {

			// Loop while the currently selected tile is occupied
			while (dummyTiles[point.getLocation().x][point.getLocation().y].isOccupied()) {

				// System.out.println(addX + ", " + addY);
				if (point.getLocation().x + addX < 0 || point.getLocation().x + addX > 7
						|| point.getLocation().y + addY < 0 || point.getLocation().y + addY > 7) {

					break;
				}

				// Check if the next tile is occupied by the current player
				if (dummyTiles[point.getLocation().x + addX][point.getLocation().y + addY].isOccupied()
						&& dummyTiles[point.getLocation().x + addX][point.getLocation().y + addY]
								.getOccupiedBy() == currentPlayer) {
					System.out.println("YES");
					// Means this is a legal move
					return true;
				} else {
					
					System.out.println("NO");
				}

				// Translate point by (addX, addY)
				point.translate(addX, addY);
			}
		}

		// Means this is not a legal move
		return false;
	}

	public int takeTiles(int addX, int addY, Point location, DummyTile[][] dummyTiles, int currentPlayer) {

		Point point = new Point(location.getLocation().x + addX, location.getLocation().y + addY);
		int tilesTaken = 0;

		while (dummyTiles[point.getLocation().x][point.getLocation().y].isOccupied()
				&& dummyTiles[point.getLocation().x][point.getLocation().y].getOccupiedBy() != currentPlayer) {

			dummyTiles[point.getLocation().x][point.getLocation().y].setOccupiedBy(currentPlayer);
			// dummyTiles[point.getLocation().x][point.getLocation().y].revalidate();
			// dummyTiles[point.getLocation().x][point.getLocation().y].repaint();
			// System.out.println("takeTiles method " + (point.x) + ", " +
			// (point.y));
			// System.out.println();
			point.translate(addX, addY);
			tilesTaken += 1;
		}
System.out.println("tilesTaken 2 is " + tilesTaken);
		return tilesTaken;
	}

	public ArrayList<DummyTile> setLegalMovesDummy(ArrayList<DummyTile> potentialMovesDummy, DummyTile[][] dummyTiles,
			int currentPlayer) {

		// Initialise the legalMoves ArrayList - every move it has to be
		// re-initialised
		ArrayList<DummyTile> legalMovesDummy = new ArrayList<DummyTile>();

		// Loop through the potentialMoves ArrayList and check for legal moves
		// for the current player
		for (DummyTile potentialMoveDummy : potentialMovesDummy) {

			// Initialise a temporary index variable to 0
			int index = 0;

			// Use a nested loop to cycle through each row and col of the
			// surrounding tiles to check for legal moves
			for (int row = -1; row <= 1; row++) {
				// for(int row = potentialMove.getLocation().x - 1; row <=
				// potentialMove.getLocation().x + 1; row++) {

				for (int col = -1; col <= 1; col++) {
					// for(int col = potentialMove.getLocation().y - 1; col <=
					// potentialMove.getLocation().y + 1; col++) {

					if ((potentialMoveDummy.getLocation().x < 1 && row < 0)
							|| (potentialMoveDummy.getLocation().x > 6 && row > 0)) {
						// if(row < 0 || row > 7) {

						index += 3;
						// System.out.println("Inside the row break statement");
						break;
					}

					if ((potentialMoveDummy.getLocation().y < 1 && col < 0)
							|| (potentialMoveDummy.getLocation().y > 6 && col > 0)) {
						// if(col < 0 || col > 7) {

						index++;
						continue;
					}

					// If (0, 0) continue to next iteration of loop
					if (row == 0 && col == 0) {
						// if(potentialMove.getLocation().equals(new Point(row,
						// col))) {
						continue;
					}

					// System.out.println("index is " + index + ", location is "
					// + potentialMove.getLocation());
					// System.out.println("index is " + index + ", location is
					// (" + row + ", " + col + ")");
					// If the neighbour at (row, col) is occupied - check if
					// legal move
					if (potentialMoveDummy.getOccupiedNeighbour(index)) {

						if (checkLegalMoveDummy(potentialMoveDummy.getLocation(), row, col, dummyTiles, legalMovesDummy,
								currentPlayer)) {

							// addToLegalMoves(potentialMove);
							if (!legalMovesDummy.contains(potentialMoveDummy)) {
								legalMovesDummy.add(potentialMoveDummy);
							}
						}
					}

					index++;
				}
			}
		}

		return legalMovesDummy;
	}
}