package ie.ben;

import java.awt.Point;

public class DummyTile {

	private Point location;
	private boolean occupied;
	private int occupiedBy;
	private boolean[] occupiedNeighbours;
	private int weight;

	public int getWeight() {
		
		return weight;
	}

	public void setWeight(int weight) {
		
		this.weight = weight;
	}

	public boolean getOccupiedNeighbour(int index) {

		return occupiedNeighbours[index];
	}
	
	public void setOccupiedNeighbour(int index) {

		occupiedNeighbours[index] = true;
	}

	public void setOccupiedNeighbours(boolean[] occupiedNeighbours) {

		this.occupiedNeighbours = occupiedNeighbours;
	}
	
	public boolean[] getOccupiedNeighbours() {

		return occupiedNeighbours;
	}
	
	public Point getLocation() {

		return location;
	}

	public void setLocation(Point location) {

		this.location = location;
	}

	public boolean isOccupied() {

		return occupied;
	}

	public void setOccupied() {

		occupied = true;
	}

	public int getOccupiedBy() {

		return occupiedBy;
	}

	public void setOccupiedBy(int occupiedBy) {
		this.occupiedBy = occupiedBy;
	}

	public DummyTile(Point location, boolean occupied, int occupiedBy, boolean[] occupiedNeighbours) {

		initDummyTile(location, occupied, occupiedBy, occupiedNeighbours);
	}

	private void initDummyTile(Point location, boolean occupied, int occupiedBy, boolean[] occupiedNeighbours) {
		System.out.println("getMoves");
		this.location = location;
		this.occupied = occupied;
		this.occupiedBy = occupiedBy;
		weight = 0;
		
		this.occupiedNeighbours = new boolean[8];
		
		for (int i = 0; i < occupiedNeighbours.length; i++) {
			
			this.occupiedNeighbours[i] = occupiedNeighbours[i];
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

//	public boolean checkPotentialMovesDummy() {
//
//		if (AIPlayer.containsPotentialMoveDummy(this)) {
//
//			return true;
//		}
//
//		return false;
//	}
//
//	public boolean checkLegalMovesDummy() {
//
//		if (AIPlayer.containsLegalMoveDummy(this)) {
//
//			return true;
//		}
//
//		return false;
//	}
//
//	public void removeTileFromPotentialMovesDummy() {
//
//		AIPlayer.removeFromPotentialMovesDummy(this);
//	}
//
//	// Method that checks if a tiles location point is already in the
//	// potentialMoves ArrayList
//	// Returns true or false
//	public boolean containsPotentialMoveDummy(DummyTile dummyTile) {
//
//		if (AIPlayer.potentialMovesDummy.contains(dummyTile)) {
//
//			return true;
//		}
//		return false;
//	}
//
//	// Method that adds a tiles location point to the potentialMoves ArrayList
//	public void addToPotentialMovesDummy(DummyTile dummyTile) {
//
//		// Add the new point to the ArrayList potentialMoves
//		AIPlayer.potentialMovesDummy.add(dummyTile);
//
//		// System.out.println("Current potential moves are :");
//		// for (int i = 0; i < potentialMoves.size(); i++) {
//		// System.out.println(potentialMoves.get(i).getLocation());
//		// }
//	}
//
//	public static void removeFromPotentialMovesDummy(DummyTile dummyTile) {
//
//		// Remove the point from potential moves
//		AIPlayer.potentialMovesDummy.remove(dummyTile);
//	}
}
