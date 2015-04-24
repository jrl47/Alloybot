package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import Controller.Game;
import Model.OreData;
import Model.State;
import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import ModelComponents.Ore;
import ModelComponents.ResourceManager;
import ModelComponents.Robot;
import ModelComponents.StateChangeButton;
import View.ScreenBuilder;

public class InformationPanelScreen extends ViewComponent{
	
	private BufferedImage myBackground;
	private Background background;
	private ModelMap myMap;
	private ResourceManager myResources;
	private AlloyBorderedButton myMoveButton;
	private AlloyBorderedButton myMineButton;
	private AlloyBorderedButton myDeselectButton;
	private AlloyBorderedButton myStopButton;
	private AlloyBorderedButton myDestroyButton;
	private AlloyBorderedButton myStatsButton;
	private AlloyBorderedButton myBuildButton;
	private AlloyBorderedButton myBackButton;
	private boolean needsRobotButtons;
	private boolean needsBuildButtons;
	private boolean needsBack;
	private boolean needsStats;
	private AlloyBorderedButton myFactoryCreationButton;
	private List<ViewComponent> myTileComponents;
	private List<ViewComponent> myRobotComponents;
	private List<ViewComponent> myGainComponents;
	private List<ViewComponent> myStatComponents;
	private List<ViewComponent> myBuildComponents;
	private Map<String, AlloyText> myCommonWords;
	private State myState;
	public InformationPanelScreen(ModelComponent c, ModelMap m, int xx, int yy){
		super(c, xx, yy, 100, 300);
		myMap = m;
		myResources = myMap.getResources();
		needsRobotButtons = true;
		needsBack = true;
		needsStats = true;
		myMoveButton = null;
		myMineButton = null;
		myDeselectButton = null;
		myStopButton = null;
		myDestroyButton = null;
		myStatsButton = null;
		myStatsButton = null;
		createBackground();
		myTileComponents = new ArrayList<ViewComponent>();
		myRobotComponents = new ArrayList<ViewComponent>();
		myGainComponents = new ArrayList<ViewComponent>();
		myStatComponents = new ArrayList<ViewComponent>();
		myBuildComponents = new ArrayList<ViewComponent>();
		myCommonWords = new HashMap<String, AlloyText>();
		myState = new State("main");
		try {
			myMoveButton = new AlloyBorderedButton(null, 10, 50, "MOVE", 1);
			myMineButton = new AlloyBorderedButton(null, 10, 70, "MINE", 1);
			myDeselectButton = new AlloyBorderedButton(null, 10, 90, "DESELECT", 1);
			myStopButton = new AlloyBorderedButton(null, 10, 110, "STOP", 1);
			myDestroyButton = new AlloyBorderedButton(null, 10, 130, "DESTROY", 1);
			myStatsButton = new AlloyBorderedButton(new StateChangeButton(myState, "stats"), 10, 150, "STATS", 1);
			myBuildButton = new AlloyBorderedButton(new StateChangeButton(myState, "build"), 10, 170, "BUILD", 1);
			
			myFactoryCreationButton = new AlloyBorderedButton(null, 10, 10, "CREATE FACTORY", 1);
			myBackButton = new AlloyBorderedButton(new StateChangeButton(myState, "main"), 7, 405, "BACK", 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage loadImage() {
		myImage = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
		try{
			if(myState.getState().equals("main")){
				for(ViewComponent v: myStatComponents){
					removeComponent(v);
				}
				removeComponent(myBackButton);
				needsBack = true;
				needsStats = true;
				if(!hasRobotAction()){
				if(Game.ticks % 11 == 0)
					createTileInfo();
				createGainInfo();
				}
				else{
				createRobotActionInfo();
				}
			}
			else if(myState.getState().equals("stats")){
				needsRobotButtons = true;
				needsBuildButtons = true;
				createRobotStats();
			}
			else{
				needsRobotButtons = true;
				createBuildOptions();
			}
		} catch(IOException e) {e.printStackTrace();}
		drawComponents();
		return myImage;
	}

	private void createBuildOptions() throws IOException {
		for(ViewComponent v: myTileComponents){
			removeComponent(v);
		}
		for(ViewComponent v: myRobotComponents){
			removeComponent(v);
		}
		for(ViewComponent v: myGainComponents){
			removeComponent(v);
		}
		if(needsBack){
			addComponent(myBackButton);
			needsBack = false;
		}
		Robot r;
		if(myMap.getSelectedObject()!=null){
			if(needsBuildButtons){
				if(myMap.getSelectedObject() instanceof Robot){
					r = (Robot)myMap.getSelectedObject();
					
					myFactoryCreationButton.setComponent(null); //to be factory creation model button
					addComponent(myFactoryCreationButton);
					myBuildComponents.add(myFactoryCreationButton);

					AlloyText a = new AlloyText("COSTS 1 GEM", 1, 10, 32);
					addComponent(a);
					myBuildComponents.add(a);

					needsBuildButtons = false;
				}
			}
		}
		else{
			for(ViewComponent v: myBuildComponents){
				removeComponent(v);
			}
			needsBuildButtons = true;
		}
	}

	private void createGainInfo() throws IOException {
		for(ViewComponent v: myGainComponents){
			removeComponent(v);
		}
		AlloyText a = null;
		if(myResources.getOilDif()!=0){
			if(myResources.getOilDif()>0)
				a = new AlloyText("GOT " + myResources.getOilDif() + " OIL", 1, 10, 298);
			if(myResources.getOilDif()<0)
				a = new AlloyText("LOST " + -1*myResources.getOilDif() + " OIL", 1, 10, 298);
			myGainComponents.add(a);
			addComponent(a);
		}
		int counter = 0;
		Map<Integer, Integer> map = myResources.getOreDif();
		for(Integer i: myResources.getOreDif().keySet()){
			Ore o = OreData.getOreObject(i);
			if(map.get(i)>0){
				a = new AlloyText("GOT " + map.get(i), 1, 10, 318 + 20*counter);
				myGainComponents.add(a);
				addComponent(a);
				counter++;
				a = new AlloyText(o.getMyName().toUpperCase() + " ORE", 1, 10, 318 + 20*counter);
				myGainComponents.add(a);
				addComponent(a);
				counter++;
			}
			if(map.get(i)<0){
				a = new AlloyText("LOST " + -1*map.get(i), 1, 10, 318 + 20*counter);
				myGainComponents.add(a);
				addComponent(a);
				counter++;
				a = new AlloyText(o.getMyName().toUpperCase() + " ORE", 1, 10, 318 + 20*counter);
				myGainComponents.add(a);
				addComponent(a);
				counter++;
			}
			
			counter++;
		}
		if(myResources.getGemDif()!=0){
			if(myResources.getGemDif()>0)
				a = new AlloyText("GOT " + myResources.getGemDif() + " GEMS", 1, 10, 318 + 20*counter);
			if(myResources.getGemDif()<0)
				a = new AlloyText("LOST " + -1*myResources.getGemDif() + " GEMS", 1, 10, 318 + 20*counter);
			myGainComponents.add(a);
			addComponent(a);
		}
	}

	private void createRobotStats() throws IOException {
		for(ViewComponent v: myTileComponents){
			removeComponent(v);
		}
		for(ViewComponent v: myRobotComponents){
			removeComponent(v);
		}
		for(ViewComponent v: myGainComponents){
			removeComponent(v);
		}
		if(needsBack){
			addComponent(myBackButton);
			needsBack = false;
		}
		
		Robot r;
		if(myMap.getSelectedObject()!=null){
			if(needsStats){
				if(myMap.getSelectedObject() instanceof Robot){
					r = (Robot)myMap.getSelectedObject();

					AlloyText a = new AlloyText(r.getName().toUpperCase() + " ROBOT", 1, 10, 10);
					AlloyText b = new AlloyText("SIZE " + r.getSize(), 1, 10, 30);
					addComponent(a);
					addComponent(b);
					myStatComponents.add(a);
					myStatComponents.add(b);
					
					Ore ore = r.getOre();
					int size = r.getSize();
					
					a = new AlloyText("OIL EFFICIENCY:", 1, 10, 50);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyOil(), size)), 1, 10, 70);
					myStatComponents.add(a);
					addComponent(a);
					
					a = new AlloyText("ORE EFFICIENCY:", 1, 10, 90);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyOre(), size)), 1, 10, 110);
					myStatComponents.add(a);
					addComponent(a);
					
					a = new AlloyText("GEM EFFICIENCY:", 1, 10, 130);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyGems(), size)), 1, 10, 150);
					myStatComponents.add(a);
					addComponent(a);
					
					
					a = new AlloyText("DIVERSITY:", 1, 10, 170);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyDiversity(), size)), 1, 10, 190);
					myStatComponents.add(a);
					addComponent(a);
					
					a = new AlloyText("DISTANCE:", 1, 10, 210);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyDistance(), size)), 1, 10, 230);
					myStatComponents.add(a);
					addComponent(a);
					
					a = new AlloyText("LUCK:", 1, 10, 250);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyLuck(), size)), 1, 10, 270);
					myStatComponents.add(a);
					addComponent(a);
					
				
					a = new AlloyText("POWER:", 1, 10, 290);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyPower(), size)), 1, 10, 310);
					myStatComponents.add(a);
					addComponent(a);
					
					a = new AlloyText("DURABILITY:", 1, 10, 330);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyDurability(), size)), 1, 10, 350);
					myStatComponents.add(a);
					addComponent(a);
					
					a = new AlloyText("MAGIC:", 1, 10, 370);
					myStatComponents.add(a);
					addComponent(a);
					a = new AlloyText(Integer.toString(processStat(ore.getMyMagic(), size)), 1, 10, 390);
					myStatComponents.add(a);
					addComponent(a);
					
					needsStats = false;
				}
			}
		}
		else{
			for(ViewComponent v: myStatComponents){
				removeComponent(v);
			}
			myState.setState("main");
			needsStats = true;
		}
	}

	public int processStat(int base, int size){
		int std = (int)Math.pow(2, base);
		int actualStat = (int) (Math.pow(size, size)*std*size);
		return actualStat;
	}

	private boolean hasRobotAction(){
		Robot r;
		if(myMap.getSelectedObject()!=null){
			if(myMap.getSelectedObject() instanceof Robot)
				return true;
		}
		return false;
	}
	private void createRobotActionInfo() throws IOException {
		for(ViewComponent v: myTileComponents){
			removeComponent(v);
		}
		for(ViewComponent v: myGainComponents){
			removeComponent(v);
		}
		for(ViewComponent v: myBuildComponents){
			removeComponent(v);
		}
		needsBuildButtons = true;
		Robot r;
		if(myMap.getSelectedObject()!=null){
			if(needsRobotButtons){
				if(myMap.getSelectedObject() instanceof Robot){
					r = (Robot)myMap.getSelectedObject();

					AlloyText a = new AlloyText(r.getName().toUpperCase() + " ROBOT", 1, 10, 10);
					AlloyText b = new AlloyText("SIZE " + r.getSize(), 1, 10, 30);
					addComponent(a);
					addComponent(b);
					myRobotComponents.add(a);
					myRobotComponents.add(b);
					myMoveButton.setComponent(r.getButtons().get(0));
					addComponent(myMoveButton);
					myRobotComponents.add(myMoveButton);
					myMineButton.setComponent(r.getButtons().get(1));
					addComponent(myMineButton);
					myRobotComponents.add(myMineButton);
					myDeselectButton.setComponent(r.getButtons().get(2));
					addComponent(myDeselectButton);
					myRobotComponents.add(myDeselectButton);
					myStopButton.setComponent(r.getButtons().get(3));
					addComponent(myStopButton);
					myRobotComponents.add(myStopButton);
					myDestroyButton.setComponent(r.getButtons().get(4));
					addComponent(myDestroyButton);
					myRobotComponents.add(myDestroyButton);

					addComponent(myStatsButton);
					myRobotComponents.add(myStatsButton);
					addComponent(myBuildButton);
					myRobotComponents.add(myBuildButton);
					needsRobotButtons = false;
				}
			}
		}
		else{
			for(ViewComponent v: myRobotComponents){
				removeComponent(v);
			}
			needsRobotButtons = true;
		}
	}

	private void createTileInfo() throws IOException {
		for(ViewComponent v: myRobotComponents){
			removeComponent(v);
		}
		needsRobotButtons = true;
		for(ViewComponent v: myBuildComponents){
			removeComponent(v);
		}
		needsBuildButtons = true;
		for(ViewComponent v: myTileComponents){
			removeComponent(v);
		}

		MapCell currentCell = null;
		if(((ModelMap)myComponent).getCurrentHighlightedCell()!=null){
			currentCell = ((ModelMap)myComponent).getCurrentHighlightedCell();
		}
		else if(((ModelMap)myComponent).getSelectedCell()!=null){
			currentCell = ((ModelMap)myComponent).getSelectedCell();
		}
		if(currentCell!=null){
			if(!myCommonWords.containsKey("TILE RESOURCES")){
				myCommonWords.put("TILE RESOURCES", new AlloyText("TILE RESOURCES", 1, 10, 10));
			}
			if(!myCommonWords.containsKey("OIL:")){
				myCommonWords.put("OIL:", new AlloyText("OIL:", 1, 10, 30));
			}
			
			AlloyText a = myCommonWords.get("TILE RESOURCES");
			myTileComponents.add(a);
			addComponent(a);
			a = myCommonWords.get("OIL:");
			myTileComponents.add(a);
			addComponent(a);
			String s = currentCell.getOil() + "";
			a = new AlloyText(s, 1, 40, 30);
			myTileComponents.add(a);
			addComponent(a);
			int counter = 0;
			for(int i=0; i<21; i++){
				if(currentCell.getOre(i)!=0){
					a = new AlloyText(OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1, 10, 50 + (20*counter));
					myTileComponents.add(a);
					addComponent(a);
					s = currentCell.getOre(i) + "";
					a = new AlloyText(s + "%", 1, 110, 50 + (20*counter));
					myTileComponents.add(a);
					addComponent(a);
					counter++;
				}
			}
			if(currentCell.getGems()!=0){
				a = new AlloyText("GEMS:", 1, 10, 50 + (20*counter));
				myTileComponents.add(a);
				addComponent(a);
				s = currentCell.getGems() + "";
				a = new AlloyText(s + "%", 1, 110, 50 + (20*counter));
				myTileComponents.add(a);
				addComponent(a);
				counter++;
			}
		}
		else{
			AlloyText a = new AlloyText("NO TILE SELECTED", 1, 10, 30);
			myTileComponents.add(a);
			addComponent(a);
		}
	}

	private void createBackground() {
		if(background==null){
		try {
			myBackground = ImageIO.read(ScreenBuilder.class.getResource("/informationpanelbackground.png"));
			background = new Background(0,0,myBackground);
		} catch (IOException e) {e.printStackTrace();}
		}
		addComponent(background);
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
	
	
	
}