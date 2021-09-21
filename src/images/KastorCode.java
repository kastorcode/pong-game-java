package images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import services.Window;


public class KastorCode {
	public static final int WIDTH = 96, HEIGHT = 53;

	public static BufferedImage kastorcode;

	
	public KastorCode () {
		try {
			kastorcode = ImageIO.read(getClass().getResource("/images/kastorcode.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void render (Graphics g) {
		g.drawImage(kastorcode, (Window.WIDTH / 2) - (WIDTH / 2),
			(Window.HEIGHT / 2) - (HEIGHT / 2), null);
	}
}