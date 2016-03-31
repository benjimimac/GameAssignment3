package ie.ben;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Tile extends JPanel {

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
		this.setBorder(BorderFactory.createEtchedBorder());

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {
				// Use an if else statement her to change to different
				// colours depending on legal moves.
				if (!isOccupied()) {

					setBackground(new Color(135, 206, 250));
					System.out.println("Not occupied");
				} else {

					setBackground(new Color(200, 0, 0));
					System.out.println("Occupied");
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
		
		
		
//		piece = new Piece(row, col, Color.black);
//		add(piece);
	}

	private void initOccupiedTile(int occupiedBy, int row, int col) {

		setOccupied(true);
		setOccupiedBy(occupiedBy);
		setRow(row);
		setCol(col);
		
		setPreferredSize(new Dimension(50, 50));

		if (getOccupiedBy() == 0) {
			piece = new Piece(row, col, Color.black);
		} else {
			piece = new Piece(row, col, Color.white);
		}
		
		this.add(piece);
		setComponentZOrder(piece, 0);
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
	
	@Override
	public void paintComponent(Graphics graphic) {
		
		//BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		//graphic = img.getGraphics();
		
		super.paintComponent(graphic);
		
		Graphics2D graphic2D = (Graphics2D) graphic;
		
		graphic2D.setColor(new Color(0, 0, 0));//colour);
		graphic2D.fillRect(25, 25, 10, 10);
//		Piece piece = new Piece(1, 1, Color.black);
//		add(piece);
		
		
		
		//ImageIcon icon = new ImageIcon(img);
	}

}
