package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ModelComponents.SelectionMenuTracker;

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
	private int maxLength;
	private int maxWordLength;
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
			for(int j=0; j<myData.size(); j++){
				
			}
		}
		for(int i=0; i<myData.size(); i++){
			myButtons.add(new ArrayList<BorderedButton>());
		}
		for(int i=0; i<myData.size(); i++){
			for(int j=0; j<myData.get(i).size(); j++){
				BorderedButton button = new BorderedButton(mySelectionTracker, 0, 0 + j*mySize*20, myData.get(i).get(j), mySize, myFont, myHoverFont, myBorder, myHoverBorder);
				myButtons.get(i).add(button);
				addComponent(button);
				if(myButtons.get(i).get(j).getImage().getHeight() > maxHeight){
					maxHeight = myButtons.get(i).get(j).getImage().getHeight();
				}
				if(myButtons.get(i).get(j).getImage().getWidth() > maxWidth){
					maxWidth = myButtons.get(i).get(j).getImage().getWidth();
				}
			}
			if(myData.get(i).size()>maxLength)
				maxLength = myData.get(i).size();
		}
	}
	public BufferedImage loadImage() {
		if(myImage == null)
			myImage = new BufferedImage(maxWidth, maxHeight*maxLength, BufferedImage.TYPE_INT_ARGB);
		drawComponents();
		return myImage;
	}
	public BufferedImage loadHover(){
		return loadImage();
	}

}
