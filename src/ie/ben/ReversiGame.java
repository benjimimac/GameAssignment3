package ie.ben;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ReversiGame extends JFrame {

	//Public constructor for the game
	public ReversiGame() {

		//On further reading of different tutorials it seems
		//that it's common place not to have implementation
		//code in the constructor for Swing applications
		//so I've passed off the code to a private method
		initGUI();
	}
	
	//Initialise the main window here
	private void initGUI() {
		
		//Call the createMenuBar method to set up the initial
		//window
		createMenuBar();
		
		Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);   
        
		setTitle("Reversi");
		setSize(700, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	private void createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		ImageIcon newIcon = new ImageIcon("new.png");
		ImageIcon saveIcon = new ImageIcon("save.png");
		ImageIcon loadIcon = new ImageIcon("load.png");
		ImageIcon settingsIcon = new ImageIcon("settings.png");
		ImageIcon exitIcon = new ImageIcon("exit.png");
		ImageIcon helpIcon = new ImageIcon("help.png");
		
		JMenu gameMenu = new JMenu("Game");
		
		JMenuItem newGame = new JMenuItem("New", newIcon);
		JMenuItem saveGame = new JMenuItem("Save", saveIcon);
		JMenuItem loadGame = new JMenuItem("Load", loadIcon);
		JMenuItem exitGame = new JMenuItem("Exit", exitIcon);
		JMenu settings = new JMenu("Settings");
		JMenu helpGame = new JMenu("Help");
		
		//Add an ActionListener that exits the application
//		exitGame.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				System.exit(0);
//			}
//		});
		
		gameMenu.add(newGame);
		gameMenu.add(saveGame);
		gameMenu.add(loadGame);
		gameMenu.add(exitGame);
		
		menuBar.add(gameMenu);
		menuBar.add(settings);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(helpGame);
		
		setJMenuBar(menuBar);
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
