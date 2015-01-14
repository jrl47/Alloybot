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
//		FixedFont f = null;
//		FixedFont back = null;
//		BufferedImage b = myFont;
//		f = new FixedFont(b, 6);
//		b = myBorder;
//		back = new FixedFont(b, 6);
//		String s = "";
//		s += "A";
//		for(int i=0; i<myString.length()-1; i++){
//			s+="B";
//		}
//		s += "C";
//		BufferedImage result = back.getStringImage(s, mySize);
//		Graphics2D g = result.createGraphics();
//		g.drawImage(f.getStringImage(myString, mySize), 3*mySize, 3*mySize, null);
//		return result;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
}
