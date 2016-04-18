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

						if (checkLegalMoveDummy(potentialMoveDummy.getLocation(), row, col)) {

							addToLegalMovesDummy(potentialMoveDummy);
						}
					}

					index++;
				}
			}
		}
	}

	public boolean checkLegalMove(/* Color colour, int player, */Point location, int addX, int addY) {

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
					if (!containsPotentialMoveDummy(dummyTiles[row][col])) {

						// potentialMoves.add(ReversiGame.tiles[row][col].getLocation());

						// Call the method to add the tiles location point to
						// the potentialMoves ArrayList
						addToPotentialMovesDummy(dummyTiles[row][col]);
					}
				}
			}
		}
	}

	// Method that checks if a tiles location point is already in the
	// potentialMoves ArrayList
	// Returns true or false
	public static boolean containsPotentialMoveDummy(DummyTile dummyTile) {

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

	// If a tile is occupied set each neighbours occupiedNeighbours array at the
	// relevant index
	public void setNeighboursOccupiedNeighbours(DummyTile[][] dummyTiles, int row, int col,
			ArrayList<DummyTile> dummyPotentialMoves) {

		int index = 7;

		for (int rows = row - 1; rows <= row + 1; rows++) {

			for (int cols = col - 1; cols <= col + 1; cols++) {

				if (rows < 0 || rows > 7) {

					index -= 3;
					break;
				}

				if (cols < 0 || cols > 7) {

					index--;
					continue;
				}

				if (row == rows && col == cols) {

					continue;
				}

				dummyTiles[rows][cols].setOccupiedNeighbour(index);

				if (!dummyTiles[rows][cols].isOccupied() && !containsPotentialMoveDummy(dummyTiles[rows][cols])) {
					addToPotentialMovesDummy(dummyTiles[rows][cols], dummyPotentialMoves);
				}

				index--;
			}
		}
	}

	// Method that checks if a tile is already in the
	// potentialMoves ArrayList
	// Returns true or false
	public static boolean containsLegalMoveDummy(DummyTile dummyTile) {

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
					
					if(GameEngine.containsPotentialMove(ReversiGame.tiles[row][col])) {
						
						addToPotentialMoves(tempDummyTiles[row][col], )
					}
				}

			}
		}

		return 0;
	}
}
