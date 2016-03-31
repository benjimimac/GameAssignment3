package ie.ben;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Piece extends JPanel {

	private int row;
	private int col;
	private Color colour;
	
	public Piece(int row, int col, Color colour) {

		initUI(row, col, colour);
	}

	private void initUI(int row, int col, Color colour) {

		setLayout(new FlowLayout());
		this.row = row;
		this.col = col;
		this.colour = colour;
		//System.out.println(colour);
	}
	
	@Override
	public void paintComponent(Graphics graphic) {
		
		//BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		//graphic = img.getGraphics();
		
		super.paintComponent(graphic);
		
		Graphics2D graphic2D = (Graphics2D) graphic;
		
		graphic2D.setColor(new Color(0, 0, 0));//colour);
		graphic2D.fillOval(50, 50, 50, 50);
		
		
		
		//ImageIcon icon = new ImageIcon(img);
	}
}
