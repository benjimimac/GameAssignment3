package ie.ben;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Piece extends JPanel {

	private int row;
	private int col;
	private Color colour;
	
	public Piece(int row, int col, Color colour) {

		initUI(row, col, colour);
	}

	private void initUI(int row, int col, Color colour) {

		this.row = row;
		this.col = col;
		this.colour = colour;
	}
	
	@Override
	public void paintComponents(Graphics graphic) {
		
		graphic.setColor(colour);
		graphic.fillOval(350, 350, 50, 50);
	}
}
