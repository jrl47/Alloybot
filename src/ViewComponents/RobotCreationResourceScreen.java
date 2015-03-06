package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ModelComponents.ResourceManager;
import View.AlloyFont;

public class RobotCreationResourceScreen extends ViewComponent{

	ResourceManager myManager;
	public RobotCreationResourceScreen(ResourceManager resourceManager, int xx, int yy) {
		super(null, xx, yy, 800, 450);
		myManager = resourceManager;
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
		return image;
	}

	@Override
	public BufferedImage loadHover() {
		// TODO Auto-generated method stub
		return loadImage();
	}

}
