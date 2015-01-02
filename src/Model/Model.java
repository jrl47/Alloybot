package Model;

import java.util.HashMap;
import java.util.List;

import Controller.InputListener;
import ModelComponents.ModelComponent;

public class Model {

	private InputListener listener;
	private ModelData data;
	public Model(InputListener l) {
		listener = l;
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
