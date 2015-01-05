package Model;

import java.util.List;

import ModelComponents.ModelComponent;

public class ScreenData {
	private String id;
	private List<ModelComponent> myComponents;
	public ScreenData(String i, List<ModelComponent> c){
		id = i;
		myComponents = c;
	}
	public String getID(){
		return id;
	}
	public List<ModelComponent> getComponents(){
		return myComponents;
	}
}