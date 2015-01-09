package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.FixedFont;
import View.ScreenBuilder;

public class StartTitle extends ViewComponent{

	public StartTitle(int xx, int yy) {
		super(null, xx, yy);
	}

	@Override
	public BufferedImage loadImage() {
		FixedFont f = null;
		try {
			BufferedImage b = ImageIO.read(ScreenBuilder.class.getResource("/fonts.png"));
			BufferedImage bb = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bb.createGraphics();
			g.drawImage(b, 0, 0, null);
			g.dispose();
			f = new FixedFont(bb, 6);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f.getStringImage("ALLOXYBOT", 5);
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
