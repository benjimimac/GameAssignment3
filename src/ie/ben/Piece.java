package ie.ben;

import java.awt.Color;
import java.awt.Graphics;

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
		//System.out.println(colour);
	}
	
	@Override
	public void paintComponent(Graphics graphic) {
		
		super.paintComponent(graphic);
		
		graphic.setColor(new Color(255, 0, 0));//colour);
		graphic.fillOval(50, 50, 50, 50);
	}
}
