
package Game_Package;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class First_Game {

	public static void main(String[] args) {

		JFrame obj = new JFrame();
		obj.setBounds(10, 10, 700, 600);
		obj.setResizable(false);
		obj.setTitle("Breaker out");
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final Game_Play game_Play = new Game_Play();
		obj.getContentPane().add(game_Play);
		// for topScore
		final String name = JOptionPane.showInputDialog("Enter your name");
		final JLabel label1 = new JLabel();
		
		if (game_Play.scors == 105) {
			label1.setText(String.format(
					"1.Top scorer >>" + name + "\n2. Second top scorer >> Aslam\n3. Third top scorer >>Raju",
					new Font("Comic sans MS", Font.ITALIC, 20)));
		} else {
			label1.setText(String.format(
					"1. Top scorer >> Aslam 2.Second top scorer >>Raju",
					new Font("Comic sans MS", Font.ITALIC, 20)));
		}

		// menuBar
		JMenuBar menuBar = new JMenuBar();
		JMenuItem menu1 = new JMenuItem("HELP");
		menu1.setForeground(Color.BLUE);
		menu1.setFont(new Font("Comic sans MS", Font.HANGING_BASELINE, 16));

		JMenuItem menu2 = new JMenuItem("TOP SCORES");
		menu2.setForeground(Color.BLUE);
		menu2.setFont(new Font("Comic sans MS", Font.HANGING_BASELINE, 16));

		menuBar.add(menu1);
		menuBar.add(menu2);
		obj.setJMenuBar(menuBar);
		// topScoreFrame
		final JFrame topScoreFrame = new JFrame();
		topScoreFrame.setResizable(false);

		topScoreFrame.setBounds(300, 200, 500, 100);
		topScoreFrame.add(label1);
		menu1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,
						"Rules for playing this game !!!\n" + "1. If you press left arrow key then paddle moves left.\n"
								+ "2. If you press right arrow key then paddle moves right.\n"
								+ "3. If you dont touch ball with paddle then game will over !!!");

			}
		});

		menu2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				topScoreFrame.setVisible(true);
				if (game_Play.scors==105) {
					label1.setText(String.format("1. Top scorer is  "+name+"  2. Second top scorer is Aslam "+" 3. Third top scorer is Raju", new Font("Comic sans MS",Font.ITALIC, 20)));
				} else {
					label1.setText(String.format("1. Top scorer is Aslam. \n"+" 2. Second top scorer is Raju", new Font("Comic sans MS",Font.ITALIC, 20)));
				}
			}
		});

		obj.setVisible(true);
	}

}
