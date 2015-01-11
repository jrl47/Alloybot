package ModelComponents;

import Model.ModelData;
import Model.StringVariable;

public class StateChangeButton extends ModelButton{

	StringVariable myOldState;
	String myNewState;
	public StateChangeButton(StringVariable oldState, String newState) {
		super();
		myNewState = newState;
		myOldState = oldState;
	}

	@Override
	public void respond() {
		myOldState.setString(myNewState);
	}

}
