package ModelComponents;


public class ResourceManager extends ModelComponent{
	
	private int myOil;
	private int myOre;
	private int myGems;
	public ResourceManager(){
		myOil = 150000;
		myOre = 100;
		myGems = 1;
	}
	public int getOil() {
		return myOil;
	}
	public int getOre(){
		return myOre;
	}
	public int getGems(){
		return myGems;
	}
	public void setOil(int oil) {
		myOil = oil;
	}
	public void setOre(int ore) {
		myOre = ore;
	}
	public void setGems(int gems) {
		myGems = gems;
	}
	@Override
	public void step() {
		
	}
	@Override
	public void respond() {

	}
}
