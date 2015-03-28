package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.OreData;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ModelComponents.Robot;
import View.AlloyFont;

public class RobotCreationResourceScreen extends ViewComponent{

	private ResourceManager myManager;
	private ModelMap myMap;
	private AlloyBorderedSelectionMenu myOreSelection;
	private AlloyBorderedSelectionMenu mySizeSelection;
	public RobotCreationResourceScreen(ResourceManager resourceManager, ModelMap m, int xx, int yy) {
		super(null, xx, yy, 800, 450);
		myManager = resourceManager;
		myMap = m;
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
		myImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
		Graphics g = myImage.getGraphics();
		AlloyFont font = null;
		try {
			font = new AlloyFont();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(myOreSelection.getSelectedIndex()!=-1 && mySizeSelection.getSelectedIndex()!=-1){
			g.drawImage(font.getStringImage("COST:", 2), 10, 10, null);
			int size = (int) Math.pow(mySizeSelection.getSelectedIndex()+1, mySizeSelection.getSelectedIndex()+1);
			g.drawImage(font.getStringImage(Integer.toString(size), 2), 80, 10, null);
			g.drawImage(font.getStringImage(OreData.getOreObject(myOreSelection.getSelectedIndex()).getMyName().toUpperCase(), 2),
					Integer.toString(size).length()*14 + 88, 10, null);
		}
		else{
			g.drawImage(font.getStringImage("SELECT A MATERIAL AND SIZE", 2), 10, 10, null);
		}

		g.drawImage(font.getStringImage("SIZES:", 1), 574, 7, null);
		g.drawImage(font.getStringImage("ORES:", 1), 718, 7, null);
		
		
		{ // old mini menu for temp testing purposes
			g.drawImage(font.getStringImage("OIL AVAILABLE:", 1), 10, 390, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getOil()), 1), 120, 390, null);
			g.drawImage(font.getStringImage("ORE AVAILABLE:", 1), 10, 410, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getOre(0)), 1), 120, 410, null);
			g.drawImage(font.getStringImage("GEMS AVAILABLE:", 1), 10, 430, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getGems()), 1), 132, 430, null);
		}
		
		drawComponents();
		return myImage;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
