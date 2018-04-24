
package Game_Package;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brikWidth;
	public int brikHeight;

	public MapGenerator(int row, int col) {

		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {// map.length means loop only for
												// row
			for (int j = 0; j < map[0].length; j++) {// map[0].length means loop
														// for column ,for first
														// row ,we can use 0=i
				map[i][j] = 1;/*
								 * every elements of map is 1 means elements are
								 * shown on the panel & ball is not intersect
								 * wit brick if we insert zero then brick will
								 * vanish
								 */
			}

		}

		brikWidth = 540 / col;
		brikHeight = 150 / row;

	}

	public void draw(Graphics2D g) {// method for drawing brick
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					g.setColor(Color.WHITE);
					g.fillRect(j * brikWidth + 80, i * brikHeight + 50, brikWidth,
							brikHeight);/*
										 * creates big white F color rectangle
										 * 
										 * create main brick by lines
										 */
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLUE);
					g.drawRect(j * brikWidth + 80, i * brikHeight + 50, brikWidth, brikHeight);

				}

			}
		}

	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;/*
								 * value=0 means every element's value is zero &
								 * this is not shown on the panel,That means
								 * these will be vanished
								 */

	}
}
