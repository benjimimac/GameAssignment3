package ie.ben;

import java.awt.Point;
import java.util.ArrayList;

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

	public void setOccupied(boolean occupied) {

		this.occupied = occupied;
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
	public boolean checkLegalMovesDummy(ArrayList<DummyTile> legalMovesDummy) {

		if (legalMovesDummy.contains(this)) {

			return true;
		}

		return false;
	}

}
