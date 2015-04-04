package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;

import Model.ScreenData;
import ModelComponents.ModelButton;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ModelComponents.RobotCreationButton;
import ModelComponents.StateChangeButton;
import ViewComponents.AlloyBorderedButton;
import ViewComponents.AlloyBorderedText;
import ViewComponents.AlloyText;
import ViewComponents.Background;
import ViewComponents.InformationPanelScreen;
import ViewComponents.InventoryScreen;
import ViewComponents.RobotCreationResourceScreen;
import ViewComponents.ViewComponent;
import ViewComponents.ViewMap;

public class ScreenBuilder extends ViewComponent{
	
	private ScreenData myData;
	public ScreenBuilder(ScreenData data, BufferedImage b) {
		super(null, 0, 0);
		myImage = b;
		myData = data;
		if(myData!=null){
		String myMeth = "build" + data.getID();
		
		// reflection stuff to call the right build method based on the ScreenData's ID
		Class cl = null;
		Method meth = null;
		cl = this.getClass();
		try {
			meth = cl.getDeclaredMethod(myMeth);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			meth.invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		}
		drawComponents();
		
	}
	public void buildStart(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/awesometitle.png"))));
			myComponents.add(new AlloyBorderedText(200,10, "ALLOYALITY", 5));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 240, 140, "START GAME", 4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildEnd(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/gameover.png"))));
			myComponents.add(new AlloyText("GAME OVER", 3, 10, 10));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildMap(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/mapbackground.png"))));
			myComponents.add(new ViewMap(myData.getComponents().get(1), 0, 0));
			myComponents.add(new InformationPanelScreen(myData.getComponents().get(1), (ModelMap)myData.getComponents().get(1), 658, 0));
			myComponents.add(new AlloyBorderedButton((ModelButton)(myData.getComponents().get(3)), 665, 425, "INVENTORY", 1));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 735, 425, "END GAME", 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildRobotMake(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/robotmakebackground.png"))));
			myComponents.add(new RobotCreationResourceScreen((ResourceManager) myData.getComponents().get(1),
					(ModelMap)(myData.getAuxiliaryComponents().get(0)),
					(RobotCreationButton) myData.getComponents().get(2), 0, 0));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 698, 425, "BACK TO MAP", 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildInventory(){
		try {
			myComponents.add(new InventoryScreen((ModelMap)(myData.getAuxiliaryComponents().get(0)), 0, 0));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 678, 425, "BACK TO MAP", 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildStats(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/gameover.png"))));
			myComponents.add(new AlloyText("GAME OVER", 3, 10, 10));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public BufferedImage loadImage() {
		return myImage;
	}
	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
}
