package Model;

import java.util.HashMap;
import java.util.List;

import Controller.InputListener;
import ModelComponents.ModelComponent;

public class Model {

	private InputListener listener;
	private HashMap<String, List<ModelComponent>> states;
	private String state;
	private List<ModelComponent> myComponents;
	public Model(InputListener l) {
		listener = l;
	}
	public void step() {
		for(ModelComponent m: myComponents){
			m.step();
		}
	}
	public List<ModelComponent> getScreenState() {
		return states.get(state);
	}

}
