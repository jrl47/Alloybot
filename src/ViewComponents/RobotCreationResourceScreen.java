package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ModelComponents.Robot;
import ModelComponents.RobotDestroyButton;
import View.AlloyFont;

public class RobotCreationResourceScreen extends ViewComponent{

	ResourceManager myManager;
	ModelMap myMap;
	AlloyBorderedButton[] myDestroyButtons;
	public RobotCreationResourceScreen(ResourceManager resourceManager, ModelMap m, int xx, int yy) {
		super(null, xx, yy, 800, 450);
		myManager = resourceManager;
		myMap = m;
	}

	@Override
	public BufferedImage loadImage() {
		BufferedImage image = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		AlloyFont font = null;
		try {
			font = new AlloyFont();
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(font.getStringImage("OIL AVAILABLE:", 2), 10, 10, null);
		g.drawImage(font.getStringImage(Integer.toString(myManager.getOil()), 2), 220, 10, null);
		g.drawImage(font.getStringImage("ORE AVAILABLE:", 2), 10, 50, null);
		g.drawImage(font.getStringImage(Integer.toString(myManager.getOre()), 2), 220, 50, null);
		g.drawImage(font.getStringImage("GEMS AVAILABLE:", 2), 10, 90, null);
		g.drawImage(font.getStringImage(Integer.toString(myManager.getGems()), 2), 232, 90, null);
		List<Robot> myRobots = myMap.getRobots();
		Robot[] robots = new Robot[myRobots.size()];
		for(int i=0; i<myRobots.size(); i++){
			robots[i] = myRobots.get(i);
		}
		Arrays.sort(robots);
		if(myDestroyButtons==null || myDestroyButtons.length!=robots.length){
			myDestroyButtons = new AlloyBorderedButton[robots.length];
			for(int i=0; i<robots.length; i++){
				try {
					myDestroyButtons[i] = new AlloyBorderedButton(null, 10, 10, "DESTROY", 1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		g.drawImage(font.getStringImage("ROBOT ROSTER:", 2), 10, 130, null);
		for(int i=0; i<robots.length; i++){
			g.drawImage(font.getStringImage("ROBOT " + i + " POWER:", 2), 10, 10 + (40 * (4 + i)), null);
			g.drawImage(font.getStringImage(Integer.toString(robots[i].getOreEfficiency()), 2), 216, 10 + (40 * (4 + i)), null);
			
		}
		drawComponents();
		return image;
	}

	@Override
	public BufferedImage loadHover() {
		// TODO Auto-generated method stub
		return loadImage();
	}

}
