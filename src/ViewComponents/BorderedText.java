package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import View.BorderedFixedFont;
import View.FixedFont;

public class BorderedText extends Text{

	private BufferedImage myBorder;
	public BorderedText(int xx, int yy, String s, int size, BufferedImage font, BufferedImage border) {
		super(xx, yy, s, size, font);
		myBorder = border;
	}
	
	@Override
	public BufferedImage loadImage() {
		BorderedFixedFont f = new BorderedFixedFont(myFont, 6, myBorder);
		return f.getStringImage(myString, mySize);
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
}
