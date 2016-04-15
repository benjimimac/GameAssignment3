package ie.ben;

import java.awt.Point;

public class DummyTile {

	private Point location;
	private boolean occupied;
	private int occupiedBy;
	
	public DummyTile(Point location, boolean occupied, int occupiedBy) {
		
		initDummyTile(location, occupied, occupiedBy);
	}
	
	private void initDummyTile(Point location, boolean occupied, int occupiedBy) {
		
		this.location = location;
		this.occupied = occupied;
		this.occupiedBy = occupiedBy;
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
}
