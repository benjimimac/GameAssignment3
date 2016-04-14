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
		
	}
}
