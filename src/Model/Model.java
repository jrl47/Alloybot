package Model;

import java.util.HashMap;
import java.util.List;

import Controller.InputListener;
import ModelComponents.ModelComponent;

public class Model {
	
	private ModelData data;
	public Model() {
		data = new ModelData();
	}
	public void step() {
		for(ModelComponent m: data.getComponents()){
			m.step();
		}
	}
	public ScreenData getScreenData(){
		return data.getScreenData();
	}
}
