package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.ScreenBuilder;

public class EndBackground extends ViewComponent{

	public EndBackground(ModelComponent c, int xx, int yy) {
		super(c, xx, yy);
	}

	@Override
	public BufferedImage loadImage() {
		try {
			BufferedImage b = ImageIO.read(ScreenBuilder.class.getResource("/gameover.png"));
			BufferedImage bb = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bb.createGraphics();
			g.drawImage(b, 0, 0, null);
			g.dispose();
			return bb;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
