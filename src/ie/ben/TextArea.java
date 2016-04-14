package ie.ben;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextArea extends JPanel{

	private int playerNum;
	JLabel label;
	JPanel panel;
	
	public TextArea(int player) {

		initUI(player);
	}

	private void initUI(int player) {

		playerNum = player + 1;

		//Set the properties for the textArea
		setBackground(new Color(41, 41, 41));
		setPreferredSize(new Dimension(ReversiGame.WINDOW_WIDTH / 2, 200));
		setBorder(BorderFactory.createEtchedBorder(20, Color.white, Color.white));
		
		//Instantiate the label
		label = new JLabel("<html>Player: " + playerNum + "<br>PieceCount: " + ReversiGame.players.get(player).pieceCount + "</html>");
		
		//Set the label properties
		label.setFont(new Font("Serif", Font.BOLD, 19));
		label.setLayout(new BorderLayout());
		label.setForeground(Color.white);
		
		//Add the label to the TextArea JPanel
		add(label);
		
		// In a for loop add two text areas that will show both players progress
//		for (PlayerObject player : ReversiGame.players) {
//
//			// Create a JPanel that will contain the JLabel and set its
//			// preferences
//			panel1 = new JPanel();
//			panel1.setBackground(new Color(41, 41, 41));
//			panel1.setPreferredSize(new Dimension(ReversiGame.WINDOW_WIDTH / 2, 200));
//			panel1.setBorder(BorderFactory.createEtchedBorder(20, Color.white, Color.white));
//
//			// Add the JLabel
//			label1 = new JLabel(
//					"<html>Player: " + playerNum + "<br>PieceCount: " + player.pieceCount + "</html>"); // Player
//			// details
//			// will
//			// go
//			// here
//			label1.setFont(new Font("Serif", Font.BOLD, 19));
//			label1.setLayout(new BorderLayout());
//			label1.setForeground(Color.white);
//
//			// Add the JLabel to the JPanel
//			panel1.add(label1);
//
//			// Position the JPanels appropriately
//			if (playerNum == 1) {
//
//				add(panel1, BorderLayout.LINE_START);
//			} else {
//
//				add(panel1, BorderLayout.LINE_END);
//			}
//
//			// Increment playerNum
//			playerNum++;
//		}
	}
}
