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

		JPanel pan1 = new JPanel();

		// Set a grid layout
		pan1.setLayout(new GridLayout(ReversiGame.TILES_PER, ReversiGame.TILES_PER, 0, 0));

		// Create the board from individual tile buttons
		// board is 8 x 8
		
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
				
			}
		}
		
		//Set each tiles boolean array occupiedNeighbours to true or false
		for(int row = 0; row < ReversiGame.tiles.length; row++) {
			
			for( int col = 0; col < ReversiGame.tiles[row].length; col++) {
				
				//Call the setOccupiedNeighbours method on every tile object
				ReversiGame.tiles[row][col].setOccupiedNeighbours();
			}
		}

		add(pan1);
		
		setBackground(new Color(127, 127, 127));

		revalidate();
		repaint();
		ReversiGame.gameLoaded = true;
	}
}
