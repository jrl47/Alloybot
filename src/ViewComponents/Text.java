package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.FixedFont;
import View.ScreenBuilder;

public class Text extends ViewComponent {

	protected String myString;
	protected BufferedImage myFont;
	protected int mySize;
	public Text(int xx, int yy, String s, int size, BufferedImage font) {
		super(null, xx, yy);
		myString = s;
		mySize = size;
		myFont = font;
	}

	@Override
	public BufferedImage loadImage() {
		FixedFont f = new FixedFont(myFont, 6);
		return f.getStringImage(myString, mySize);
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
