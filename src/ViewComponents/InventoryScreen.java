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
import ModelComponents.Ore;
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
	private Ore myOreStats;
	private List<StateChangeButton> myOreStateChanges;
	public InventoryScreen(ModelMap m, int xx, int yy) {
		super(null, xx, yy);
		myMap = m;
		myManager = m.getResources();
		background = null;
		createClassMenu();
		myState = new State("Back");
		myState.setState("Back");
		mySelectedOre = new State("null");
		mySelectedOre.setState("null");
		myOil = new StateChangeButton(myState, "Oil");
		myOre = new StateChangeButton(myState, "Ore");
		myGems = new StateChangeButton(myState, "Gems");
		myBack = new StateChangeButton(myState, "Back");
		myOreStateChanges = new ArrayList<StateChangeButton>();
		for(int i=0; i<21; i++){
			myOreStateChanges.add(new StateChangeButton(mySelectedOre, "" + i));
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
				addComponent(new AlloyBorderedButton(myOreStateChanges.get(i), 190 + (i/7)*210, 30 + (20*counter), OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:" + myManager.getOre(i), 1));
				counter++;
				if(counter==7)
					counter=0;
			}
		}
		if(mySelectedOre.getState().equals("null")){
			if(myClass.getSelectedIndex()!=-1)
				addComponent(new AlloyText("PLEASE SELECT AN ORE", 3, 190, 200));
		}
		else{
			Ore myOreStats = null;
			myOreStats = OreData.getOreObject(Integer.parseInt(mySelectedOre.getState()));
			addComponent(new AlloyText(myOreStats.getMyName().toUpperCase(), 3, 190, 200));
			
			addComponent(new AlloyText("OIL EFFICIENCY:", 1, 170, 270));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyOil()+1), 1, "OIL EFFICIENCY:".length()*7 + 177, 270));
			int length1 = "OIL EFFICIENCY:".length()*7 + 194 + Integer.toString(myOreStats.getMyOil()+1).length()*7 + 14;
			
			addComponent(new AlloyText("ORE EFFICIENCY:", 1, length1, 270));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyOre()+1), 1, "ORE EFFICIENCY:".length()*7 + 7 + length1, 270));
			int length2 = length1 + "ORE EFFICIENCY".length()*7 + Integer.toString(myOreStats.getMyOre()+1).length()*7 + 14 + 14 + 14;
			
			addComponent(new AlloyText("GEM EFFICIENCY:", 1, length2, 270));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyGems()+1), 1, "GEM EFFICIENCY:".length()*7 + 7 + length2, 270));
			
			
			addComponent(new AlloyText("DIVERSITY:", 1, 170, 290));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyDiversity()+1), 1, "DIVERSITY:".length()*7 + 177, 290));
			length1 = "DIVERSITY:".length()*7 + 194 + Integer.toString(myOreStats.getMyDiversity()+1).length()*7 + 14;
			
			addComponent(new AlloyText("DISTANCE:", 1, length1, 290));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyDistance()+1), 1, "DISTANCE:".length()*7 + 7 + length1, 290));
			length2 = length1 + "DISTANCE:".length()*7 + Integer.toString(myOreStats.getMyDistance()+1).length()*7 + 14 + 14 + 14;
			
			addComponent(new AlloyText("LUCK:", 1, length2, 290));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyLuck()+1), 1, "LUCK:".length()*7 + 7 + length2, 290));
			
		
			addComponent(new AlloyText("POWER:", 1, 170, 310));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyPower()+1), 1, "POWER:".length()*7 + 177, 310));
			length1 = "POWER:".length()*7 + 194 + Integer.toString(myOreStats.getMyPower()+1).length()*7 + 14;
			
			addComponent(new AlloyText("DURABILITY:", 1, length1, 310));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyDurability()+1), 1, "DURABILITY:".length()*7 + 7 + length1, 310));
			length2 = length1 + "DURABILITY:".length()*7 + Integer.toString(myOreStats.getMyDurability()+1).length()*7 + 14 + 14 + 14;
			
			addComponent(new AlloyText("MAGIC:", 1, length2, 310));
			addComponent(new AlloyText(Integer.toString(myOreStats.getMyMagic()+1), 1, "MAGIC:".length()*7 + 7 + length2, 310));
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
		if((myState.getPreviousState()!=null && !myState.getState().equals(myState.getPreviousState())) || 
				myClass.getPrevIndex()!=myClass.getSelectedIndex() ||
				(mySelectedOre.getPreviousState()!=null && !mySelectedOre.getState().equals(mySelectedOre.getPreviousState()))){
			myState.setState(myState.getState());
			myClass.setIndex(myClass.getSelectedIndex());
			mySelectedOre.setState(mySelectedOre.getState());
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
