package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ModelComponents.BasicMap;
import ModelComponents.InventoryButton;
import ModelComponents.MapMoveButton;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ModelComponents.Robot;
import ModelComponents.RobotCreationButton;
import ModelComponents.RobotDeselectButton;
import ModelComponents.RobotEnableButton;
import ModelComponents.RobotFactory;
import ModelComponents.RobotMoveButton;
import ModelComponents.RobotStopButton;
import ModelComponents.SmeltButton;
import ModelComponents.StateChangeButton;

public class ModelData {
	public static final String START_MENU_STATE = "Start";
	public static final String GAME_OVER_STATE = "End";
	public static final String MAP_EXPLORATION_STATE = "Map";
	public static final String FACTORY_STATE = "Factory";
	public static final String ROBOT_CREATION_STATE = "RobotMake";
	public static final String INVENTORY_STATE = "Inventory";
	public static final String SMELT_STATE = "Smelt";
	
	private HashMap<String, ScreenData> screens;
	private State state;
	private ResourceManager manager;
	private Set<ModelComponent> myComponents;
	private ModelMap myMap;
	
	public ModelData(){
		myComponents = new HashSet<ModelComponent>();
		manager = new ResourceManager();
		myComponents.add(manager);
		screens = new HashMap<String, ScreenData>();
		state = new State(START_MENU_STATE);
		OreData.init();
		makeModelMap();
		loadStart();
		loadMap();
		loadEnd();
		loadFactory();
		loadRobotMake();
		loadInventory();
		loadSmelt();
	}

	private void makeModelMap() {
		BasicMap b = new BasicMap(manager, state);
		myMap = b;
		Robot r = new Robot(0, 1, myMap);
		b.addObject(r, 85, 85);
		RobotFactory rf = new RobotFactory(state);
		b.addObject(rf, 87, 87);
	}

	private void loadMap() {
		List<ModelComponent> mapComp = new ArrayList<ModelComponent>();
		mapComp.add(new StateChangeButton(state, GAME_OVER_STATE));
		mapComp.add(myMap);
		mapComp.add(manager);
		mapComp.add(new InventoryButton(state));
		myComponents.addAll(mapComp);
		ScreenData s = new ScreenData(MAP_EXPLORATION_STATE, mapComp);
		screens.put(MAP_EXPLORATION_STATE, s);
	}

	private void loadEnd() {
		screens.put(GAME_OVER_STATE, new ScreenData(GAME_OVER_STATE, null));
	}
	
	private void loadRobotMake() {
		List<ModelComponent> makeComp = new ArrayList<ModelComponent>();
		makeComp.add(new StateChangeButton(state, MAP_EXPLORATION_STATE));
		makeComp.add(manager);
		makeComp.add(new RobotCreationButton(myMap));
		myComponents.addAll(makeComp);
		ScreenData s = new ScreenData(ROBOT_CREATION_STATE, makeComp);
		s.addAuxiliaryComponent(myMap);
		screens.put(ROBOT_CREATION_STATE, s);
	}
	
	private void loadInventory() {
		List<ModelComponent> invComp = new ArrayList<ModelComponent>();
		invComp.add(new StateChangeButton(state, MAP_EXPLORATION_STATE));
		invComp.add(manager);
		myComponents.addAll(invComp);
		ScreenData s = new ScreenData(INVENTORY_STATE, invComp);
		s.addAuxiliaryComponent(myMap);
		screens.put(INVENTORY_STATE, s);
	}
	
	private void loadSmelt() {
		List<ModelComponent> smeltComp = new ArrayList<ModelComponent>();
		smeltComp.add(new StateChangeButton(state, MAP_EXPLORATION_STATE));
		smeltComp.add(manager);
		smeltComp.add(new SmeltButton(myMap));
		myComponents.addAll(smeltComp);
		ScreenData s = new ScreenData(SMELT_STATE, smeltComp);
		s.addAuxiliaryComponent(myMap);
		screens.put(SMELT_STATE, s);
	}

	private void loadStart() {
		List<ModelComponent> startComp = new ArrayList<ModelComponent>();
		startComp.add(new StateChangeButton(state, MAP_EXPLORATION_STATE));
		myComponents.addAll(startComp);
		screens.put(START_MENU_STATE, new ScreenData(START_MENU_STATE, startComp));
	}
	
	private void loadFactory() {
		List<ModelComponent> factoryComp = new ArrayList<ModelComponent>();
		factoryComp.add(new StateChangeButton(state, ROBOT_CREATION_STATE));
		factoryComp.add(new StateChangeButton(state, SMELT_STATE));
		factoryComp.add(new StateChangeButton(state, MAP_EXPLORATION_STATE));
		myComponents.addAll(factoryComp);
		screens.put(FACTORY_STATE, new ScreenData(FACTORY_STATE, factoryComp));
	}

	public Set<ModelComponent> getComponents() {
		return myComponents;
	}
	public ScreenData getScreenData() {
		ScreenData s = screens.get(state.getState());
		return s;
	}
	
	public String getState(){
		return state.getState();
	}
}
