package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import View.ScreenBuilder;

public class Background extends ViewComponent{
	
	private BufferedImage myImage;
	public Background(int xx, int yy, BufferedImage b) {
		super(null, xx, yy);
		myImage = b;
	}

	@Override
	public BufferedImage loadImage() {
		BufferedImage b = myImage;
		BufferedImage bb = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bb.createGraphics();
		g.drawImage(b, 0, 0, null);
		g.dispose();
		return bb;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
