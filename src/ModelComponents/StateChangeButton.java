package ModelComponents;

import Model.State;

public class StateChangeButton extends ModelButton{

	private State myOldState;
	private String myNewState;
	public StateChangeButton(State oldState, String newState) {
		super();
		myNewState = newState;
		myOldState = oldState;
	}

	@Override
	public void respond() {
		myOldState.setState(myNewState);
	}

}
