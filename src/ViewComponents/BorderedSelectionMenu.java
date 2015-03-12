package ViewComponents;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import View.BorderedFixedFont;

public class BorderedSelectionMenu extends ViewComponent{

	protected String myString;
	protected int mySize;
	protected BufferedImage myFont;
	protected BufferedImage myHoverFont;
	private BufferedImage myBorder;
	private BufferedImage myHoverBorder;
	private List<Map<String, Integer>> myData;
	private int myIndex;
	public BorderedSelectionMenu(int xx, int yy, int size, BufferedImage font, BufferedImage hoverFont, 
			BufferedImage border, BufferedImage hoverBorder) {
		super(null, xx, yy);
		mySize = size;
		myFont = font;
		myHoverFont = hoverFont;
	}
	public void loadSelection(List<Map<String, Integer>> data){
		myData = data;
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
