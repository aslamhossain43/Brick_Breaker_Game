
package Game_Package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game_Play extends JPanel implements KeyListener, ActionListener {
	private ImageIcon down;
	
	private boolean play = false;
	private int totalBrick = 21;
	public int scors = 0;
	private Timer timer;
	private int delay = 8;
	private int playerX = 310;
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;

	// MapGenerator type variable,it is for calling
	private MapGenerator map;

	public Game_Play() {
		
		map = new MapGenerator(3, 7);
	addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}

	public void paint(java.awt.Graphics g) {

		// Background
		g.setColor(Color.BLUE);
		g.fillRect(0, 20, 692, 572);
		
		

		//down=new ImageIcon("nice_way1.jpg");
		//down.paintIcon(this,g,0,0);
		// calling MapGenerator,
		map.draw((Graphics2D) g);
		// Borders
		g.setColor(Color.YELLOW);
		g.fillRect(0, 20, 3, 572);
		g.fillRect(0, 20, 692, 3);
		g.fillRect(691, 20, 3, 592);

	// top score level
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 695, 20);
	/*	// g.setColor(Color.RED);
		g.setFont(new Font("Comic sans MS", Font.BOLD, 20));
		g.drawString(" Top Score : ", 2, 15);
*/
		// score
		g.setColor(Color.RED);
		g.setFont(new Font("Comic sans MS", Font.BOLD, 20));
		g.drawString(" Scores : " + scors, 550, 16);
		
		// Paddle
		g.setColor(Color.ORANGE);
		g.fillRect(playerX, 532, 100, 8);

		// ball
		g.setColor(Color.RED);
		g.fillOval(ballPosX, ballPosY, 20, 20);

		// if win
		if (totalBrick <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			g.setColor(Color.RED);
			g.setFont(new Font("Comic sans MS", Font.BOLD, 30));
			g.drawString(" YOU WON ", 190, 300);

			g.setFont(new Font("Comic sans MS", Font.BOLD, 25));
			g.drawString(" Press ENTER to Restart ", 230, 350);
			/*
			 * //top score
			 * 
			 * 
			 * g.setColor(Color.RED); g.setFont(new Font("Comic sans MS",
			 * Font.BOLD, 20)); g.drawString("  You are top Scorer ." , 122,
			 * 15);
			 * 
			 */

		}
		// game over
		if (ballPosY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Comic sans MS", Font.BOLD, 30));
			g.drawString(" GAME OVER, SCORE :  ", 190, 300);

			g.setFont(new Font("Comic sans MS", Font.BOLD, 25));
			g.drawString(" PRESS ENTER TO RESTART ", 230, 350);
		
			
		}
		g.dispose();

	};

	@Override
	public void actionPerformed(ActionEvent event) {
		timer.start();
		if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 532, 100, 8))) {
			ballYdir = -ballYdir;
		}
		A: for (int i = 0; i < map.map.length; i++) {// map.map.length means
														// first map is object
			for (int j = 0; j < map.map[0].length; j++) {
				if (map.map[i][j] > 0) {
					int brickX = j * map.brikWidth + 80;
					int brickY = i * map.brikHeight + 50;
					int brickWidth = map.brikWidth;
					int brickHeight = map.brikHeight;
					Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
					Rectangle ballrect = new Rectangle(ballPosX, ballPosY, 20, 20);
					Rectangle brickreckt = rect;
					if (ballrect.intersects(brickreckt)) {
						map.setBrickValue(0, i, j);

						totalBrick--;
						scors += 5;

						if (ballPosX + 19 <= brickreckt.x || ballPosX + 1 >= brickreckt.x + brickreckt.width) {
							/*
							 * First logic:for ball,X diameter=20
							 * ballPosX=60,distance from x direction to first
							 * rectangle=80,so,60+19<80 Second logic : (.x)
							 * means distance from x axis to rectangle's first
							 * wall & (.width) means width of rectangle
							 */

							ballXdir = -ballXdir;
						} else {
							ballYdir = -ballYdir;
						}
						break A;

					}

				}
			}

		}

		if (play) {
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if (ballPosX < 0) {
				ballXdir = -ballXdir;

			}
			if (ballPosY < 30) {
				ballYdir = -ballYdir;

			}
			if (ballPosX > 670) {
				ballXdir = -ballXdir;

			}
		}
	repaint();

	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600) {
				playerX = 600;

			} else {
				moveRight();
			}

		}
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {

			if (playerX < 10) {
				playerX = 10;

			} else {
				moveLeft();
			}

		}
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballPosX = 120;
				ballPosY = 320;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				totalBrick = 21;
				scors = 0;
				map = new MapGenerator(3, 7);
				repaint();
			}

		}

	}

	public void moveRight() {
		play = true;
		playerX += 20;

	}

	public void moveLeft() {
		play = true;
		playerX -= 20;

	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

}
