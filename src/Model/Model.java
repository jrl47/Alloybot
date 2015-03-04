package Model;
import ModelComponents.ModelComponent;

public class Model {
	
	public static final int TICK_SCALAR = 30;
	private ModelData data;
	public Model() {
		data = new ModelData();
	}
	public void step() {
		for(ModelComponent m: data.getComponents()){
			m.step();
		}
	}
	public ScreenData getScreenData(){
		return data.getScreenData();
	}
}
