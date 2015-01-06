package ModelComponents;

import Model.ModelData;

public class ModelStartButton extends ModelButton{

	public ModelStartButton(ModelData d) {
		super(d);
	}

	@Override
	public void respond() {
		data.setState(ModelData.GAME_OVER_STATE);
	}

}
