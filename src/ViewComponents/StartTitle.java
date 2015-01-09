package ViewComponents;

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
			f = new FixedFont(ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")), 6);
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
