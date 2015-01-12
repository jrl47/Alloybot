package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.FixedFont;
import View.ScreenBuilder;

public class Text extends ViewComponent {

	String myString;
	BufferedImage myImage;
	int mySize;
	public Text(int xx, int yy, String s, int size, BufferedImage b) {
		super(null, xx, yy);
		myString = s;
		mySize = size;
		myImage = b;
	}

	@Override
	public BufferedImage loadImage() {
		FixedFont f = null;
		BufferedImage b = myImage;
		BufferedImage bb = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bb.createGraphics();
		g.drawImage(b, 0, 0, null);
		g.dispose();
		f = new FixedFont(bb, 6);
		return f.getStringImage(myString, mySize);
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}