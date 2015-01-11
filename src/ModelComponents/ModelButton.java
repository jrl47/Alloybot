package ModelComponents;

import Model.ModelData;

public abstract class ModelButton extends ModelComponent{

	public ModelButton() {
		super();
	}

	@Override
	public void step() {

	}

	@Override
	public abstract void respond();

}
