package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.Game;
import Model.Model;
import Model.OreData;
import ModelComponents.ModelButton;
import ModelComponents.ResourceManager;
import View.ScreenBuilder;

public class ResourcesInfoScreen extends ViewComponent{
	private Background background;
	private ResourceManager myManager;
	private ModelButton myInventoryButton;
	public ResourcesInfoScreen(ResourceManager resourceManager, ModelButton inventoryButton, int xx, int yy){
		super(null, xx, yy, 100, 300);
		myInventoryButton = inventoryButton;
		myManager = resourceManager;
		myImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);

		buildComponents();
	}
	private void buildComponents() {
		removeComponents();
		myImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
		try {
			if(background==null){
				background = new Background(0,0,ImageIO.read(ScreenBuilder.class.getResource("/resourcedatabackground.png")));	
		}
		addComponent(background);
		addComponent(new AlloyBorderedButton(myInventoryButton, 10, 8, "INVENTORY", 1));
		addComponent(new AlloyText("OIL:", 1, 10, 30));
		String s = myManager.getOil() + "";
		addComponent(new AlloyText(s, 1, 40, 30));
		int counter = 0;
		for(int i=0; i<5; i++){
			if(myManager.getOre(i)!=0){
				addComponent(new AlloyText(OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1, 10, 50 + (20*counter)));
				s = myManager.getOre(i) + "";
				addComponent(new AlloyText(s, 1, 110, 50 + (20*counter)));
				counter++;
			}
		}
		addComponent(new AlloyText("GEMS:", 1, 10, 50 + (20*counter)));
		s = myManager.getGems() + "";
		addComponent(new AlloyText(s, 1, 46, 50 + (20*counter)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public BufferedImage loadImage() {
		if((Game.ticks % Model.TICK_SCALAR )==0)
			buildComponents();
		drawComponents();
		return myImage;
	}
	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
}
