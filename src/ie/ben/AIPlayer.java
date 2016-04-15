package ie.ben;

import java.util.ArrayList;

public class AIPlayer extends PlayerObject {
	
	private DummyTile[][] dummyTiles;
	private ArrayList<DummyTile> legalMovesDummy;
	private ArrayList<DummyTile> potentialMovesDummy;
	
	public AIPlayer(int player) {

		super(player);
	}

	public AIPlayer(int player, int pieceCount) {

		super(player, pieceCount);
	}
	
	public void createDummyTiles() {
		
		dummyTiles = new DummyTile[ReversiGame.TILES_PER][ReversiGame.TILES_PER];
		
		
		
		for(int row = 0; row < ReversiGame.TILES_PER; row++){
			
			for(int col = 0; col < ReversiGame.TILES_PER; col++) {
				
				DummyTile dummyTile;
				
				if(ReversiGame.tiles[row][col].isOccupied()) {
					
					
				}
			}
		}
	}
}
