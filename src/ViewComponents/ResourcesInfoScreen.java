package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Model.OreData;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import View.AlloyFont;
import View.ScreenBuilder;

public class ResourcesInfoScreen extends ViewComponent{
	private BufferedImage myBackground;
	private ResourceManager myManager;
	private boolean componentsLoaded;
	public ResourcesInfoScreen(ResourceManager resourceManager, int xx, int yy){
		super(null, xx, yy, 100, 300);
		myManager = resourceManager;
	}
	@Override
	public BufferedImage loadImage() {
		if(myBackground==null){
		try {
			myBackground = ImageIO.read(ScreenBuilder.class.getResource("/resourcedatabackground.png"));
			BufferedImage bb = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bb.getGraphics();
			g.drawImage(myBackground, 0, 0, null);
			myBackground = bb;
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		myImage = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = myImage.getGraphics();
		g.drawImage(myBackground, 0, 0, null);
		AlloyFont font = null;
		try {
			font = new AlloyFont();
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(font.getStringImage("INVENTORY", 1), 10, 10, null);
		g.drawImage(font.getStringImage("OIL:", 1), 10, 30, null);
		String s = myManager.getOil() + "";
		g.drawImage(font.getStringImage(s, 1), 40, 30, null);
		int counter = 0;
		for(int i=0; i<5; i++){
			if(myManager.getOre(i)!=0){
				g.drawImage(font.getStringImage(OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1), 10, 50 + (20*counter), null);
				s = myManager.getOre(i) + "";
				g.drawImage(font.getStringImage(s, 1), 110, 50 + (20*counter), null);
				counter++;
			}
		}
//		g.drawImage(font.getStringImage("ORE:", 1), 10, 50, null);
//		s = myManager.getOre(0) + "";
//		g.drawImage(font.getStringImage(s, 1), 40, 50, null);
		g.drawImage(font.getStringImage("GEMS:", 1), 10, 50 + (20*counter), null);
		s = myManager.getGems() + "";
		g.drawImage(font.getStringImage(s, 1), 46, 50 + (20*counter), null);
		return myImage;
	}
	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
}
