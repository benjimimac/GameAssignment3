package ie.ben;

import java.awt.Point;
import java.util.ArrayList;

public class GameEngine {
	
	public ArrayList<Point> occupiedTiles;
	public ArrayList<Point> adjacentTiles;
	
//	public GameEngine() {
//		
//		initGameEngine();
//	}
//	
//	private void initGameEngine() {
//		occupiedTiles = new ArrayList<Point>();
//	}
	
	
	//Set an ArrayList of occupied Tiles
	public void setOccupied() {
		
		//Initialise a new empty ArrayList
		occupiedTiles = new ArrayList<Point>();
		
		for(int i = 0; i < ReversiGame.TILES_PER; i++) {
			for(int j = 0; j < ReversiGame.TILES_PER; j++) {
				if(ReversiGame.tiles[i][j].isOccupied()) {
					occupiedTiles.add(ReversiGame.tiles[i][j].getLocation());
				}
			}
		}
		
		System.out.println(occupiedTiles.size());
	}
	
	public void setAdjacentTiles() {
		
		//Initialise a new empty ArrayList
		adjacentTiles = new ArrayList<Point>();
		
	}
}
