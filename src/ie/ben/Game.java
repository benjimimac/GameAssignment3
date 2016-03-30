package ie.ben;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JPanel;

class OneTile extends JButton {

	public OneTile() {
		super();
		initUI();
	}

	private void initUI() {
		setPreferredSize(new Dimension(175, 75));
		setBackground(new Color(0, 123, 0));
//		JPanel pan1 = new JPanel();
//		JPanel pan2 = new JPanel();
//		
//		pan1.setLayout(new GridLayout(ReversiGame.TILES_PER, ReversiGame.TILES_PER, 0, 0));
		
		
//		for(int i = 0; i < ReversiGame.TILES_PER; i++) {
//			for(int j = 0; j < ReversiGame.TILES_PER; j++) {
//				JButton tile = 
//			}
//		}
//		addMouseListener(new MouseAdapter() {
//
//			@Override
//			public void mouseEntered(MouseEvent mouseEvent) {
//				//setBorder(BorderFactory.createLineBorder(Color.yellow));
//				setBackground(new Color(3, 59, 90).brighter());
//			}
//
//			@Override
//			public void mouseExited(MouseEvent mouseEvent) {
//				setBorder(BorderFactory.createLineBorder(Color.gray));
//			}
//		});
	}
}

public class Game extends JPanel {

	private JPanel panel;
	
	private int boardWidth;
	private int boardHeight;
	
	//Point[][] tiles;
	Point tiles;

	public Game() {

		initUI();
	}

	private void initUI() {
		
		//tiles = new Point[ReversiGame.TILES_PER][ReversiGame.TILES_PER];
		
//		for (int i = 0; i < ReversiGame.TILES_PER; i++) {
//			for(int j = 0; j < ReversiGame.TILES_PER; j++) {
//				tiles[i][j] = new Point(i, j);
//				
//			}
//		}
		int i = 0;
		int j = 0;
		tiles = new Point(i, j);
	}
	
	@Override
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		// drawMenuBoard(g);

		Dimension size = getSize();
		Insets insets = getInsets();

		boardWidth = size.width - insets.left - insets.right - 1;
		boardHeight = size.height - insets.top - insets.bottom - 1;

//		System.out.println(getInsets().top + ", " + getSize().width + ", " + boardWidth);
		graphic.setColor(new Color(9, 22, 66));
		graphic.fillRect(0, 0, size.width, size.height);

		graphic.setColor(new Color(127, 127, 127));

		// Draw the tiles on the board
//		for (int i = 0; i <= ReversiGame.TILES_PER - 1; i++) {
//			for (int j = 0; j <= ReversiGame.TILES_PER - 1; j++) {
				//System.out.println(tiles[i][j]);
		int i = 0;
		int j = 0;
		int c = 0;
				graphic.setColor(new Color(0, 123, 0));
				graphic.fillRect(ReversiGame.BOARD_START_X + (ReversiGame.TILE_SIZE * j),
						ReversiGame.BOARD_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.TILE_SIZE,
						ReversiGame.TILE_SIZE);
				graphic.setColor(new Color(0, 0, 0));
				graphic.drawRect(ReversiGame.BOARD_START_X + (ReversiGame.TILE_SIZE * j),
						ReversiGame.BOARD_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.TILE_SIZE,
						ReversiGame.TILE_SIZE);
				

				// graphic.drawRect(j * 10, i * 10, 20, 20);
//			}
//		}

//		for (int i = 0; i <= ReversiGame.TILES_PER - 1; i++) {
//			for (int j = 0; j <= ReversiGame.TILES_PER - 1; j++) {
//
//				System.out.println(ReversiGame.num++);
//				if (i == j) {
//					graphic.setColor(new Color(255, 255, 255));
//					graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * j),
//							ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
//							ReversiGame.PIECE_SIZE);
//					graphic.setColor(new Color(0, 0, 0));
//					if (i < ReversiGame.TILES_PER / 2) {
//						if (i % 2 == 1) {
//							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j - 1)),
//									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
//									ReversiGame.PIECE_SIZE);
//							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j + 1)),
//									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
//									ReversiGame.PIECE_SIZE);
//						}
//					} else {
//						if (i % 2 == 0) {
//							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j - 1)),
//									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
//									ReversiGame.PIECE_SIZE);
//							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j + 1)),
//									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
//									ReversiGame.PIECE_SIZE);
//						}
//					}
//				}
//			}
//		}
	}
}
