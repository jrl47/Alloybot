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
	private boolean loaded;
	private AlloyBorderedSelectionMenu myClass;
	private State mySelectedOre;
	public InventoryScreen(ModelMap m, int xx, int yy) {
		super(null, xx, yy);
		myMap = m;
		myManager = m.getResources();
		background = null;
		createClassMenu();
		myState = new State("Back");
		myState.setState("Back");
		mySelectedOre = new State("null");
		myState.setState("null");
		myOil = new StateChangeButton(myState, "Oil");
		myOre = new StateChangeButton(myState, "Ore");
		myGems = new StateChangeButton(myState, "Gems");
		myBack = new StateChangeButton(myState, "Back");
		for(int i=0; i<21; i++){
			
		}
		buildComponents();
	}

	private void buildComponents() {
		try{
			removeComponents();
			if(background==null)
				background = new Background(0,0,ImageIO.read(ScreenBuilder.class.getResource("/inventorybackground.png")));	
			addComponent(background);
			if(myState.getState().equals("Oil")){
				loadOilMenu();
			}
			if(myState.getState().equals("Ore")){
				loadOreMenu();
			}
			if(myState.getState().equals("Gems")){
				loadGemMenu();
			}
			if(myState.getState().equals("Back")){
				loadMainMenu();
			}
		}
		catch(IOException e) {e.printStackTrace();}
	}

	private void loadOilMenu() throws IOException {
		addComponent(new AlloyText("OIL:", 5, 10, 10));
		addComponent(new AlloyText(myManager.getOil() + "", 5, 175, 10));
		addComponent(new AlloyBorderedButton(myBack, 658, 400, "BACK TO INVENTORY", 1));
	}

	private void loadOreMenu() throws IOException {
		addComponent(new AlloyText("ORE:", 5, 10, 10));
		String s;
		int counter = 0;
		addComponent(myClass);
		if(myClass.getSelectedIndex()==-1){
			addComponent(new AlloyText("PLEASE SELECT AN ORE CLASS", 3, 190, 30));
		}
		if(myClass.getSelectedIndex()==0){
			for(int i=0; i<21; i++){
				int l = OreData.getOreObject(i).getMyName().length() + 6;
				addComponent(new AlloyBorderedButton(null, 190 + (i/7)*210, 30 + (20*counter), OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:" + myManager.getOre(i), 1));
				counter++;
				if(counter==7)
					counter=0;
			}
		}
		addComponent(new AlloyBorderedButton(myBack, 658, 400, "BACK TO INVENTORY", 1));
	}

	private void loadGemMenu() throws IOException {
		addComponent(new AlloyText("GEMS:", 5, 10, 10));
		addComponent(new AlloyText(myManager.getGems() + "", 5, 210, 10));
		addComponent(new AlloyBorderedButton(myBack, 658, 400, "BACK TO INVENTORY", 1));
	}

	private void loadMainMenu() throws IOException {
		addComponent(new AlloyBorderedButton(myOil, 180, 32, "OIL HOARDE", 6));
		addComponent(new AlloyBorderedButton(myOre, 130, 162, "INVENT-ORE-Y", 6));
		addComponent(new AlloyBorderedButton(myGems, 200, 292, "GEM CACHE", 6));
	}
	
	private void createClassMenu() {
		myClass = null;
		try {
			myClass = new AlloyBorderedSelectionMenu(15, 90, 2);
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
		if(!loaded){
			buildComponents();
			loaded = true;
		}
		if((myState.getPreviousState()!=null && !myState.getState().equals(myState.getPreviousState()))|| myClass.getPrevIndex()!=myClass.getSelectedIndex()){
			myState.setState(myState.getState());
			myClass.setIndex(myClass.getSelectedIndex());
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
