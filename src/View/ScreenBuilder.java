package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Model.OreData;
import Model.ScreenData;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ViewComponents.AlloyBorderedButton;
import ViewComponents.AlloyBorderedSelectionMenu;
import ViewComponents.AlloyBorderedText;
import ViewComponents.AlloyText;
import ViewComponents.Background;
import ViewComponents.ResourcesInfoScreen;
import ViewComponents.RobotActionScreen;
import ViewComponents.RobotCreationResourceScreen;
import ViewComponents.TileInfoScreen;
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
			myComponents.add(new AlloyText(10, 10, "GAME OVER", 3));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildMap(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/mapbackground.png"))));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 698, 425, "END GAME", 1));
			myComponents.add(new ViewMap(myData.getComponents().get(1), 146, 0));
			myComponents.add(new TileInfoScreen(myData.getComponents().get(1), 660, 0));
			myComponents.add(new ResourcesInfoScreen((ResourceManager)(myData.getComponents().get(2)), 0, 0));
			RobotActionScreen r = new RobotActionScreen(myData.getComponents().get(1), 0, 300);
			myComponents.add(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildRobotMake(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/robotmakebackground.png"))));
			myComponents.add(new RobotCreationResourceScreen((ResourceManager) myData.getComponents().get(1),
					(ModelMap) myData.getComponents().get(2), 0, 0));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(3), 400, 10, "CREATE CLASS 1 ROBOT", 2));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(4), 400, 50, "CREATE CLASS 2 ROBOT", 2));
			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 698, 425, "BACK TO MAP", 1));
//			myComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 698, 425, "BACK TO MAP", 1));
			AlloyBorderedSelectionMenu oreMenu = new AlloyBorderedSelectionMenu(600, 125, 1);
			List<List<String>> menuStrings = new ArrayList<List<String>>();
			menuStrings.add(new ArrayList<String>());
			menuStrings.add(new ArrayList<String>());
			for(int i=0; i<5; i++){
				menuStrings.get(i/3).add(OreData.getOreObject(i).getMyName());
			}
			oreMenu.loadSelection(menuStrings);
			myComponents.add(oreMenu);
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
