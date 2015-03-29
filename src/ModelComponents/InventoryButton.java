package ModelComponents;

import Model.ModelData;
import Model.State;

public class InventoryButton extends ModelButton{

	private State myOldState;
	public InventoryButton(State oldState) {
		super();
		myOldState = oldState;
	}
	public void step() {

	}
	public void respond() {
		myOldState.setState(ModelData.INVENTORY_STATE);
	}
}
