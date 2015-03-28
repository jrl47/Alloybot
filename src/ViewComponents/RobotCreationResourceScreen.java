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
	private AlloyBorderedButton[] myDestroyButtons;
	private int activeButtons;
	public RobotCreationResourceScreen(ResourceManager resourceManager, ModelMap m, int xx, int yy) {
		super(null, xx, yy, 800, 450);
		myManager = resourceManager;
		myMap = m;
		AlloyBorderedSelectionMenu oreMenu = null;
		try {
			oreMenu = new AlloyBorderedSelectionMenu(714, 15, 1);
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
		oreMenu.loadSelection(menuStrings);
		myOreSelection = oreMenu;
		myComponents.add(oreMenu);
		myDestroyButtons = new AlloyBorderedButton[10];
		for(int i=0; i<10; i++){
			try {
				myDestroyButtons[i] = new AlloyBorderedButton(null, 160, 10 + (20 * (19 + i)), "DESTROY", 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		g.drawImage(font.getStringImage("SELECTED ORE:", 2), 10, 10, null);
		if(myOreSelection.getSelectedIndex()!=-1)
			g.drawImage(font.getStringImage(OreData.getOreObject(myOreSelection.getSelectedIndex()).getMyName().toUpperCase(), 2), 210, 10, null);
		
		
		
		{ // old mini menu for temp testing purposes
			g.drawImage(font.getStringImage("OIL AVAILABLE:", 1), 10, 310, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getOil()), 1), 120, 310, null);
			g.drawImage(font.getStringImage("ORE AVAILABLE:", 1), 10, 330, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getOre(0)), 1), 120, 330, null);
			g.drawImage(font.getStringImage("GEMS AVAILABLE:", 1), 10, 350, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getGems()), 1), 132, 350, null);
			List<Robot> myRobots = myMap.getRobots();
			Robot[] robots = new Robot[myRobots.size()];
			for(int i=0; i<myRobots.size(); i++){
				robots[i] = myRobots.get(i);
			}
			Arrays.sort(robots);
			if(activeButtons!=robots.length){
				for(int i=0; i<activeButtons; i++){
					removeComponent(myDestroyButtons[i]);
				}
				for(int i=0; i<robots.length; i++){
					myDestroyButtons[i].setComponent(robots[i].getButtons().get(4));
					addComponent(myDestroyButtons[i]);
				}
				activeButtons = robots.length;
			}
			g.drawImage(font.getStringImage("ROBOT ROSTER:", 1), 10, 370, null);
			for(int i=0; i<robots.length; i++){
				g.drawImage(font.getStringImage("ROBOT " + i + " POWER:", 1), 10, 10 + (20 * (19 + i)), null);
				g.drawImage(font.getStringImage(Integer.toString(robots[i].getOreEfficiency()), 1), 110, 10 + (20 * (19 + i)), null);
			}
		}
		
		drawComponents();
		return myImage;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
