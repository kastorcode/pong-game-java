package services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Score {
	public static final int fontSize = 8;

	public static int player, enemy = 0;

	
	public static void playerScored () {
		player++;
	}

	
	public static void enemyScored() {
		enemy++;
	}

	
	public static void render (Graphics g) {
		g.setFont(new Font("Ubuntu", Font.PLAIN, fontSize));
		g.setColor(new Color(227, 76, 15));
		g.drawString(Integer.toString(player),
			Window.WIDTH - (fontSize * 2),
			(Window.HEIGHT / 2) + fontSize);

		g.setColor(new Color(113, 89, 193));
		g.drawString(Integer.toString(enemy),
			Window.WIDTH - (fontSize * 2),
			(Window.HEIGHT / 2) - fontSize);
	}
}