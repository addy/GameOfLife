
import java.awt.Point;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.Transient;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JPanel
{
	static Model model;
	static int rows = 250;
	static int cols = 125;
	static int rounds = 10000;
	int counter = 0;

	public static void main(String[] args) throws InterruptedException 
	{
		model = new Model(rows, cols);
		MainClass m = new MainClass();
		m.repaint();

		JFrame frame = new JFrame();
		frame.getContentPane().add(m);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		for (int round = 0; round < rounds; round++) 
		{
			model.getNewGameState();
			m.repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Color gColor = g.getColor();
		
		for (int i = 0; i < rows; i++) 
		{
			for (int j = 0; j < cols; j++) 
			{
				Point p = new Point(i, j);
				if (model.grid1.get(p)) 
				{
					g.setColor(Color.BLUE);
					g.fillRect(i * 5, j * 5, 5, 5);
				}
			}
		}
		g.setColor(gColor);
	}

	@Override
	@Transient
	public Dimension getPreferredSize()
	{
		return new Dimension(rows * 5, cols * 5);
	}
}
