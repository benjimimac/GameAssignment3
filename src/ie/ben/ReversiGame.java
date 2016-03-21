package ie.ben;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class ReversiGame extends JFrame {

	public ReversiGame() {

		initUI();
	}
	
	private void initUI() {
		Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);   
        
		setTitle("Reversi");
		setSize(700, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ReversiGame game = new ReversiGame();
				game.setVisible(true);
			}
		});
	}
}
