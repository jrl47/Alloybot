package ViewComponents;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;
import View.FixedFont;

public class BorderedButton extends Button{

	private BufferedImage myBorder;
	private BufferedImage myHoverBorder;
	public BorderedButton(ModelComponent c, int xx, int yy, String s, int size,
			BufferedImage font, BufferedImage hoverFont, BufferedImage border, BufferedImage hoverBorder) {
		super(c, xx, yy, s, size, font, hoverFont);
		myBorder = border;
		myHoverBorder = hoverBorder;
	}
	
	public BufferedImage loadImage() {
		FixedFont f = null;
		FixedFont back = null;
		BufferedImage b = myFont;
		f = new FixedFont(b, 6);
		b = myBorder;
		back = new FixedFont(b, 6);
		String s = "";
		s += "A";
		for(int i=0; i<myString.length()-1; i++){
			s+="B";
		}
		s += "C";
		BufferedImage result = back.getStringImage(s, mySize);
		Graphics2D g = result.createGraphics();
		g.drawImage(f.getStringImage(myString, mySize), 3*mySize, 3*mySize, null);
		return result;
	}
}
