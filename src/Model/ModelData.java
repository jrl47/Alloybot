package Model;

import java.util.HashMap;
import java.util.List;

import ModelComponents.ModelComponent;

public class ModelData {
	private HashMap<String, ScreenData> screens;
	private String state;
	private List<ModelComponent> myComponents;

	public List<ModelComponent> getComponents() {
		return myComponents;
	}
	public ScreenData getScreenData() {
		return screens.get(state);
	}
}
