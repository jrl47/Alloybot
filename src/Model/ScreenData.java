package Model;

import java.util.ArrayList;
import java.util.List;

import ModelComponents.ModelComponent;
import ModelComponents.ResourceManager;

public class ScreenData {
	private String id;
	private List<ModelComponent> myComponents;
	private List<ModelComponent> myAuxiliaryComponents;
	public ScreenData(String i, List<ModelComponent> c){
		id = i;
		myComponents = c;
		myAuxiliaryComponents = new ArrayList<ModelComponent>();
	}
	public void addAuxiliaryComponent(ModelComponent m){
		myAuxiliaryComponents.add(m);
	}
	public String getID(){
		return id;
	}
	public List<ModelComponent> getComponents(){
		return myComponents;
	}
	public List<ModelComponent> getAuxiliaryComponents(){
		return myAuxiliaryComponents;
	}
}
