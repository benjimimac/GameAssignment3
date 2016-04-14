package ie.ben;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;

public class Board extends JPanel{

	private Tile tile;
	
	public Board() {
		
		initUI();
	}
	
	private void initUI() {
//		if (ReversiGame.gameLoaded) {
//			remove(this);
//			revalidate();
//			repaint();
//			// removeAll();
//			// pan1.removeAll();
//			// pan1.updateUI();
//			// boardPanel.removeAll();
//			// boardPanel.updateUI();
//			// boardPanel.repaint();
//			// boardPanel.add(pan1);
//			// boardPanel.repaint();
//			// remove(boardPanel);
//			// repaint();
//			// System.out.println("if statement");
//		}

		JPanel pan1 = new JPanel();
		//boardPanel = new JPanel();
		// pan1 = new JPanel();

		// JLayeredPane pan2 = new JLayeredPane();
		// boardPanel = new JPanel();
		// game = new Game();

		// Set a grid layout
		pan1.setLayout(new GridLayout(ReversiGame.TILES_PER, ReversiGame.TILES_PER, 0, 0));

		// Create the board from individual tile buttons
		// board is 8 x 8
		// tilesOccupied = new boolean[TILES_PER][TILES_PER];
		// tiles = new ArrayList<ArrayList<Tile>>();
		ReversiGame.tiles = new Tile[ReversiGame.TILES_PER][ReversiGame.TILES_PER];
		
		//Create the tiles objects and add them to the tiles 2D array
		for (int row = 0; row < ReversiGame.TILES_PER; row++) {
			
			// tiles.add(new ArrayList<Tile>());
			for (int col = 0; col < ReversiGame.TILES_PER; col++) {
				
				if (row == col && (row == 3 || row == 4)) {
					
					tile = new Tile(new Point(row, col), 1);
					// tilesOccupied[i][j] = true;
				} else if ((row == 3 && col == 4) || (row == 4 && col == 3)) {
					
					tile = new Tile(new Point(row, col), 0);
					// tilesOccupied[i][j] = true;
				} else {
					
					tile = new Tile(new Point(row, col));
					// tilesOccupied[i][j] = false;
				}

				pan1.add(tile, new Integer(1));
				// tiles.get(i).add(tile);
				
				ReversiGame.tiles[row][col] = tile;
				// System.out.println("added tile");
				// if(i == 4 && j == 4) {
				// Piece test = new Piece(0, 0, Color.red);
				// pan1.add(test, new Integer(2));
				// }
			}
		}
		
		//Set each tiles boolean array occupiedNeighbours to true or false
		for(int row = 0; row < ReversiGame.tiles.length; row++) {
			
			for( int col = 0; col < ReversiGame.tiles[row].length; col++) {
				
				//Call the setOccupiedNeighbours method on every tile object
				ReversiGame.tiles[row][col].setOccupiedNeighbours();
			}
		}

		// setBackground(new Color(9, 22, 66));
		// pan2.setLayout(new GridLayout(1, 1, 50, 150));
		add(pan1);
		// Piece test = new Piece(0, 0, Color.red);
		// add(test);
		setBackground(new Color(127, 127, 127));
//		add(boardPanel, BorderLayout.SOUTH);
		// getContentPane().setBackground(new Color(9, 22, 66));
		// game.setVisible(false);
		// menu = new MainMenu();
		// add(menu);

		// Create the container windows that holds the listener objects
		// Container pane = getContentPane();
		// GroupLayout gl = new GroupLayout(pane);
		// pane.setLayout(gl);

		// Set the title/size/position/close operation

		// boardPanel.repaint();
		revalidate();
		repaint();
		ReversiGame.gameLoaded = true;
	}
}
