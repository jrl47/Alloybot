package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ModelComponents.ModelComponent;
import ModelComponents.StateChangeButton;

public class ModelData {
	public static final String START_MENU_STATE = "Start";
	public static final String GAME_OVER_STATE = "End";
	
	private HashMap<String, ScreenData> screens;
	private State state;
	private List<ModelComponent> myComponents;
	
	public ModelData(){
		state = new State(START_MENU_STATE);
		myComponents = new ArrayList<ModelComponent>();
		screens = new HashMap<String, ScreenData>();
		
		List<ModelComponent> startComp = new ArrayList<ModelComponent>();
		startComp.add(new StateChangeButton(state, GAME_OVER_STATE));
		myComponents.addAll(startComp);
		screens.put(START_MENU_STATE, new ScreenData(START_MENU_STATE, startComp));
		
		screens.put(GAME_OVER_STATE, new ScreenData(GAME_OVER_STATE, null));
	}

	public List<ModelComponent> getComponents() {
		return myComponents;
	}
	public ScreenData getScreenData() {
		return screens.get(state.getState());
	}
	
	public String getState(){
		return state.getState();
	}
}
