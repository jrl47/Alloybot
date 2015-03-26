package ViewComponents;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ModelComponents.SelectionMenuOption;
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
	private List<BorderedButton> allButtons;
	private SelectionMenuTracker mySelectionTracker;
	private SelectionMenuTracker myCurrentListTracker;
	private int maxWidth;
	private int maxHeight;
	private int maxLength;
	private int maxWordLength;
	private int myButtonIndex;
	private int myMenuIndex;
	public BorderedSelectionMenu(int xx, int yy, int size, BufferedImage font, BufferedImage hoverFont, 
			BufferedImage border, BufferedImage hoverBorder) {
		super(null, xx, yy);
		myButtons = new ArrayList<List<BorderedButton>>();
		allButtons = new ArrayList<BorderedButton>();
		mySize = size;
		myFont = font;
		myHoverFont = hoverFont;
		myBorder = border;
		myHoverBorder = hoverBorder;
		myButtonIndex = -1;
		myMenuIndex = 0;
		mySelectionTracker = new SelectionMenuTracker();
		myCurrentListTracker = new SelectionMenuTracker();
	}
	public void loadSelection(List<List<String>> data){
		myData = data;
		loadButtons();
	}
	private void loadButtons() {
		for(int i=0; i<myData.size(); i++){
			for(int j=0; j<myData.size(); j++){
				if(myData.get(i).get(j).length()>maxWordLength)
					maxWordLength = myData.get(i).get(j).length();
			}
		}
		for(int i=0; i<myData.size(); i++){
			myButtons.add(new ArrayList<BorderedButton>());
		}
		int counter = 0;
		int menuCounter = 0;
		for(int i=0; i<myData.size(); i++){
			for(int j=0; j<myData.get(i).size(); j++){
				String s = myData.get(i).get(j);
				while(s.length()<maxWordLength){
					if(s.length() % 2 == 0){
						s += ' ';
					}
					else{
						s = ' ' + s;
					}
				}
				BorderedButton button = new BorderedButton(new SelectionMenuOption(mySelectionTracker, counter), 0, j*mySize*20+(mySize*20), s, mySize, myFont, myHoverFont, myBorder, myHoverBorder);
				counter++;
				allButtons.add(button);
				myButtons.get(i).add(button);
//				addComponent(button);
				if(myButtons.get(i).get(j).getImage().getHeight() > maxHeight){
					maxHeight = myButtons.get(i).get(j).getImage().getHeight();
				}
				if(myButtons.get(i).get(j).getImage().getWidth() > maxWidth){
					maxWidth = myButtons.get(i).get(j).getImage().getWidth();
				}
			}
			
			String s = ">NEXT>";
			while(s.length()<maxWordLength){
				if(s.length() % 2 == 0){
					s += ' ';
				}
				else{
					s = ' ' + s;
				}
			}
			BorderedButton button = new BorderedButton(new SelectionMenuOption(myCurrentListTracker, (menuCounter+1) % myData.size()), 0, 0, s, mySize, myFont, myHoverFont, myBorder, myHoverBorder);
			if(menuCounter==0)
				myCurrentListTracker.setOption((SelectionMenuOption)button.getComponent());
			menuCounter++;
			allButtons.add(button);
			myButtons.get(i).add(button);
//			addComponent(button);
			if(myData.get(i).size()>maxLength)
				maxLength = myData.get(i).size();
		}
		for(Button b: myButtons.get(myMenuIndex)){
			addComponent(b);
		}
	}
	public BufferedImage loadImage() {
		if(mySelectionTracker.getSelectedIndex()!=-1){
			if(mySelectionTracker.getSelectedIndex()!=myButtonIndex){
				if(myButtonIndex!=-1)
					allButtons.get(myButtonIndex).deselect();
				myButtonIndex = mySelectionTracker.getSelectedIndex();
				allButtons.get(myButtonIndex).select();
			}
		}
		System.out.println(myCurrentListTracker.getSelectedIndex());
		if(myCurrentListTracker.getSelectedIndex()!=myMenuIndex){
			System.out.println("YEE");
			for(Button b: myButtons.get(myMenuIndex)){
				removeComponent(b);
			}
			myMenuIndex = myCurrentListTracker.getSelectedIndex();
			for(Button b: myButtons.get(myMenuIndex)){
				addComponent(b);
			}
		}
		if(myImage == null)
			myImage = new BufferedImage(maxWidth, maxHeight*(1+maxLength), BufferedImage.TYPE_INT_ARGB);
		drawComponents();
		return myImage;
	}
	public BufferedImage loadHover(){
		return loadImage();
	}

}
