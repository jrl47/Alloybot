package ModelComponents;

import Model.ModelData;


public abstract class ModelComponent {

	protected ModelData data;
	
	public ModelComponent(ModelData d){
		data = d;
	}
	
	public abstract void step();
	public abstract void respond();
}
