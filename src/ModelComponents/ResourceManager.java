package ModelComponents;


public class ResourceManager extends ModelComponent{
	
	private int myOil;
	private int myOre;
	public ResourceManager(){
		myOil = 150000;
		myOre = 100;
	}
	public int getOil() {
		return myOil;
	}
	public int getOre(){
		return myOre;
	}
	public void setOil(int oil) {
		myOil = oil;
	}
	public void setOre(int ore) {
		myOre = ore;
	}
	@Override
	public void step() {
		
	}
	@Override
	public void respond() {

	}
}
