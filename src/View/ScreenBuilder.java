package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
	private List<ViewComponent> myStartComponents;
	private List<ViewComponent> myEndComponents;
	private List<ViewComponent> myMapComponents;
	private List<ViewComponent> myRobotMakeComponents;
	private List<ViewComponent> myInventoryComponents;
	public ScreenBuilder() {
		super(null, 0, 0);
	}
	
	public void initialize(ScreenData data, BufferedImage b){
		removeComponents();
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
		if(myStartComponents==null){
			try {
				myStartComponents = new ArrayList<ViewComponent>();
				myStartComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/awesometitle.png"))));
				myStartComponents.add(new AlloyBorderedText(200,10, "ALLOYALITY", 5));
				myStartComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 240, 140, "START GAME", 4));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(ViewComponent v: myStartComponents){
			addComponent(v);
//			myComponents.add(v);
		}
	}
	public void buildEnd(){
		if(myEndComponents==null){
			try {
				myEndComponents = new ArrayList<ViewComponent>();
				myEndComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/gameover.png"))));
				myEndComponents.add(new AlloyText("GAME OVER", 3, 10, 10));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(ViewComponent v: myEndComponents){
			addComponent(v);
//			myComponents.add(v);
		}
	}
	public void buildMap(){
		if(myMapComponents==null){
			try {
				myMapComponents = new ArrayList<ViewComponent>();
				myMapComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/mapbackground.png"))));
				myMapComponents.add(new ViewMap(myData.getComponents().get(1), 0, 0));
				myMapComponents.add(new InformationPanelScreen(myData.getComponents().get(1), (ModelMap)myData.getComponents().get(1), 658, 0));
				myMapComponents.add(new AlloyBorderedButton((ModelButton)(myData.getComponents().get(3)), 665, 425, "INVENTORY", 1));
				myMapComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 735, 425, "END GAME", 1));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(ViewComponent v: myMapComponents){
			addComponent(v);
//			myComponents.add(v);
		}
	}
	public void buildRobotMake(){
		if(myRobotMakeComponents==null){
			try {
				myRobotMakeComponents = new ArrayList<ViewComponent>();
				myRobotMakeComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/robotmakebackground.png"))));
				myRobotMakeComponents.add(new RobotCreationResourceScreen((ResourceManager) myData.getComponents().get(1),
						(ModelMap)(myData.getAuxiliaryComponents().get(0)),
						(RobotCreationButton) myData.getComponents().get(2), 0, 0));
				myRobotMakeComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 698, 425, "BACK TO MAP", 1));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(ViewComponent v: myRobotMakeComponents){
			addComponent(v);
//			myComponents.add(v);
		}
	}
	public void buildInventory(){
		if(myInventoryComponents==null){
			try {
				myInventoryComponents = new ArrayList<ViewComponent>();
				myInventoryComponents.add(new InventoryScreen((ModelMap)(myData.getAuxiliaryComponents().get(0)), 0, 0));
				myInventoryComponents.add(new AlloyBorderedButton(myData.getComponents().get(0), 678, 425, "BACK TO MAP", 1));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		((InventoryScreen)myInventoryComponents.get(0)).reset();
		for(ViewComponent v: myInventoryComponents){
			addComponent(v);
//			myComponents.add(v);
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
