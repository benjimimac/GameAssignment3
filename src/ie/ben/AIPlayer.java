package ie.ben;

public class AIPlayer extends PlayerObject {
	
	private DummyTile[][] dummyTile;
	private ArrayList<DummyTile> legalMovesDummy;
	private ArrayList<DummyTile> potentialMovesDummy;
	
	public AIPlayer(int player) {

		super(player);
	}

	public AIPlayer(int player, int pieceCount) {

		super(player, pieceCount);
	}
	
	public void createDummyTiles() {
		
		for(Tile tile: ReversiGame.tiles) {
			
		}
	}
}
