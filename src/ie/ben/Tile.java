package ie.ben;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Tile extends JButton {

	private boolean occupied; // Each tile should know if they're occupied
	private int occupiedBy; // Each tile should know what colour piece occupies
							// them
							// 0 = black, 1 = white
	private Piece piece;
	private int row;
	private int col;

	// Constructor for empty tiles
	public Tile(int row, int col) {

		initUI();
		
		initEmptyTile(row, col);
		
	}

	// Constructor for tiles that are already occupied
	public Tile(int row, int col, int occupiedBy) {

		initUI();
		
		initOccupiedTile(occupiedBy, row, col);
	}

	private void initUI() {
		setPreferredSize(new Dimension(75, 75));
		setBackground(new Color(0, 123, 0));

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {
				// Use an if else statement her to change to different
				// colours depending on legal moves.
				if (!isOccupied()) {

					setBackground(new Color(135, 206, 250));
				} else {

					setBackground(new Color(200, 0, 0));
				}
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent) {
				setBackground(new Color(0, 123, 0));
			}
		});
	}

	private void initEmptyTile(int row, int col) {

		setOccupied(false);
		setOccupiedBy(0);
		setRow(row);
		setCol(col);
	}

	private void initOccupiedTile(int occupiedBy, int row, int col) {

		setOccupied(true);
		setOccupiedBy(occupiedBy);
		setRow(row);
		setCol(col);

		if (getOccupiedBy() == 0) {
			piece = new Piece(row, col, Color.black);
		} else {
			piece = new Piece(row, col, Color.white);
		}
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
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

}
