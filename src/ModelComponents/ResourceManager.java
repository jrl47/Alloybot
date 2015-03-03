package ModelComponents;


public class ResourceManager extends ModelComponent{
	
	private int myOil;
	public ResourceManager(){
		myOil = 150000;
	}
	public int getOil() {
		return myOil;
	}
	public void setOil(int oil) {
		myOil = oil;
	}
	@Override
	public void step() {
		
	}
	@Override
	public void respond() {

	}
}
