package entities;

import java.awt.Color;
import java.awt.Graphics;

import services.Window;


public class Enemy {
	public static final int WIDTH = 40, HEIGHT = 5;

	public static double x, y, speed;

	
	public Enemy () {
		init();

		speed = 0.01;
	}

	
	public void init () {
		x = (Window.WIDTH / 2) - (WIDTH / 2);
		y = 0;
	}
	
	
	public static void speedIncrements () {
		speed += 0.01;
	}

	
	public void tick () {
		x += (Ball.x - x - 6) * speed;
		
		if (x + WIDTH > Window.WIDTH) {
			x = Window.WIDTH - WIDTH;
		}
		else if (x < 0) {
			x = 0;
		}
	}

	
	public void render (Graphics g) {
		g.setColor(new Color(113, 89, 193));
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
}