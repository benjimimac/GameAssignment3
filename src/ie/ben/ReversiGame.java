package ie.ben;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class ReversiGame extends JFrame {

	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_HEIGHT = 900;

	MainMenu menu;
	// Game game;
	Tile tile;
	// ArrayList<ArrayList<Tile>> tiles;
	public static Tile[][] tiles;
	public static final int TILE_SIZE = 75;
	public static final int TILES_PER = 8;
	public static final int BOARD_START_X = 50;
	public static final int BOARD_START_Y = 150;
	public static final int PIECE_SIZE = 52;
	public static final int PIECE_START_X = 63;
	public static final int PIECE_START_Y = 163;
	public static int num = 0;

	private boolean gameLoaded = false;;

	private JPanel boardPanel;

	// The default is 0 (black)
	public int player = 0;

	public HumanPlayer player1;
	public AIPlayer player2;
	ArrayList<PlayerObject> players;// = new ArrayList<PlayerObject>();

	// private JPanel pan1;

	// public boolean[][] tilesOccupied;

	// Public constructor for the game
	public ReversiGame() {

		// On further reading of different tutorials it seems
		// that it's common place not to have implementation
		// code in the constructor for Swing applications
		// so I've passed off the code to a private method
		initGUI();
	}

	// Initialise the main window here
	private void initGUI() {

		// Call the createMenuBar method to set up the initial
		// window
		createMenuBar();
		menu = new MainMenu();
		add(menu);
		// menu.setVisible(false);

		// Tile tileTest = new Tile(1, 1, 1);
		// add(tileTest);

		setTitle("Reversi");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	// method to create the menu bar of the window
	private void createMenuBar() {

		// Use the Java Swing libraries to set up appropriate
		// menu bar features.
		JMenuBar menuBar = new JMenuBar();

		ImageIcon newIcon = new ImageIcon("new.png");
		ImageIcon saveIcon = new ImageIcon("save.png");
		ImageIcon loadIcon = new ImageIcon("load.png");
		ImageIcon settingsIcon = new ImageIcon("settings.png");
		ImageIcon exitIcon = new ImageIcon("exit.png");
		ImageIcon helpIcon = new ImageIcon("help.png");

		// Create a menu called "Game"
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G); // Set a mnemonic for the game menu
												// "Alt-G"

		JMenuItem newGame = new JMenuItem(new MenuItemAction("New", newIcon, KeyEvent.VK_N));
		newGame.setToolTipText("Start a new game");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent newGameEvent) {
				// Hide the main menu and start the game
				menu.setVisible(false);

				// Create the player objects and add them to the players
				// ArrayList
				player1 = new HumanPlayer(player);
				player2 = new AIPlayer((player + 1) % 2);
				players = new ArrayList<PlayerObject>();
				players.add(player1);
				players.add(player2);

				// Create the text areas for each player
				createTextArea();

				// Call the createBoard method
				createBoard();
				// boardPanel.setVisible(false);

				// boardPanel.setVisible(true);
				
				GameEngine test = new GameEngine();
				test.setOccupied();
				System.out.println(test.occupiedTiles.size());
			}
		});
		JMenuItem saveGame = new JMenuItem(new MenuItemAction("Save", saveIcon, KeyEvent.VK_S));
		saveGame.setToolTipText("Save the current game");
		JMenuItem loadGame = new JMenuItem(new MenuItemAction("Load", loadIcon, KeyEvent.VK_L));
		loadGame.setToolTipText("Load the last game");
		JMenuItem exitGame = new JMenuItem(new MenuItemAction("Exit", exitIcon, KeyEvent.VK_E));
		exitGame.setToolTipText("Exit the program");
		exitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		JMenu settings = new JMenu("Settings");
		settings.setToolTipText("Change the game settings");
		JMenu helpGame = new JMenu("Help");
		helpGame.setToolTipText("Help!");

		// Add an ActionListener that exits the application
		exitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

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

	private void createBoard() {

		if (gameLoaded) {
			remove(this.boardPanel);
			revalidate();
			repaint();
			// removeAll();
			// pan1.removeAll();
			// pan1.updateUI();
			// boardPanel.removeAll();
			// boardPanel.updateUI();
			// boardPanel.repaint();
			// boardPanel.add(pan1);
			// boardPanel.repaint();
			// remove(boardPanel);
			// repaint();
			// System.out.println("if statement");
		}

		JPanel pan1 = new JPanel();
		boardPanel = new JPanel();
		// pan1 = new JPanel();

		// JLayeredPane pan2 = new JLayeredPane();
		// boardPanel = new JPanel();
		// game = new Game();

		// Set a grid layout
		pan1.setLayout(new GridLayout(TILES_PER, TILES_PER, 0, 0));

		// Create the board from individual tile buttons
		// board is 8 x 8
		// tilesOccupied = new boolean[TILES_PER][TILES_PER];
		// tiles = new ArrayList<ArrayList<Tile>>();
		tiles = new Tile[TILES_PER][TILES_PER];
		for (int i = 0; i < TILES_PER; i++) {
			// tiles.add(new ArrayList<Tile>());
			for (int j = 0; j < TILES_PER; j++) {
				if (i == j && (i == 3 || i == 4)) {
					tile = new Tile(new Point(i, j), 1);
					// tilesOccupied[i][j] = true;
				} else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
					tile = new Tile(new Point(i, j), 0);
					// tilesOccupied[i][j] = true;
				} else {
					tile = new Tile(new Point(i, j));
					// tilesOccupied[i][j] = false;
				}

				pan1.add(tile, new Integer(1));
				// tiles.get(i).add(tile);
				tiles[i][j] = tile;
				// System.out.println("added tile");
				// if(i == 4 && j == 4) {
				// Piece test = new Piece(0, 0, Color.red);
				// pan1.add(test, new Integer(2));
				// }
			}
		}

		// setBackground(new Color(9, 22, 66));
		// pan2.setLayout(new GridLayout(1, 1, 50, 150));
		boardPanel.add(pan1);
		// Piece test = new Piece(0, 0, Color.red);
		// add(test);
		boardPanel.setBackground(new Color(127, 127, 127));
		add(boardPanel, BorderLayout.SOUTH);
		// getContentPane().setBackground(new Color(9, 22, 66));
		// game.setVisible(false);
		// menu = new MainMenu();
		// add(menu);

		// Create the container windows that holds the listener objects
		// Container pane = getContentPane();
		// GroupLayout gl = new GroupLayout(pane);
		// pane.setLayout(gl);

		// Set the title/size/position/close operation

		// boardPanel.repaint();
		revalidate();
		repaint();
		gameLoaded = true;

	}

	private void createTextArea() {

		int playerNum = 1;

		// In a for loop add two text areas that will show both players progress
		for (PlayerObject player : players) {

			// Create a JPanel that will contain the JLabel and set its
			// preferences
			JPanel panel1 = new JPanel();
			panel1.setBackground(new Color(41, 41, 41));
			panel1.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, 200));
			panel1.setBorder(BorderFactory.createEtchedBorder(20, Color.white, Color.white));

			// Add the JLabel
			JLabel label1 = new JLabel(
					"<html>Player: " + playerNum + "<br>PieceCount: " + player.pieceCount + "</html>"); // Player
			// details
			// will
			// go
			// here
			label1.setFont(new Font("Serif", Font.BOLD, 19));
			label1.setLayout(new BorderLayout());
			label1.setForeground(Color.white);

			// Add the JLabel to the JPanel
			panel1.add(label1);

			// Position the JPanels appropriately
			if (playerNum == 1) {

				add(panel1, BorderLayout.LINE_START);
			} else {

				add(panel1, BorderLayout.LINE_END);
			}

			// Increment playerNum
			playerNum++;
		}
	}

	// Create a private inner class to handle the mnemonic and naming of the
	// menu items
	// This is integral to the windows pane so include it as an inner class
	private class MenuItemAction extends AbstractAction {

		// Constructor for the MenuItemAction class
		public MenuItemAction(String text, ImageIcon icon, Integer mnemonic) {
			super(text);

			putValue(SMALL_ICON, icon);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		// Remove this when the menu items have functionality
		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println(e.getActionCommand() + ", " + gameLoaded);
		}
	}

	public static void main(String[] args) {

		// The EventQueue invokeLater method is used so the
		// application doesn't crash.
		EventQueue.invokeLater(new Runnable() {

			// Runnable() must implement the inherited abstract
			// run method. This creates the game object and runs
			// the application loop.
			@Override
			public void run() {
				ReversiGame game = new ReversiGame();
				// game.setBackground(new Color(9, 22, 66));
				game.setVisible(true);
			}
		});
	}
}
