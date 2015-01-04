package ModelComponents;

import Model.ModelData;

public class StartButton extends ModelButton{

	public StartButton(ModelData d) {
		super(d);
	}

	@Override
	public void respond() {
		data.setState(ModelData.GAME_OVER_STATE);
	}

}
