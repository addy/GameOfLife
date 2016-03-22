package Test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashMap;

//import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import Program.Model;

public class TestModel {
	Model model;
	int x = 5;
	int y = 5;
	//Model mockModel = Mockito.mock(Model.class);
	HashMap<Point, Boolean> grid;

	@Before
	public void setUp() throws Exception {
		model = new Model(x, y);

		grid = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Point p = new Point(i, j);
				if ((i + j) % 3 == 0) {
					grid.put(p, false);
				} else {
					grid.put(p, true);
				}
			}
		}
	}

	@Test
	public void testHashMapGridSize() {
		// assertTrue(model.grid1.size() == x*y);
		assertEquals(Model.grid1.size(), x * y);
	}

	@Test
	public void testCountAliveNbrsNonExistingPosition() {
		assertEquals(model.countAliveNeighbors(new Point(-1, -1)), -1);
	}

	@Test
	public void testGameRule1() {
		grid.clear();
		grid.put(new Point(0,0), false );
		grid.put(new Point(0,1), true);
		grid.put(new Point(0,2), false);
		grid.put(new Point(1,0), false);
		grid.put(new Point(1,1), false);
		grid.put(new Point(1,2), false);
		grid.put(new Point(2,0), false);
		grid.put(new Point(2,1), true);
		grid.put(new Point(2,2), true);
		model.setGrid(grid);
		model.getNewGameState();
		assertEquals(model.grid1.get(new Point(0,0)), false);
	}
	@Test
	public void testGameRule2ForDead() {
		model.setGrid(grid);
		model.getNewGameState();
		assertEquals(model.grid1.get(new Point(3,0)), false);
	}
	@Test
	public void testGameRule2ForLive() {
		grid.clear();
		grid.put(new Point(0,0), false );
		grid.put(new Point(0,1), true);
		grid.put(new Point(0,2), false);
		grid.put(new Point(1,0), false);
		grid.put(new Point(1,1), false);
		grid.put(new Point(1,2), true);
		grid.put(new Point(2,0), false);
		grid.put(new Point(2,1), true);
		grid.put(new Point(2,2), true);
		model.setGrid(grid);
		model.getNewGameState();
		assertEquals(model.grid1.get(new Point(2,2)), true);
	}
	@Test
	public void testGameRule3() {
		model.setGrid(grid);
		model.getNewGameState();
		assertEquals(model.grid1.get(new Point(1,1)), false);
	}
	
	@Test
	public void testGameRule4() {
		model.setGrid(grid);
		model.getNewGameState();
		assertEquals(model.grid1.get(new Point(0,0)), true);
	}

}
