package ModelComponents;

import Model.ModelData;
import Model.State;

public class RobotFactory extends MapCellObject{

	private State myOldState;
	public RobotFactory(State oldState) {
		super(false);
		myOldState = oldState;
	}
	public void step() {

	}
	public void respond() {
		myOldState.setState(ModelData.ROBOT_CREATION_STATE);
	}

}
