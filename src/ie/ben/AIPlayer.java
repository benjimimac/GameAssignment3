package ie.ben;

import java.awt.Point;
import java.util.ArrayList;

public class AIPlayer extends PlayerObject {

	private DummyTile[][] dummyTiles;
	public ArrayList<DummyTile> legalMovesDummy;
	public ArrayList<DummyTile> potentialMovesDummy;
	private int currentPlayer;

	public AIPlayer(int player) {

		super(player);
	}

	public AIPlayer(int player, int pieceCount) {

		super(player, pieceCount);
	}

	public void createDummyTiles() {

		dummyTiles = new DummyTile[ReversiGame.TILES_PER][ReversiGame.TILES_PER];

		for (int row = 0; row < ReversiGame.TILES_PER; row++) {

			for (int col = 0; col < ReversiGame.TILES_PER; col++) {

				DummyTile dummyTile;

				if (ReversiGame.tiles[row][col].isOccupied()) {

					dummyTile = new DummyTile(new Point(row, col), true, ReversiGame.tiles[row][col].getOccupiedBy());
				} else {

					dummyTile = new DummyTile(new Point(row, col), false, 0);
				}

				dummyTiles[row][col] = dummyTile;
			}
		}
	}

	public void createLegalMovesDummy() {

		legalMovesDummy = new ArrayList<DummyTile>();

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

						if (checkLegalMoveDummy(GameEngine.currentPlayer, potentialMoveDummy.getLocation(), row, col, dummyTiles, legalMovesDummy)) {

							addToLegalMovesDummy(potentialMoveDummy);
						}
					}

					index++;
				}
			}
		}
	}

	public boolean checkLegalMoveDummy(/* Color colour,*/ int player, Point location, int addX, int addY, DummyTile[][] dummyTiles, ArrayList<DummyTile> legalMovesDummy) {

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

					// Means this is a legal move
					return true;
				}

				// Translate point by (addX, addY)
				point.translate(addX, addY);
			}
		}

		// Means this is not a legal move
		return false;
	}

	public void initPotentialMovesDummy() {

		// Instantiate the potentialMoves ArrayList
		potentialMovesDummy = new ArrayList<DummyTile>();

		// Loop through the global tiles object array to add tiles to the
		// potentialMoves arrayList
		for (int row = 0; row < dummyTiles.length; row++) {

			for (int col = 0; col < dummyTiles[row].length; col++) {

				// Check if the current tile has at least one neighbour tile
				// that is occupied
				if (dummyTiles[row][col].isPotentialMove()) {

					// If potentailMoves does not contain the reference to the
					// tile location add it
					if (!containsPotentialMoveDummy(dummyTiles[row][col], potentialMovesDummy)) {

						// potentialMoves.add(ReversiGame.tiles[row][col].getLocation());

						// Call the method to add the tiles location point to
						// the potentialMoves ArrayList
						addToPotentialMovesDummy(dummyTiles[row][col], potentialMovesDummy);
					}
				}
			}
		}
	}

	// Method that checks if a tiles location point is already in the
	// potentialMoves ArrayList
	// Returns true or false
	public boolean containsPotentialMoveDummy(DummyTile dummyTile, ArrayList<DummyTile> potentialMovesDummy) {

		if (potentialMovesDummy.contains(dummyTile)) {

			return true;
		}
		return false;
	}

	// Method that adds a tiles location point to the potentialMoves ArrayList
	public void addToPotentialMovesDummy(DummyTile dummyTile, ArrayList<DummyTile> dummyPotentialMoves) {

		// Add the new point to the ArrayList potentialMoves
		dummyPotentialMoves.add(dummyTile);

		// System.out.println("Current potential moves are :");
		// for (int i = 0; i < potentialMoves.size(); i++) {
		// System.out.println(potentialMoves.get(i).getLocation());
		// }
	}

	public static void removeFromPotentialMovesDummy(DummyTile dummyTile, ArrayList<DummyTile> dummyPotentialMoves) {

		// Remove the point from potential moves
		dummyPotentialMoves.remove(dummyTile);
	}

//	// If a tile is occupied set each neighbours occupiedNeighbours array at the
//	// relevant index
//	public void setNeighboursOccupiedNeighbours(DummyTile[][] dummyTiles, int row, int col,
//			ArrayList<DummyTile> dummyPotentialMoves) {
//
//		int index = 7;
//
//		for (int rows = row - 1; rows <= row + 1; rows++) {
//
//			for (int cols = col - 1; cols <= col + 1; cols++) {
//
//				if (rows < 0 || rows > 7) {
//
//					index -= 3;
//					break;
//				}
//
//				if (cols < 0 || cols > 7) {
//
//					index--;
//					continue;
//				}
//
//				if (row == rows && col == cols) {
//
//					continue;
//				}
//
//				dummyTiles[rows][cols].setOccupiedNeighbour(index);
//
//				if (!dummyTiles[rows][cols].isOccupied() && !containsPotentialMoveDummy(dummyTiles[rows][cols])) {
//					addToPotentialMovesDummy(dummyTiles[rows][cols], dummyPotentialMoves);
//				}
//
//				index--;
//			}
//		}
//	}

	// Method that checks if a tile is already in the
	// potentialMoves ArrayList
	// Returns true or false
	public boolean containsLegalMoveDummy(DummyTile dummyTile) {

		if (legalMovesDummy.contains(dummyTile)) {

			return true;
		}
		return false;
	}

	public int selectMove() {
		ArrayList<DummyTile> legalMovesDummy = new ArrayList<DummyTile>();
		ArrayList<DummyTile> potentialMovesDummy = new ArrayList<DummyTile>();

		for (int index = 0; index < GameEngine.legalMoves.size(); index++) {

			DummyTile[][] tempDummyTiles = new DummyTile[ReversiGame.TILES_PER][ReversiGame.TILES_PER];
			for (int row = 0; row < ReversiGame.TILES_PER; row++) {

				for (int col = 0; col < ReversiGame.TILES_PER; col++) {

					DummyTile tempDummyTile = new DummyTile(new Point(row, col),
							ReversiGame.tiles[row][col].isOccupied(), ReversiGame.tiles[row][col].getOccupiedBy(),
							ReversiGame.tiles[row][col].getOccupiedNeighbours());
					tempDummyTiles[row][col] = tempDummyTile;

					if (GameEngine.containsPotentialMove(ReversiGame.tiles[row][col])) {

						addToPotentialMovesDummy(tempDummyTiles[row][col], potentialMovesDummy);
					}
				}

			}
		}

		return 0;
	}

	public int getMoveWeights(ArrayList<DummyTile> potentialMovesDummy, ArrayList<DummyTile> legalMovesDummy,
			DummyTile[][] dummyTiles, int player, int index) {

		if (legalMovesDummy.isEmpty()) {
			// Calculate the score at this point and return the difference
			return 0;
		} else {

			// In a loop make copies of the array and ArrayList
			for (int i = 0; i < legalMovesDummy.size(); i++) {

				// Make a temp 2d array of all dummy tiles
				DummyTile[][] tempDummyTiles = new DummyTile[ReversiGame.TILES_PER][ReversiGame.TILES_PER];
				for (int row = 0; row < ReversiGame.TILES_PER; row++) {

					for (int col = 0; col < ReversiGame.TILES_PER; col++) {

						DummyTile tempDummyTile = new DummyTile(new Point(row, col), dummyTiles[row][col].isOccupied(),
								dummyTiles[row][col].getOccupiedBy(), dummyTiles[row][col].getOccupiedNeighbours());

						tempDummyTiles[row][col] = tempDummyTile;
					}
				}

				// Copy the potentialMoves dummy to a new ArrayList
				ArrayList<DummyTile> tempPotentialMovesDummy = new ArrayList<DummyTile>();

				for (int j = 0; j < potentialMovesDummy.size(); j++) {

					tempPotentialMovesDummy
							.add(tempDummyTiles[potentialMovesDummy.get(j).getLocation().x][potentialMovesDummy.get(j)
									.getLocation().y]);
				}

				// Copy the legalMovesDummy to a new ArrayList
				ArrayList<DummyTile> tempLegalMovesDummy = new ArrayList<DummyTile>();

				for (int j = 0; j < legalMovesDummy.size(); j++) {

					tempLegalMovesDummy.add(tempDummyTiles[legalMovesDummy.get(j).getLocation().x][legalMovesDummy
							.get(j).getLocation().y]);
				}

				// Set a dummyTile to be occupied by the current player
				tempLegalMovesDummy.get(i).setOccupied();
				tempLegalMovesDummy.get(i).setOccupiedBy(player);
				tempPotentialMovesDummy.remove(tempLegalMovesDummy.get(i));
				setDummyNeighboursOccupiedNeighbours(tempDummyTiles, tempLegalMovesDummy, tempLegalMovesDummy.get(i).getLocation());
				
				
			}
			return 0;
		}
	}

	public void setDummyNeighboursOccupiedNeighbours(DummyTile[][] dummyTiles, ArrayList<DummyTile> legalMovesDummy,
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

				if (!dummyTiles[row][col].isOccupied() && !containsPotentialMoveDummy(dummyTiles[row][col], potentialMovesDummy)) {
					addToPotentialMovesDummy(dummyTiles[row][col], potentialMovesDummy);
				}

				index--;
			}
		}
	}
	
	public void takeDummyTiles(DummyTile[][] dummyTiles, Point location) {

		int index = 0;
		//int tilesTaken = 0;

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

				if (dummyTiles[location.getLocation().x + row][location.getLocation().y + col].getOccupiedNeighbour(index)) {
					// System.out.println("Tile.takeTiles method if statement -
					// index is " + index + " - row/col is " + row + ", " +
					// col);
					if (checkLegalMove(location, row, col, dummyTiles, currentPlayer)) {

						changeTiles(row, col, location, currentPlayer, dummyTiles);
					}
				}

				index++;
			}
		}
//		ReversiGame.players.get(GameEngine.currentPlayer).setPieceCount(tilesTaken + 1);
//		ReversiGame.players.get((GameEngine.currentPlayer + 1) % 2).setPieceCount(-tilesTaken);
//		System.out.println("tilesTaken = " + tilesTaken);
	}
	
	public boolean checkLegalMove(Point location, int addX, int addY, DummyTile[][] dummyTiles, int currentPlayer) {

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

					// Means this is a legal move
					return true;
				}

				// Translate point by (addX, addY)
				point.translate(addX, addY);
			}
		}

		// Means this is not a legal move
		return false;
	}
	
	public int takeTiles(int addX, int addY, Point location, int currentPlayer, DummyTile[][] dummyTiles) {

		Point point = new Point(location.getLocation().x + addX, location.getLocation().y + addY);
		int tilesTaken = 0;

		while (dummyTiles[point.getLocation().x][point.getLocation().y].isOccupied()
				&& dummyTiles[point.getLocation().x][point.getLocation().y].getOccupiedBy() != currentPlayer) {

			ReversiGame.tiles[point.getLocation().x][point.getLocation().y].setOccupiedBy(currentPlayer);
			ReversiGame.tiles[point.getLocation().x][point.getLocation().y].revalidate();
			ReversiGame.tiles[point.getLocation().x][point.getLocation().y].repaint();
			System.out.println("takeTiles method " + (point.x) + ", " + (point.y));
			// System.out.println();
			point.translate(addX, addY);
			tilesTaken += 1;
		}

		return tilesTaken;
	}
}


