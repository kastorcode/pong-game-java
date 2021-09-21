package services;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Window extends Canvas {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 240;
	public static final int HEIGHT = 120;
	public static final int SCALE = 3;

	JFrame frame;


	public Window () {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}