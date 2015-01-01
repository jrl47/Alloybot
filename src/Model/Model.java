package Model;

import Controller.InputListener;

public class Model {

	InputListener listener;
	State state;
	public Model(InputListener l) {
		listener = l;
	}
	public void step() {
		state.step();
	}
	public ViewData getViewData() {
		return state.getViewData();
	}

}
