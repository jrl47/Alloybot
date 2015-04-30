package ModelComponents;

public class SmeltButton extends ModelButton{

	private ModelMap myMap;
	private ResourceManager myManager;
	private int myOreType1;
	private int myOreType2;
	private int myCreationType;
	private int mySize;
	private int myOreCost;
	private boolean wasTriggered;
	
	public SmeltButton(ModelMap m){
		myMap = m;
		myManager = myMap.getResources();
	}
	
	public void setCost(int oreType1, int oreType2, int creationType, int size){
		wasTriggered = false;
		myOreType1 = oreType1;
		myOreType2 = oreType2;
		myCreationType = creationType;
		mySize = size;
		myOreCost = (int) Math.pow(size, size);
	}
	public void respond() {
		wasTriggered = true;
		if(myManager.getOre(myOreType1)>=myOreCost && myManager.getOre(myOreType2)>=myOreCost && myManager.getGems()>=myOreCost){
			myManager.setOre(myManager.getOre(myOreType1) - myOreCost, myOreType1);
			myManager.setOre(myManager.getOre(myOreType2) - myOreCost, myOreType2);
			myManager.setGems(myManager.getGems() - myOreCost);
			myManager.setOre(myManager.getOre(myCreationType) + myOreCost, myCreationType);
		}
	}
	
	public boolean wasTriggered(){
		return wasTriggered;
	}
}
