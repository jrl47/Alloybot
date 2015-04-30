package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.OreData;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import ModelComponents.Ore;
import ModelComponents.ResourceManager;
import ModelComponents.Robot;
import ModelComponents.RobotCreationButton;
import View.AlloyFont;

public class RobotCreationResourceScreen extends ViewComponent{

	private ResourceManager myManager;
	private ModelMap myMap;
	private AlloyBorderedSelectionMenu myOreSelection;
	private AlloyBorderedSelectionMenu mySizeSelection;
	private AlloyBorderedButton myCreationButton;
	private int myOreIndex;
	private int mySizeIndex;
	private boolean loaded;
	public RobotCreationResourceScreen(ResourceManager resourceManager, ModelMap map, RobotCreationButton creationButton, int xx, int yy) {
		super(null, xx, yy, 800, 450);
		myManager = resourceManager;
		myMap = map;
		myCreationButton = null;
		try {
			myCreationButton = new AlloyBorderedButton(creationButton, 10, 90, "CREATE ROBOT", 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		createOreMenu();
		createSizeMenu();
	}

	private void createOreMenu() {
		myOreSelection = null;
		try {
			myOreSelection = new AlloyBorderedSelectionMenu(714, 26, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<List<String>> menuStrings = new ArrayList<List<String>>();
		for(int i=0; i<3; i++){
			menuStrings.add(new ArrayList<String>());
		}
		for(int i=0; i<21; i++){
			menuStrings.get(i/7).add(OreData.getOreObject(i).getMyName());
		}
		myOreSelection.loadSelection(menuStrings);
		myComponents.add(myOreSelection);
	}

	private void createSizeMenu() {
		mySizeSelection = null;
		try {
			mySizeSelection = new AlloyBorderedSelectionMenu(570, 26, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<List<String>> menuStrings = new ArrayList<List<String>>();
		for(int i=0; i<2; i++){
			menuStrings.add(new ArrayList<String>());
		}
		menuStrings.get(0).add("SIZE 1 (1 ORE)");
		menuStrings.get(0).add("SIZE 2 (4 ORE)");
		menuStrings.get(0).add("SIZE 3 (27 ORE)");
		menuStrings.get(0).add("SIZE 4 (256 ORE)");
		menuStrings.get(1).add("SIZE 5 (3125 ORE)");
		menuStrings.get(1).add("SIZE 6 (46656 ORE)");
		menuStrings.get(1).add("SIZE 7 (823543 ORE)");
		mySizeSelection.loadSelection(menuStrings);
		myComponents.add(mySizeSelection);
	}
	@Override
	public BufferedImage loadImage() {
		if(((RobotCreationButton) myCreationButton.getComponent()).wasTriggered()){
			loaded = false;
		}
		myImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
		Graphics g = myImage.getGraphics();
		AlloyFont font = null;
		try {
			font = new AlloyFont();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(myOreSelection.getSelectedIndex()!=myOreIndex || mySizeSelection.getSelectedIndex()!=mySizeIndex){
			loaded = false;
			myOreIndex = myOreSelection.getSelectedIndex();
			mySizeIndex = mySizeSelection.getSelectedIndex();
		}
		if(myOreSelection.getSelectedIndex()!=-1 && mySizeSelection.getSelectedIndex()!=-1 && !loaded){
			removeComponents();
			myComponents.add(myOreSelection);
			myComponents.add(mySizeSelection);
			try {
				addComponent(new AlloyText("COST:", 2, 10, 10));
				int size = mySizeSelection.getSelectedIndex()+1;
				int sizeCost = (int) Math.pow(size, size);
				addComponent(new AlloyText(Integer.toString(sizeCost), 2, 88, 10));
				int oreIndex = myOreSelection.getSelectedIndex();
				Ore ore = OreData.getOreObject(oreIndex);
				String oreName = ore.getMyName().toUpperCase();
				addComponent(new AlloyText(oreName, 2, Integer.toString(sizeCost).length()*14 + 100, 10));
				addComponent(new AlloyText(", 1 GEM", 2, (Integer.toString(sizeCost).length() + oreName.length())*14 + 100, 10));
			
				addComponent(new AlloyText(oreName, 2, 10, 50));
				addComponent(new AlloyText("AVAILABLE:", 2, oreName.length()*14 + 24, 50));
				addComponent(new AlloyText(Integer.toString(myManager.getOre(oreIndex)), 2, oreName.length()*14 + 24 + 14 + 140, 50));
				
				if(sizeCost > myManager.getOre(oreIndex)){
					removeComponent(myCreationButton);
					addComponent(new AlloyText("NOT ENOUGH RESOURCES", 2, 10, 90));
				}
				else if(myMap.getCell(myMap.getCurrentHighlightedCell().getX(), myMap.getCurrentHighlightedCell().getY() + 1).getObjects().size()!=0){
					removeComponent(myCreationButton);
					addComponent(new AlloyText("ROBOT IN THE WAY OF CREATION", 2, 10, 90));
				}
				else{
					((RobotCreationButton)myCreationButton.getComponent()).setCost(oreIndex, size);
					addComponent(myCreationButton);
				}
				
				addComponent(new AlloyText("OIL EFFICIENCY:", 1, 10, 130));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyOil(), size)), 1, "OIL EFFICIENCY:".length()*7 + 17, 130));
				int length1 = "OIL EFFICIENCY:".length()*7 + 24 + Integer.toString(Robot.processStat(ore.getMyOil(), size)).length()*7 + 14;
				
				addComponent(new AlloyText("ORE EFFICIENCY:", 1, length1, 130));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyOre(), size)), 1, "ORE EFFICIENCY:".length()*7 + 7 + length1, 130));
				int length2 = length1 + "ORE EFFICIENCY".length()*7 + Integer.toString(Robot.processStat(ore.getMyOre(), size)).length()*7 + 14 + 14 + 14;
				
				addComponent(new AlloyText("GEM EFFICIENCY:", 1, length2, 130));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyGems(), size)), 1, "GEM EFFICIENCY:".length()*7 + 7 + length2, 130));
				
				
				addComponent(new AlloyText("DIVERSITY:", 1, 10, 150));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyDiversity(), size)), 1, "DIVERSITY:".length()*7 + 17, 150));
				length1 = "DIVERSITY:".length()*7 + 24 + Integer.toString(Robot.processStat(ore.getMyDiversity(), size)).length()*7 + 14;
				
				addComponent(new AlloyText("DISTANCE:", 1, length1, 150));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyDistance(), size)), 1, "DISTANCE:".length()*7 + 7 + length1, 150));
				length2 = length1 + "DISTANCE:".length()*7 + Integer.toString(Robot.processStat(ore.getMyDistance(), size)).length()*7 + 14 + 14 + 14;
				
				addComponent(new AlloyText("LUCK:", 1, length2, 150));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyLuck(), size)), 1, "LUCK:".length()*7 + 7 + length2, 150));
				
			
				addComponent(new AlloyText("POWER:", 1, 10, 170));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyPower(), size)), 1, "POWER:".length()*7 + 17, 170));
				length1 = "POWER:".length()*7 + 24 + Integer.toString(Robot.processStat(ore.getMyPower(), size)).length()*7 + 14;
				
				addComponent(new AlloyText("DURABILITY:", 1, length1, 170));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyDurability(), size)), 1, "DURABILITY:".length()*7 + 7 + length1, 170));
				length2 = length1 + "DURABILITY:".length()*7 + Integer.toString(Robot.processStat(ore.getMyDurability(), size)).length()*7 + 14 + 14 + 14;
				
				addComponent(new AlloyText("MAGIC:", 1, length2, 170));
				addComponent(new AlloyText(Integer.toString(Robot.processStat(ore.getMyMagic(), size)), 1, "MAGIC:".length()*7 + 7 + length2, 170));
				
				loaded = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (myOreSelection.getSelectedIndex()==-1 && mySizeSelection.getSelectedIndex()==-1){
			removeComponents();
			myComponents.add(myOreSelection);
			myComponents.add(mySizeSelection);
			try {
				addComponent(new AlloyText("SELECT A MATERIAL AND SIZE", 2, 10, 10));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		g.drawImage(font.getStringImage("SIZES:", 1), 574, 7, null);
		g.drawImage(font.getStringImage("ORES:", 1), 718, 7, null);
		
		
		{ // old mini menu for temp testing purposes
			g.drawImage(font.getStringImage("OIL AVAILABLE:", 1), 10, 410, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getOil()), 1), 120, 410, null);
			g.drawImage(font.getStringImage("GEMS AVAILABLE:", 1), 10, 430, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getGems()), 1), 132, 430, null);
		}
		
		drawComponents();
		return myImage;
	}
	
//	// Takes base stat and enters into equation to find robot's practical stat based on size
//	public int processStat(int base, int size){
//		int std = (int)Math.pow(2, base);
//		int actualStat = (int) (Math.pow(size, size)*std*Math.pow(size, base));
//		return actualStat;
//	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
