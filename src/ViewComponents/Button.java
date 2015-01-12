package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.FixedFont;
import View.ScreenBuilder;

public class Button extends ViewComponent{

	String myString;
	int mySize;
	BufferedImage myFont;
	BufferedImage myHoverFont;
	public Button(ModelComponent c, int xx, int yy, String s, int size, BufferedImage font, BufferedImage hoverFont) {
		super(c, xx, yy);
		myString = s;
		mySize = size;
		myFont = font;
		myHoverFont = hoverFont;
	}

	@Override
	public BufferedImage loadImage() {
		FixedFont f = null;
		BufferedImage b = myFont;
		BufferedImage bb = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bb.createGraphics();
		g.drawImage(b, 0, 0, null);
		g.dispose();
		f = new FixedFont(bb, 6);
		return f.getStringImage(myString, mySize);
	}

	@Override
	public BufferedImage loadHover() {
		FixedFont f = null;
		BufferedImage b = myHoverFont;
		BufferedImage bb = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bb.createGraphics();
		g.drawImage(b, 0, 0, null);
		g.dispose();
		f = new FixedFont(bb, 6);
		return f.getStringImage(myString, mySize);
	}

}
