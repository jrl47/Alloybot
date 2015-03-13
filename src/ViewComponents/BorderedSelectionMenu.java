package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ModelComponents.SelectionMenuTracker;
import View.BorderedFixedFont;

public class BorderedSelectionMenu extends ViewComponent{

	protected String myString;
	protected int mySize;
	protected BufferedImage myFont;
	protected BufferedImage myHoverFont;
	private BufferedImage myBorder;
	private BufferedImage myHoverBorder;
	private List<List<String>> myData;
	private List<List<BorderedButton>> myButtons;
	private SelectionMenuTracker mySelectionTracker;
	private int maxWidth;
	private int maxHeight;
	private int myIndex;
	public BorderedSelectionMenu(int xx, int yy, int size, BufferedImage font, BufferedImage hoverFont, 
			BufferedImage border, BufferedImage hoverBorder) {
		super(null, xx, yy);
		myButtons = new ArrayList<List<BorderedButton>>();
		mySize = size;
		myFont = font;
		myHoverFont = hoverFont;
		myBorder = border;
		myHoverBorder = hoverBorder;
	}
	public void loadSelection(List<List<String>> data){
		myData = data;
		loadButtons();
	}
	private void loadButtons() {
		for(int i=0; i<myData.size(); i++){
			myButtons.add(new ArrayList<BorderedButton>());
		}
		for(int i=0; i<myData.size(); i++){
			for(int j=0; j<myData.get(i).size(); j++){
				try {
					myButtons.get(i).add(new AlloyBorderedButton(mySelectionTracker, x, y + j*mySize*14, myData.get(i).get(j), mySize));
				} catch (IOException e) {
					e.printStackTrace();
				}
				myComponents.add(myButtons.get(i).get(j));
				if(myButtons.get(i).get(j).getImage().getHeight() > maxHeight){
					maxHeight = myButtons.get(i).get(j).getImage().getHeight();
				}
				if(myButtons.get(i).get(j).getImage().getWidth() > maxWidth){
					maxWidth = myButtons.get(i).get(j).getImage().getWidth();
				}
			}
		}
	}
	public BufferedImage loadImage() {
		return new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);
	}
	public BufferedImage loadHover(){
		return loadImage();
	}

}
