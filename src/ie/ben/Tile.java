package ie.ben;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class Tile extends JButton {

	public Tile() {
		
		initUI();
	}
	
	private void initUI() {
		setPreferredSize(new Dimension(75, 75));
		setBackground(new Color(0, 123, 0));
	}
}
