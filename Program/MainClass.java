package Program;

import java.awt.Point;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.Transient;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JPanel {

	static Model model;
	static int rows = 200;
	static int cols = 100;
	static int rounds = 1000;
	int counter = 0;

	public static void main(String[] args) throws InterruptedException {

		model = new Model(rows, cols);

		MainClass m = new MainClass();
		m.repaint();

		JFrame frame = new JFrame();
		frame.getContentPane().add(m);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		for (int round = 0; round < rounds; round++) {
			model.getNewGameState();
			Thread.sleep(100);

			m.repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color gColor = g.getColor();
		

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Point p = new Point(i, j);
				if (model.grid1.get(p)) {
					g.setColor(Color.black);
					g.fillRect(i * 10, j * 10, 10, 10);
				} else {
					g.setColor(Color.DARK_GRAY);
					g.drawRect(i * 10, j * 10, 10, 10);
				}
			}

		}
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Iteration: " + counter++, 30, 30);
		g.setColor(gColor);

	}

	@Override
	@Transient
	public Dimension getPreferredSize() {
		return new Dimension(rows * 10, cols * 10);
	}

}
