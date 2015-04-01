package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Controller.Game;
import Model.Model;
import Model.OreData;
import Model.State;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ModelComponents.StateChangeButton;
import View.ScreenBuilder;

public class InventoryScreen extends ViewComponent{

	private Background background;
	private ModelMap myMap;
	private ResourceManager myManager;
	private State myState;
	private StateChangeButton myOil;
	private StateChangeButton myOre;
	private StateChangeButton myGems;
	private StateChangeButton myBack;
	private AlloyBorderedSelectionMenu myClass;
	public InventoryScreen(ModelMap m, int xx, int yy) {
		super(null, xx, yy);
		myMap = m;
		myManager = m.getResources();
		background = null;
		createClassMenu();
		myState = new State("Back");
		myOil = new StateChangeButton(myState, "Oil");
		myOre = new StateChangeButton(myState, "Ore");
		myGems = new StateChangeButton(myState, "Gems");
		myBack = new StateChangeButton(myState, "Back");
		buildComponents();
	}

	private void buildComponents() {
		try{
			removeComponents();
			
			if(background==null)
				background = new Background(0,0,ImageIO.read(ScreenBuilder.class.getResource("/inventorybackground.png")));	
			addComponent(background);
			
			
			if(myState.getState().equals("Oil")){
				addComponent(new AlloyText("OIL:", 5, 10, 10));
				addComponent(new AlloyText(myManager.getOil() + "", 5, 175, 10));
				addComponent(new AlloyBorderedButton(myBack, 658, 400, "BACK TO INVENTORY", 1));
			}
			if(myState.getState().equals("Ore")){
				addComponent(new AlloyText("ORE:", 5, 10, 10));
				String s;
				int counter = 0;
				addComponent(myClass);
				for(int i=0; i<21; i++){
					if(myManager.getOre(i)!=0){
						addComponent(new AlloyText(OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1, 10, 50 + (20*counter)));
						s = myManager.getOre(i) + "";
						addComponent(new AlloyText(s, 1, 110, 50 + (20*counter)));
						counter++;
					}
				}
				addComponent(new AlloyBorderedButton(myBack, 658, 400, "BACK TO INVENTORY", 1));
			}
			if(myState.getState().equals("Gems")){
				addComponent(new AlloyText("GEMS:", 5, 10, 10));
				addComponent(new AlloyText(myManager.getGems() + "", 5, 210, 10));
				addComponent(new AlloyBorderedButton(myBack, 658, 400, "BACK TO INVENTORY", 1));
			}
			if(myState.getState().equals("Back")){
				addComponent(new AlloyBorderedButton(myOil, 60, 60, "OIL", 7));
				addComponent(new AlloyBorderedButton(myOre, 280, 60, "ORE", 7));
				addComponent(new AlloyBorderedButton(myGems, 500, 60, "GEMS", 7));
			}
		}
		catch(IOException e) {e.printStackTrace();
		
		
		}
	}
	
	private void createClassMenu() {
		myClass = null;
		try {
			myClass = new AlloyBorderedSelectionMenu(570, 26, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<List<String>> menuStrings = new ArrayList<List<String>>();
		for(int i=0; i<1; i++){
			menuStrings.add(new ArrayList<String>());
		}
		menuStrings.get(0).add("Class 1");
		menuStrings.get(0).add("Class 2");
		menuStrings.get(0).add("Class 3");
		menuStrings.get(0).add("Class 4");
		menuStrings.get(0).add("Class 5");
		menuStrings.get(0).add("Class 6");
		menuStrings.get(0).add("Class 7");
		myClass.loadSelection(menuStrings);
		myComponents.add(myClass);
	}

	@Override
	public BufferedImage loadImage() {
		if((myState.getPreviousState()!=null && !myState.getState().equals(myState.getPreviousState())) || (!myState.getState().equals("Back") && (Game.ticks % Model.TICK_SCALAR )==0)){
			myState.setState(myState.getState());
			buildComponents();
		}
		myImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
		drawComponents();
		return myImage;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
