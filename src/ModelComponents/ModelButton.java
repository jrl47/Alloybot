package ModelComponents;

import Model.ModelData;

public abstract class ModelButton extends ModelComponent{

	public ModelButton(ModelData d) {
		super(d);
	}

	@Override
	public void step() {

	}

	@Override
	public abstract void respond();

}
