package entities;

import java.awt.Color;
import java.awt.Graphics;

import services.Window;


public class Player {
	public static final int WIDTH = 40, HEIGHT = 5;
	public static final double speed = 0.2;

	public boolean right, left;

	public static int x, y;

	
	public Player () {
		init();
	}

	
	public void init () {
		right = left = false;
		x = (Window.WIDTH / 2) - (WIDTH / 2);
		y = Window.HEIGHT - HEIGHT;
	}

	
	public void tick () {
		if (right && x + WIDTH < Window.WIDTH) {
			x += Ball.speed + Player.speed;
		}
		else if (left && x > 0) {
			x -= Ball.speed - Player.speed;
		}
	}


	public void render (Graphics g) {
		g.setColor(new Color(227, 76, 15));
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
}