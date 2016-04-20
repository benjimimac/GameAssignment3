package ie.ben;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;

class DrawPanel extends JPanel {

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
	}
}

public class MainMenu extends JPanel {

	private int menuWidth;
	private int menuHeight;

	public MainMenu() {

		//setPreferredSize(new Dimension(300, 300));

	}

	@Override
	public void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		// drawMenuBoard(g);

		Dimension size = getSize();
		Insets insets = getInsets();

		menuWidth = size.width - insets.left - insets.right - 1;
		menuHeight = size.height - insets.top - insets.bottom - 1;

		graphic.setColor(new Color(9, 22, 66));
		graphic.fillRect(0, 0, size.width, size.height);

		graphic.setColor(new Color(127, 127, 127));

		// Draw the tiles on the board
		for (int i = 0; i <= ReversiGame.TILES_PER - 1; i++) {
			for (int j = 0; j <= ReversiGame.TILES_PER - 1; j++) {
				graphic.setColor(new Color(0, 123, 0));
				graphic.fillRect(ReversiGame.BOARD_START_X + (ReversiGame.TILE_SIZE * j),
						ReversiGame.BOARD_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.TILE_SIZE,
						ReversiGame.TILE_SIZE);
				graphic.setColor(new Color(0, 0, 0));
				graphic.drawRect(ReversiGame.BOARD_START_X + (ReversiGame.TILE_SIZE * j),
						ReversiGame.BOARD_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.TILE_SIZE,
						ReversiGame.TILE_SIZE);

			}
		}

		for (int i = 0; i <= ReversiGame.TILES_PER - 1; i++) {
			for (int j = 0; j <= ReversiGame.TILES_PER - 1; j++) {

				if (i == j) {
					graphic.setColor(new Color(255, 255, 255));
					graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * j),
							ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
							ReversiGame.PIECE_SIZE);
					graphic.setColor(new Color(0, 0, 0));
					if (i < ReversiGame.TILES_PER / 2) {
						if (i % 2 == 1) {
							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j - 1)),
									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
									ReversiGame.PIECE_SIZE);
							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j + 1)),
									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
									ReversiGame.PIECE_SIZE);
						}
					} else {
						if (i % 2 == 0) {
							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j - 1)),
									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
									ReversiGame.PIECE_SIZE);
							graphic.fillOval(ReversiGame.PIECE_START_X + (ReversiGame.TILE_SIZE * (j + 1)),
									ReversiGame.PIECE_START_Y + (ReversiGame.TILE_SIZE * i), ReversiGame.PIECE_SIZE,
									ReversiGame.PIECE_SIZE);
						}
					}
				}
			}
		}
	}
}
