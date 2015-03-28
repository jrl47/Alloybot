package ModelComponents;

public class OldRobotCreationButton extends ModelButton{

	RobotFactory myFactory;
	ModelMap myMap;
	ResourceManager myManager;
	int oilCost;
	int oreCost;
	int gemCost;
	int newOilE;
	int newOreE;
	public OldRobotCreationButton(ModelMap m, int oilC, int oreC, int gemC, int newOil, int newOre){
		myMap = m;
		myManager = myMap.getResources();
		oilCost = oilC;
		oreCost = oreC;
		gemCost = gemC;
		newOilE = newOil;
		newOreE = newOre;
	}
	public void respond() {
		myFactory = (RobotFactory) myMap.getSelectedObject();
		if(myManager.getOil()>=oilCost && myManager.getOre(0)>=oreCost && myManager.getGems()>=gemCost
				&& myMap.getCell(myFactory.getX(), myFactory.getY() + 1).getObjects().size()==0){
			myManager.setOil(myManager.getOil() - oilCost);
			myManager.setOre(myManager.getOre(0) - oreCost, 0);
			myManager.setGems(myManager.getGems() - gemCost);
			Robot r = new Robot(newOilE, newOreE, myMap);
			myMap.addObject(r, myFactory.getX(), myFactory.getY() + 1);
		}
	}
	
}
