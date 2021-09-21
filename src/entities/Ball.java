package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.util.Random;

import services.Audio;
import services.Game;
import services.Score;
import services.Window;


public class Ball {
	public static final int WIDTH = 4, HEIGHT = 4;

	public static final double MAX_SPEED = 4.0;

	public static double x, y, dx, dy, speed;
	
	public static Audio ballSound;

	
	public Ball () {
		init();

		speed = 2.0;

		ballSound = new Audio("ball.wav");
	}

	
	public void init () {
		x = (Window.WIDTH / 2) - (WIDTH / 2);
		y = (Window.HEIGHT / 2) - (HEIGHT / 2);
		reverseDirection();
		dy *= -1;
	}

	
	public static void speedIncrements () {
		speed += 0.1;
	}
	
	
	public static void reverseDirection () {
		int angle = new Random().nextInt(120 - 45) + 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}

	
	public void tick () {
		if (x + (dx * speed) + WIDTH >= Window.WIDTH ||
			x + (dx * speed) < 0) {
			dx *= -1;
		}
		
		if (y < 0) {
			Score.playerScored();
			
			if (speed < MAX_SPEED) {
				Ball.speedIncrements();
				Enemy.speedIncrements();
			}

			Game.init();
			return;
		}
		else if (y >= Window.HEIGHT) {
			Score.enemyScored();
			Game.init();
			return;
		}
		
		Rectangle boundsBall = new Rectangle((int)(x + (dx * speed)),
			(int)(y + (dy * speed)), WIDTH, HEIGHT);

		Rectangle boundsPlayer = new Rectangle(Player.x, Player.y,
			Player.WIDTH, Player.HEIGHT);

		Rectangle boundsEnemy = new Rectangle((int)Enemy.x, (int)Enemy.y,
				Enemy.WIDTH, Enemy.HEIGHT);

		if (boundsBall.intersects(boundsPlayer)) {
			ballSound.play();
			reverseDirection();
			dy *= -1;
		}
		else if (boundsBall.intersects(boundsEnemy)) {
			ballSound.play();
			reverseDirection();
		}

		x += dx * speed;
		y += dy * speed;
	}

	
	public void render (Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, WIDTH, HEIGHT);
		g.drawOval((int)x, (int)y, WIDTH, HEIGHT);
	}
}