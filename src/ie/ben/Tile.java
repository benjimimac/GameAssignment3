package ie.ben;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Tile extends JPanel {

	private boolean occupied; // Each tile should know if they're occupied
	private int occupiedBy; // Each tile should know what colour piece occupies
							// them
							// 0 = black, 1 = white
	// private Piece piece;
	private int row;
	private int col;
	private Point location;

	boolean[] occupiedNeighbours;

	// Constructor for empty tiles
	public Tile(Point location) {

		initUI();

		initEmptyTile(location);

	}

	// Constructor for tiles that are already occupied
	public Tile(Point location, int occupiedBy) {

		initUI();

		initOccupiedTile(location, occupiedBy);
	}

	// Third constructor will be used by the AI to make its decisions
	// The AI make a copy of the the original board so it can check possible
	// future moves
	public Tile(Point location, boolean occupied, int occupiedBy) {

		// Make a copy of the original tile
		if (occupied) {

			initOccupiedTile(location, occupiedBy);
		} else {

			initEmptyTile(location);
		}
	}

	private void initUI() {
		setPreferredSize(new Dimension(75, 75));
		setBackground(new Color(0, 123, 0));
		this.setBorder(BorderFactory.createEtchedBorder(1, Color.white, Color.black));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {

				// Use an if else statement her to change to different
				// colours depending on legal moves.
				if (!checkLegalMoves() && !isOccupied()) { // !isOccupied()) {

					setBackground(new Color(135, 0, 0));
					revalidate();
					repaint();
				}
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent) {

				if (!checkLegalMoves()) {

					setBackground(new Color(0, 123, 0));
					revalidate();
					repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent moouseEvent) {

				// GameEngine.updateGame(this);
				updateGame();
			}
		});

	}

	private void updateGame() {

		if (checkLegalMoves() ) {

			// Update the tile properties before calling a static method from
			// GameEngine
			setOccupied(true);
			setOccupiedBy(GameEngine.currentPlayer);
			setBackground(new Color(0, 123, 0));
			removeTileFromPotentialMoves();
			addNeighbours();
			revalidate();
			repaint();
			//addToOccupied();
			takeTiles();

			boolean gameOver = GameEngine.updateGame();

			if (gameOver) {

				GameOver endMessage = new GameOver(this, ReversiGame.players.get(0).pieceCount,
						ReversiGame.players.get(1).pieceCount);
				endMessage.setVisible(true);
				// add(endMessage);
			}

			
		}
		
	}

	private void initEmptyTile(Point location) {

		setOccupied(false);
		setOccupiedBy(0);
		setLocation(location);

		// Initialise the nneighbours boolean array
		initNeighbours();

		
	}

	private void initOccupiedTile(Point location, int occupiedBy) {

		setOccupied(true);
		setOccupiedBy(occupiedBy);
		setLocation(location);

		setPreferredSize(new Dimension(50, 50));

		// Initialise the nneighbours boolean array
		initNeighbours();

		
	}

	public void initNeighbours() {

		// Every tile should know if there neighbours are occupied - default is
		// false
		occupiedNeighbours = new boolean[ReversiGame.TILES_PER];

	}

	// Each tile should know if their neighbours are occupied or not
	public void setOccupiedNeighbours() {

		// create an index variable to access the occupiedNeighbours array
		int index = 0;

		// Loop through the tiles surrounding the current tile and check if
		// occupied
		for (int row = location.x - 1; row <= location.x + 1; row++) {

			for (int col = location.y - 1; col <= location.y + 1; col++) {

				// If row is less than 0 or greater than 7 we are out of bounds
				// - break from inner loop
				if (row < 0 || row > 7) {

					index += 3;
					break;
				}

				// If col is less than 0 or greater than 7 we are also out of
				// bounds - continue to next iteration
				if (col < 0 || col > 7) {

					index++;
					continue;
				}

				// No need to check its own cell - continue to next iteration
				if (location.equals(new Point(row, col))) {
					continue;
				}

				// Check if the tile is occupied - if true set index to true
				if (ReversiGame.tiles[row][col].isOccupied()) {
					setOccupiedNeighbour(index);
				}

				index++;
			}
		}


	}

	// Checks is the tile is a candidate for a potential move
	public boolean isPotentialMove() {

		// If tile is already occupied return false immediately
		if (isOccupied()) {

			return false;
		}

		// Loop through the occupiedNeighbours array and return true if any are
		// true
		for (boolean occupiedNeighbour : occupiedNeighbours) {

			if (occupiedNeighbour) {
				return true;
			}
		}

		return false;
	}

	public boolean checkPotentialMoves() {

		if (GameEngine.containsPotentialMove(this)) {

			return true;
		}

		return false;
	}

	public boolean checkLegalMoves() {

		if (GameEngine.containsLegalMove(this)) {

			return true;
		}

		return false;
	}

	private void removeTileFromPotentialMoves() {
		GameEngine.potentialMoves.remove(this);
	}

	public void addNeighbours() {

		GameEngine.setNeighboursOccupiedNeighbours(this);
	}

	public void addToOccupied() {

		GameEngine.addToOccupiedTiles(this);
	}

	public void setOccupiedNeighbour(int index) {

		// Set the value of occupiedNeghbours at a given index to true
		occupiedNeighbours[index] = true;
	}

	public boolean getOccupiedNeighbour(int index) {

		// Set the value of occupiedNeghbours at a given index to true
		return occupiedNeighbours[index];
	}

	public boolean[] getOccupiedNeighbours() {

		return occupiedNeighbours;
	}

	public void setOccupied(boolean occupied) {

		this.occupied = occupied;
	}

	public void setOccupiedBy(int occupiedBy) {

		this.occupiedBy = occupiedBy;
	}

	public boolean isOccupied() {

		return occupied;
	}

	public int getOccupiedBy() {

		return occupiedBy;
	}


	public void setLocation(Point location) {

		this.location = location;
	}

	public Point getLocation() {

		return location;
	}

	public void takeTiles() {

		int index = 0;
		int tilesTaken = 0;

		for (int row = -1; row <= 1; row++) {

			for (int col = -1; col <= 1; col++) {

				if (getLocation().x + row < 0 || getLocation().x + row > 7) {
					index += 3;
					break;
				}

				if (getLocation().y + col < 0 || getLocation().y + col > 7) {
					index++;
					continue;
				}

				if (location.equals(new Point(getLocation().x + row, getLocation().y + col))) {

					continue;
				}

				if (getOccupiedNeighbour(index)) {
					
					if (GameEngine.checkLegalMove(location, row, col)) {

						tilesTaken += GameEngine.takeTiles(row, col, location);
					}
				}

				index++;
			}
		}
		ReversiGame.players.get(GameEngine.currentPlayer).setPieceCount(tilesTaken + 1);
		ReversiGame.players.get((GameEngine.currentPlayer + 1) % 2).setPieceCount(-tilesTaken);
	}

	

	@Override
	public void paintComponent(Graphics graphic) {

		super.paintComponent(graphic);

		Graphics2D graphic2D = (Graphics2D) graphic;

		Dimension size = this.getSize();

		if (isOccupied()) {
			if (occupiedBy == 0) {
				graphic2D.setColor(new Color(0, 0, 0));
			} else {
				graphic2D.setColor(new Color(255, 255, 255));
			}
			graphic2D.fillOval((size.width / 2) - (ReversiGame.PIECE_SIZE / 2),
					size.height / 2 - (ReversiGame.PIECE_SIZE / 2), ReversiGame.PIECE_SIZE, ReversiGame.PIECE_SIZE);

		}
		
	}

}
