package services;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Ball;
import entities.Enemy;
import entities.Player;

import images.KastorCode;

import java.awt.image.BufferStrategy;


public class Game extends Window implements Runnable, KeyListener {
	private static final long serialVersionUID = 2L;

	private Thread thread;

	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public static KastorCode kastorcode;
	public static Audio bgSound;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;

	public static boolean paused;

	
	public Game () {
		super();

		paused = true;
		kastorcode = new KastorCode();
		bgSound = new Audio("sonic.wav").loop();
		addKeyListener(this);
		player = new Player();
		enemy = new Enemy();
		ball = new Ball();

		thread = new Thread(this);
		thread.start();
	}

	
	public static void pauseOrResume () {
		paused = !paused;
		bgSound.toggle();
	}

	
	public static void init () {
		player.init();
		enemy.init();
		ball.init();
	}


	public void tick () {
		if (paused) {
			return;
		}

		player.tick();
		enemy.tick();
		ball.tick();
	}

	
	public void render () {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = layer.getGraphics();
		g.setColor(new Color(0, 20, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (paused) {
			kastorcode.render(g);
		}

		Score.render(g);
		player.render(g);
		enemy.render(g);
		ball.render(g);

		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();
	}

	
	public void run () {
		while (true) {
			tick();
			render();

			try {
				Thread.sleep(1000 / 60);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			pauseOrResume();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}