package ie.ben;

import java.awt.Point;
import java.util.ArrayList;

public class GameEngine {

	public ArrayList<Point> occupiedTiles; // Store updated occupied tiles here
	public ArrayList<Point> adjacentTiles;
	public static ArrayList<Tile> potentialMoves;
	public ArrayList<Point> legalMoves;

	public static int whichPlayer;
	public int moves;

	public GameEngine(int whichPlayer) {

		initOccupiedTiles();

		initPotentialMoves();

		initGameEngine(whichPlayer, 2);
	}
	//
	// private void initGameEngine() {
	// occupiedTiles = new ArrayList<Point>();
	// }

	// Set an ArrayList of occupied Tiles
	public void initOccupiedTiles() {

		// Initialise a new empty ArrayList
		occupiedTiles = new ArrayList<Point>();

		for (int i = 0; i < ReversiGame.TILES_PER; i++) {
			for (int j = 0; j < ReversiGame.TILES_PER; j++) {
				if (ReversiGame.tiles[i][j].isOccupied()) {
					occupiedTiles.add(ReversiGame.tiles[i][j].getLocation());
					System.out.println(ReversiGame.tiles[i][j].getLocation());
				}
			}
		}

		System.out.println(occupiedTiles.size());
	}

	private void setAdjacentTiles() {

		// Initialise a new empty ArrayList
		adjacentTiles = new ArrayList<Point>();

	}

	private void initPotentialMoves() {

		// Instantiate the potentialMoves ArrayList
		potentialMoves = new ArrayList<Tile>();

		// Loop through the global tiles object array to add tiles to the
		// potentialMoves arrayList
		for (int row = 0; row < ReversiGame.tiles.length; row++) {

			for (int col = 0; col < ReversiGame.tiles[row].length; col++) {

				//Check if the current tile has at least one neighbour tile that is occupied 
				if (ReversiGame.tiles[row][col].isPotentialMove()) {

					// If potentailMoves does not contain the reference to the
					// tile location add it
					if (!containsPoint(ReversiGame.tiles[row][col])) {

						//potentialMoves.add(ReversiGame.tiles[row][col].getLocation());
						
						//Call the method to add the tiles location point to the potentialMoves ArrayList
						addToPotentialMoves(ReversiGame.tiles[row][col]);
					}
				}
			}
		}	
		
		System.out.println("Current potential moves are :");
		for (int i = 0; i < potentialMoves.size(); i++) {
			System.out.println(potentialMoves.get(i).getLocation());
		}
	}

	//Method that checks if a tiles location point is already in the potentialMoves ArrayList
	//Returns true or false
	public static boolean containsPoint(Tile tile) {

		if (potentialMoves.contains(tile)) {

			return true;
		}
		return false;
	}

	//Method that adds a tiles location point to the potentialMoves ArrayList
	public static void addToPotentialMoves(Tile tile) {

		// Add the new point to the ArrayList potentialMoves
		potentialMoves.add(tile);
		
//		System.out.println("Current potential moves are :");
//		for (int i = 0; i < potentialMoves.size(); i++) {
//			System.out.println(potentialMoves.get(i).getLocation());
//		}
	}
	
	public static void removeFromPotentialMoves(Tile tile) {
		
		//Remove the point from potential moves
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

				if (!ReversiGame.tiles[row][col].isOccupied() && !containsPoint(ReversiGame.tiles[row][col])) {
					addToPotentialMoves(ReversiGame.tiles[row][col]);
				}
			}
		}
	}

	private void initGameEngine(int whichPlayer, int moves) {

		//Set the static variable that tracks which player turn it is
		setWhichPlayer(whichPlayer);
		
		this.moves = moves;
	}
	
	public static void setWhichPlayer(int player) {
		
		whichPlayer = player;
	}
}
