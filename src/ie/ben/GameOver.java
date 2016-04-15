package ie.ben;

import static javax.swing.GroupLayout.Alignment.CENTER;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JDialog {

	public GameOver(JPanel parent, int scoreOne, int scoreTwo) {

		// super(parent);

		initUI(scoreOne, scoreTwo);
	}

	private void initUI(int scoreOne, int scoreTwo) {

		ImageIcon icon; icon = new ImageIcon("winner.png");
		

		String text = null;
		if (scoreOne > scoreTwo) {
			text = "<html>Player 1</html>";
			
			icon = new ImageIcon("winner.png");
		} else if(scoreTwo > scoreOne) {
			
			text = "<html>Player 2</html>";
			
			icon = new ImageIcon("winner.png");
		} else {
			
			text = "<html>Game is tied</html>";
			
			icon = new ImageIcon("draw.png");
		}
		
		JLabel iconLabel = new JLabel(icon);
		
		JLabel textLabel = new JLabel(text);
		textLabel.setFont(new Font("Serif", Font.BOLD, 25));

		JButton newBtn = new JButton("New?");
		newBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				dispose();
			}
		});

		createLayout(textLabel, iconLabel, newBtn);

		setModalityType(ModalityType.APPLICATION_MODAL);

		setTitle("About Notes");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getParent());
	}

	private void createLayout(JComponent... arg) {

		Container pane = getContentPane();
		GroupLayout group = new GroupLayout(pane);
		pane.setLayout(group);

		group.setAutoCreateContainerGaps(true);
		group.setAutoCreateGaps(true);

		group.setHorizontalGroup(group.createParallelGroup(CENTER).addComponent(arg[0]).addComponent(arg[1])
				.addComponent(arg[2]).addGap(200));

		group.setVerticalGroup(group.createSequentialGroup().addGap(30).addComponent(arg[0]).addGap(20)
				.addComponent(arg[1]).addGap(20).addComponent(arg[2]).addGap(30));

		pack();
	}
}
