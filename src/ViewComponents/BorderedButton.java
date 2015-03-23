package ViewComponents;

import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;
import View.BorderedFixedFont;
import View.FixedFont;

public class BorderedButton extends Button{

	private BufferedImage myBorder;
	private BufferedImage myHoverBorder;
	private BufferedImage mySelectedBorder;
	public BorderedButton(ModelComponent c, int xx, int yy, String s, int size,
			BufferedImage font, BufferedImage hoverFont, BufferedImage border, BufferedImage hoverBorder) {
		super(c, xx, yy, s.toUpperCase(), size, font, hoverFont);
		myBorder = border;
		myHoverBorder = hoverBorder;
	}
	
	public BufferedImage loadImage() {
		if(selected&&mySelectedFont!=null){
			BorderedFixedFont f = new BorderedFixedFont(mySelectedFont, 6, mySelectedBorder);
			return f.getStringImage(myString, mySize);
		}
		BorderedFixedFont f = new BorderedFixedFont(myFont, 6, myBorder);
		return f.getStringImage(myString, mySize);
	}
	public BufferedImage loadHover(){
		BorderedFixedFont f = new BorderedFixedFont(myHoverFont, 6, myHoverBorder);
		return f.getStringImage(myString, mySize);
	}
	public void setSelectedBorder(BufferedImage b){
		mySelectedBorder = b;
	}
}
