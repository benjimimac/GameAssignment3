package ie.ben;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameEngine extends JFrame {

	public static ArrayList<Tile> occupiedTiles; // Store updated occupied tiles
													// here
	public ArrayList<Point> adjacentTiles;
	public static ArrayList<Tile> potentialMoves;
	public static ArrayList<Tile> legalMoves;

	public static int currentPlayer;
	private static int moves;

	public static void initGameEngine(int whichPlayer) {

		initOccupiedTiles();

		initPotentialMoves();

		initGameEngine(whichPlayer, 2);
	}
	//
	// private void initGameEngine() {
	// occupiedTiles = new ArrayList<Point>();
	// }

	// Set an ArrayList of occupied Tiles
	private static void initOccupiedTiles() {

		// Initialise a new empty ArrayList
		occupiedTiles = new ArrayList<Tile>();

		for (int i = 0; i < ReversiGame.TILES_PER; i++) {
			for (int j = 0; j < ReversiGame.TILES_PER; j++) {
				if (ReversiGame.tiles[i][j].isOccupied()) {
					occupiedTiles.add(ReversiGame.tiles[i][j]);
					// System.out.println(ReversiGame.tiles[i][j]);
				}
			}
		}

		// System.out.println(occupiedTiles.size());
	}

	public static void addToOccupiedTiles(Tile tile) {

		occupiedTiles.add(tile);
	}

	public static void repaintOccupiedTiles() {

		// Loop through the occupied tiles ArrayList and revalidate and repaint
		// all the occupied tiles

		for (Tile tile : occupiedTiles) {

			tile.revalidate();
			tile.repaint();
		}
	}

	public static void resetAllLegalTiles() {

		for (Tile legalMove : legalMoves) {

			legalMove.setBackground(new Color(0, 123, 0));
			legalMove.revalidate();
			legalMove.repaint();
		}
	}

	public static void repaintLegalMoveTiles() {

		// Each turn the legal moves must be repainted
		// Loop through the legalMoves ArrayList
		for (Tile legalMove : legalMoves) {

			legalMove.setBackground(new Color(135, 206, 250));
			legalMove.revalidate();
			legalMove.repaint();
		}
	}

//	private void setAdjacentTiles() {
//
//		// Initialise a new empty ArrayList
//		adjacentTiles = new ArrayList<Point>();
//
//	}

	private static void initPotentialMoves() {

		// Instantiate the potentialMoves ArrayList
		potentialMoves = new ArrayList<Tile>();

		// Loop through the global tiles object array to add tiles to the
		// potentialMoves arrayList
		for (int row = 0; row < ReversiGame.tiles.length; row++) {

			for (int col = 0; col < ReversiGame.tiles[row].length; col++) {

				// Check if the current tile has at least one neighbour tile
				// that is occupied
				if (ReversiGame.tiles[row][col].isPotentialMove()) {

					// If potentailMoves does not contain the reference to the
					// tile location add it
					if (!containsPotentialMove(ReversiGame.tiles[row][col])) {

						// potentialMoves.add(ReversiGame.tiles[row][col].getLocation());

						// Call the method to add the tiles location point to
						// the potentialMoves ArrayList
						addToPotentialMoves(ReversiGame.tiles[row][col]);
					}
				}
			}
		}

	}

	

	// Method that checks if a tiles location point is already in the
	// potentialMoves ArrayList
	// Returns true or false
	public static boolean containsPotentialMove(Tile tile) {

		if (potentialMoves.contains(tile)) {

			return true;
		}
		return false;
	}

	// Method that adds a tiles location point to the potentialMoves ArrayList
	public static void addToPotentialMoves(Tile tile) {

		// Add the new point to the ArrayList potentialMoves
		potentialMoves.add(tile);

		// System.out.println("Current potential moves are :");
		// for (int i = 0; i < potentialMoves.size(); i++) {
		// System.out.println(potentialMoves.get(i).getLocation());
		// }
	}

	public static void removeFromPotentialMoves(Tile tile) {

		// Remove the point from potential moves
		potentialMoves.remove(tile);
	}

	// If a tile is occupied set each neighbours occupiedNeighbours array at the
	// relevant index
	public static void setNeighboursOccupiedNeighbours(Tile tile) {

		int index = 7;

		for (int row = tile.getLocation().x - 1; row <= tile.getLocation().x + 1; row++) {

			for (int col = tile.getLocation().y - 1; col <= tile.getLocation().y + 1; col++) {

				if (row < 0 || row > 7) {

					index -= 3;
					break;
				}

				if (col < 0 || col > 7) {

					index--;
					continue;
				}

				if (tile.getLocation().equals(new Point(row, col))) {

					continue;
				}

				ReversiGame.tiles[row][col].setOccupiedNeighbour(index);

				if (!ReversiGame.tiles[row][col].isOccupied() && !containsPotentialMove(ReversiGame.tiles[row][col])) {
					addToPotentialMoves(ReversiGame.tiles[row][col]);
				}

				index--;
			}
		}
	}

	public static boolean checkLegalMove(/* Color colour, int player, */Point location, int addX, int addY) {

		// Instantiate the leagalMoves ArrayList
		// legalMoves = new ArrayList<Tile>();

		// Create a temporary Point object set to the location of the "next"
		// tile
		Point point = new Point(location.x + addX, location.y + addY);

		// if()
		// Check initial tile isn't occupied by the current player
		// System.out.println("checkLegalMove point - " + point);
		if (ReversiGame.tiles[point.getLocation().x][point.getLocation().y].getOccupiedBy() != currentPlayer) {

			// Loop while the currently selected tile is occupied
			while (ReversiGame.tiles[point.getLocation().x][point.getLocation().y].isOccupied()) {

				// System.out.println(addX + ", " + addY);
				if (point.getLocation().x + addX < 0 || point.getLocation().x + addX > 7
						|| point.getLocation().y + addY < 0 || point.getLocation().y + addY > 7) {

					break;
				}

				// Check if the next tile is occupied by the current player
				if (ReversiGame.tiles[point.getLocation().x + addX][point.getLocation().y + addY].isOccupied()
						&& ReversiGame.tiles[point.getLocation().x + addX][point.getLocation().y + addY]
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

	public static void setLegalMoves() {

		// Initialise the legalMoves ArrayList - every move it has to be
		// re-initialised
		legalMoves = new ArrayList<Tile>();

		// Loop through the potentialMoves ArrayList and check for legal moves
		// for the current player
		for (Tile potentialMove : potentialMoves) {

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

					if ((potentialMove.getLocation().x < 1 && row < 0)
							|| (potentialMove.getLocation().x > 6 && row > 0)) {
						// if(row < 0 || row > 7) {

						index += 3;
						// System.out.println("Inside the row break statement");
						break;
					}

					if ((potentialMove.getLocation().y < 1 && col < 0)
							|| (potentialMove.getLocation().y > 6 && col > 0)) {
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
					if (potentialMove.getOccupiedNeighbour(index)) {

						if (checkLegalMove(potentialMove.getLocation(), row, col)) {

							addToLegalMoves(potentialMove);
						}
					}

					index++;
				}
			}
		}
	}

	// Method that checks if a tile is already in the
	// potentialMoves ArrayList
	// Returns true or false
	public static boolean containsLegalMove(Tile tile) {

		if (legalMoves.contains(tile)) {

			return true;
		}
		return false;
	}

	public static int takeTiles(int addX, int addY, Point location) {

		Point point = new Point(location.getLocation().x + addX, location.getLocation().y + addY);
		int tilesTaken = 0;

		while (ReversiGame.tiles[point.getLocation().x][point.getLocation().y].isOccupied()
				&& ReversiGame.tiles[point.getLocation().x][point.getLocation().y].getOccupiedBy() != currentPlayer) {

			ReversiGame.tiles[point.getLocation().x][point.getLocation().y].setOccupiedBy(currentPlayer);
			ReversiGame.tiles[point.getLocation().x][point.getLocation().y].revalidate();
			ReversiGame.tiles[point.getLocation().x][point.getLocation().y].repaint();
//			System.out.println("takeTiles method " + (point.x) + ", " + (point.y));
			// System.out.println();
			point.translate(addX, addY);
			tilesTaken += 1;
		}

		return tilesTaken;
	}

	// Method that adds a tiles location point to the potentialMoves ArrayList
	public static void addToLegalMoves(Tile tile) {

		// Add the new point to the ArrayList legalMoves
		legalMoves.add(tile);

		// System.out.println("Current potential moves are :");
		// for (int i = 0; i < potentialMoves.size(); i++) {
		// System.out.println(potentialMoves.get(i).getLocation());
		// }
	}

	private static void initGameEngine(int currentPlayer, int m) {

		// Set the static variable that tracks which player turn it is
		setCurrentPlayer(currentPlayer);

		moves = m;
	}

	public static void setCurrentPlayer(int player) {

		currentPlayer = player;
	}

	public static boolean updateGame() {

		boolean gameOver = false;

		// Change player
		currentPlayer = (currentPlayer + 1) % 2;

		// Text areas must be updated now
		ReversiGame.setTextAreaText();
		ReversiGame.setTextAreaColour();

		// legalMoves array must be reset every turn
		resetAllLegalTiles();
		setLegalMoves();
		repaintLegalMoveTiles(); // repaints all the relevant tiles

		// If legalMoves is empty there are no legal moves for the current
		// player
		if (legalMoves.isEmpty()) {

			gameOver = false;
			// If potentialMoves is not empty switch to next player and
			// reset/repaint components
			if (!potentialMoves.isEmpty()) {

				// Change to next player
				currentPlayer = (currentPlayer + 1) % 2;

				// Repaint the text areas
				ReversiGame.setTextAreaColour();

				// Reset/repaint legalMoves
				resetAllLegalTiles();
				setLegalMoves();
				repaintLegalMoveTiles();

				// If there are no legal moves for second player then game over
				if (legalMoves.isEmpty()) {
					gameOver = true;
					currentPlayer = 0;
					// Create a dialog that gives scores and option for new game
					// ReversiGame.endMessage.setVisible(true);

					// public static void endGameDialog() {

					// GameOver endMessage = new GameOver(this);
					// endMessage.setVisible(false);
					// //add(endMessage);

					System.out.println("Game Over");
				}

			} else {

				gameOver = true;
				System.out.println("Game Over");

			}
		}

		return gameOver;
	}
}
