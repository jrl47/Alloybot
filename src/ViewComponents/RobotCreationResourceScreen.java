package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ModelComponents.Robot;
import View.AlloyFont;

public class RobotCreationResourceScreen extends ViewComponent{

	private ResourceManager myManager;
	private ModelMap myMap;
	private AlloyBorderedButton[] myDestroyButtons;
	private int activeButtons;
	public RobotCreationResourceScreen(ResourceManager resourceManager, ModelMap m, int xx, int yy) {
		super(null, xx, yy, 800, 450);
		myManager = resourceManager;
		myMap = m;
		myDestroyButtons = new AlloyBorderedButton[10];
		for(int i=0; i<10; i++){
			try {
				myDestroyButtons[i] = new AlloyBorderedButton(null, 380, 10 + (40 * (4 + i)), "DESTROY", 2);
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
		g.drawImage(font.getStringImage("ROBOT ROSTER:", 2), 10, 130, null);
		for(int i=0; i<robots.length; i++){
			g.drawImage(font.getStringImage("ROBOT " + i + " POWER:", 2), 10, 10 + (40 * (4 + i)), null);
			g.drawImage(font.getStringImage(Integer.toString(robots[i].getOreEfficiency()), 2), 216, 10 + (40 * (4 + i)), null);
		}
		drawComponents();
		return myImage;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
