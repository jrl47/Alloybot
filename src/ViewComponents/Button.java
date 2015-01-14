package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.FixedFont;
import View.ScreenBuilder;

public class Button extends ViewComponent{

	protected String myString;
	protected int mySize;
	protected BufferedImage myFont;
	protected BufferedImage myHoverFont;
	public Button(ModelComponent c, int xx, int yy, String s, int size, BufferedImage font, BufferedImage hoverFont) {
		super(c, xx, yy);
		myString = s;
		mySize = size;
		myFont = font;
		myHoverFont = hoverFont;
	}

	@Override
	public BufferedImage loadImage() {
		FixedFont f = new FixedFont(myFont, 6);
		return f.getStringImage(myString, mySize);
	}

	@Override
	public BufferedImage loadHover() {
		FixedFont f = new FixedFont(myHoverFont, 6);
		return f.getStringImage(myString, mySize);
	}

}
