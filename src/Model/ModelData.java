package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ModelComponents.BasicMap;
import ModelComponents.MapMoveButton;
import ModelComponents.ModelComponent;
import ModelComponents.ResourceManager;
import ModelComponents.Robot;
import ModelComponents.RobotDeselectButton;
import ModelComponents.RobotEnableButton;
import ModelComponents.RobotMoveButton;
import ModelComponents.RobotStopButton;
import ModelComponents.StateChangeButton;

public class ModelData {
	public static final String START_MENU_STATE = "Start";
	public static final String GAME_OVER_STATE = "End";
	public static final String MAP_EXPLORATION_STATE = "Map";
	
	private HashMap<String, ScreenData> screens;
	private State state;
	private ResourceManager manager;
	private Set<ModelComponent> myComponents;
	
	public ModelData(){
		myComponents = new HashSet<ModelComponent>();
		manager = new ResourceManager();
		myComponents.add(manager);
		screens = new HashMap<String, ScreenData>();
		state = new State(START_MENU_STATE);
		loadStart();
		loadMap();
		loadEnd();
	}

	private void loadMap() {
		List<ModelComponent> mapComp = new ArrayList<ModelComponent>();
		mapComp.add(new StateChangeButton(state, GAME_OVER_STATE));
		BasicMap b = new BasicMap(manager);
		Robot r = new Robot();
		r.addButton(new RobotMoveButton(r));
		r.addButton(new RobotEnableButton(r));
		r.addButton(new RobotDeselectButton(r));
		r.addButton(new RobotStopButton(r));
		b.addRobot(r, 50, 50);
		r = new Robot();
		r.addButton(new RobotMoveButton(r));
		r.addButton(new RobotEnableButton(r));
		r.addButton(new RobotDeselectButton(r));
		r.addButton(new RobotStopButton(r));
		b.addRobot(r, 40, 50);
		myComponents.add(r);
		mapComp.add(b);
		mapComp.add(manager);
		myComponents.addAll(mapComp);
		ScreenData s = new ScreenData(MAP_EXPLORATION_STATE, mapComp);
		screens.put(MAP_EXPLORATION_STATE, s);
	}

	private void loadEnd() {
		screens.put(GAME_OVER_STATE, new ScreenData(GAME_OVER_STATE, null));
	}

	private void loadStart() {
		List<ModelComponent> startComp = new ArrayList<ModelComponent>();
		startComp.add(new StateChangeButton(state, MAP_EXPLORATION_STATE));
		myComponents.addAll(startComp);
		screens.put(START_MENU_STATE, new ScreenData(START_MENU_STATE, startComp));
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
