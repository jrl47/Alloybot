package ViewComponents;

import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;
import View.FixedFont;

public class Button extends ViewComponent{

	protected String myString;
	protected int mySize;
	protected BufferedImage myFont;
	protected BufferedImage myHoverFont;
	protected BufferedImage mySelectedFont;
	protected boolean selected;
	public Button(ModelComponent c, int xx, int yy, String s, int size, BufferedImage font, BufferedImage hoverFont) {
		super(c, xx, yy);
		myString = s;
		mySize = size;
		myFont = font;
		myHoverFont = hoverFont;
	}

	@Override
	public BufferedImage loadImage() {
		if(selected&&mySelectedFont!=null){
			FixedFont f = new FixedFont(mySelectedFont, 6);
			return f.getStringImage(myString, mySize);
		}
		FixedFont f = new FixedFont(myFont, 6);
		return f.getStringImage(myString, mySize);
	}

	@Override
	public BufferedImage loadHover() {
		FixedFont f = new FixedFont(myHoverFont, 6);
		return f.getStringImage(myString, mySize);
	}
	public void setSelectedFont(BufferedImage b){
		mySelectedFont = b;
	}
	public void select(){
		selected = true;
	}
	public void deselect(){
		selected = false;
	}
	public void setComponent(ModelComponent c){
		myComponent = c;
	}

}
