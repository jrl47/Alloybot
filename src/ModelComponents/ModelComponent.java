package ModelComponents;

import Model.ModelData;


public abstract class ModelComponent {
	
	public ModelComponent(){
		
	}
	
	public abstract void step();
	public abstract void respond();
}
