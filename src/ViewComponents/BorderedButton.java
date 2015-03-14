package ViewComponents;

import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;
import View.BorderedFixedFont;

public class BorderedButton extends Button{

	private BufferedImage myBorder;
	private BufferedImage myHoverBorder;
	public BorderedButton(ModelComponent c, int xx, int yy, String s, int size,
			BufferedImage font, BufferedImage hoverFont, BufferedImage border, BufferedImage hoverBorder) {
		super(c, xx, yy, s.toUpperCase(), size, font, hoverFont);
		myBorder = border;
		myHoverBorder = hoverBorder;
	}
	
	public BufferedImage loadImage() {
		BorderedFixedFont f = new BorderedFixedFont(myFont, 6, myBorder);
		return f.getStringImage(myString, mySize);
	}
	public BufferedImage loadHover(){
		BorderedFixedFont f = new BorderedFixedFont(myHoverFont, 6, myHoverBorder);
		return f.getStringImage(myString, mySize);
	}
}
