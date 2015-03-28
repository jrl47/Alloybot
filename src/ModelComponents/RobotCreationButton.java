package ModelComponents;

public class RobotCreationButton extends ModelButton{

	private RobotFactory myFactory;
	private ModelMap myMap;
	private ResourceManager myManager;
	private int myOreType;
	private int mySize;
	private int myOreCost;
	public RobotCreationButton(ModelMap m){
		myMap = m;
		myManager = myMap.getResources();
	}
	public void setCost(int oreType, int size){
		myOreType = oreType;
		mySize = size;
		myOreCost = (int) Math.pow(size, size);
	}
	public void respond() {
		myFactory = (RobotFactory) myMap.getSelectedObject();
		if(myManager.getOre(myOreType)>=myOreCost && myManager.getGems()>=1
				&& myMap.getCell(myFactory.getX(), myFactory.getY() + 1).getObjects().size()==0){
			myManager.setOre(myManager.getOre(myOreType) - myOreCost, 0);
			myManager.setGems(myManager.getGems() - 1);
			Robot r = new Robot(myOreType, mySize, myMap);
			myMap.addObject(r, myFactory.getX(), myFactory.getY() + 1);
		}
	}
	
}
