package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ModelComponents.BasicMap;
import ModelComponents.MapMoveButton;
import ModelComponents.ModelComponent;
import ModelComponents.StateChangeButton;

public class ModelData {
	public static final String START_MENU_STATE = "Start";
	public static final String GAME_OVER_STATE = "End";
	public static final String MAP_EXPLORATION_STATE = "Map";
	
	private HashMap<String, ScreenData> screens;
	private State state;
	private ResourceManager manager;
	private List<ModelComponent> myComponents;
	
	public ModelData(){
		myComponents = new ArrayList<ModelComponent>();
		manager = new ResourceManager();
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
		mapComp.add(b);
		myComponents.addAll(mapComp);
		ScreenData s = new ScreenData(MAP_EXPLORATION_STATE, mapComp);
		s.addResources(manager);
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

	public List<ModelComponent> getComponents() {
		return myComponents;
	}
	public ScreenData getScreenData() {
		ScreenData s = screens.get(state.getState());
		return s;
	}
	
	public String getState(){
		return state.getState();
	}
	public ResourceManager getResources(){
		return manager;
	}
}
