package Program;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Model {

	public static HashMap<Point, Boolean> grid1;
	public static HashMap<Point, Boolean> grid2;
	Random random = new Random();
	// constructor
	public Model(int rows, int cols) {
		// Initializing the grid by use of random boolean values.
		// We are sure that the hashmap has not been created so far so no need
		// to check the condition if-exists.
		grid1 = new HashMap<>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Point key = new Point(i, j);
				boolean bit = random.nextBoolean();
				if (!grid1.containsKey(key))
					grid1.put(key, bit);
			}
		}
	}
	// rules of the game
	// count alive neighbors of a given cell
	public int countAliveNeighbors(Point pos) {
		int count = 0;
		if (grid1.containsKey(pos)) {
			HashSet<Point> nbrs = new HashSet<>();

			nbrs.add(new Point(pos.x - 1, pos.y - 1));
			nbrs.add(new Point(pos.x - 1, pos.y));
			nbrs.add(new Point(pos.x - 1, pos.y + 1));
			nbrs.add(new Point(pos.x, pos.y - 1));
			nbrs.add(new Point(pos.x, pos.y + 1));
			nbrs.add(new Point(pos.x + 1, pos.y - 1));
			nbrs.add(new Point(pos.x + 1, pos.y));
			nbrs.add(new Point(pos.x + 1, pos.y + 1));

			for (Point p : nbrs) {
				if (grid1.containsKey(p)) {
					if (grid1.get(p) == true)
						count++;
				}
			}
			
			return count;
		}
		return -1;
	}

	// getting new game state in grid2 by following the game rules:
	/*
	 * Any live cell with fewer than two live neighbors dies, as if caused by
	 * under-population. Any live cell with two or three live neighbors lives on
	 * to the next generation. Any live cell with more than three live neighbors
	 * dies, as if by over-population. Any dead cell with exactly three live
	 * neighbors becomes a live cell, as if by reproduction.
	 */
	public void getNewGameState() {
		int aliveNeighborCount;
		grid2 = new HashMap<>();
		for (Point p : grid1.keySet()) {
			aliveNeighborCount = countAliveNeighbors(p);
			if (aliveNeighborCount < 2 || aliveNeighborCount > 3) {
				grid2.put(p, false);
			} else if (aliveNeighborCount == 3) {
				grid2.put(p, true);
			} else {
				grid2.put(p, grid1.get(p));
			}
		}
		grid1 = new HashMap<>(grid2);
		grid2.clear();
	}
	
	//used as a mock! Not used in the program otherwise
	public void setGrid(HashMap<Point, Boolean> grid){
		grid1 = new HashMap<>(grid);
	}
}
